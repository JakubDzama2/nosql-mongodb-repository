package sk.upjs.nosql_mongodb_repository.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sk.upjs.nosql_data_source.entity.Student;
import sk.upjs.nosql_data_source.persist.DaoFactory;
import sk.upjs.nosql_data_source.persist.StudentDao;
import sk.upjs.nosql_mongodb_repository.StudentRepository;
import sk.upjs.nosql_mongodb_repository.entity.MongoStudent;
import sk.upjs.nosql_mongodb_repository.entity.MongoStudijnyProgram;
import sk.upjs.nosql_mongodb_repository.entity.NameAndSurnameOnly;
import sk.upjs.nosql_mongodb_repository.entity.RokStudijnyProgramCountGroupBy;
import sk.upjs.nosql_mongodb_repository.entity.TitulCountGroupBy;

@Service
public class MongoStudentService {

	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	private StudentDao studentDao = DaoFactory.INSTANCE.getStudentDao();
	
	public void fillDb() {
		List<MongoStudent> students = studentDao.getAll()
			.stream()
			.map(s -> new MongoStudent(s))
			.collect(Collectors.toList());
			
//		students.forEach(s -> System.out.println(s));
		repository.saveAll(students);
	}
	
	public List<MongoStudent> findAll() {
		return repository.findAll();
	}
	
	public MongoStudent findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void saveStudent(MongoStudent student) {
		repository.save(student);
	}
	
	public void deleteStudent(MongoStudent student) {
		repository.delete(student);
	}
	
	public List<MongoStudent> findByTitulOnlyNames(String titul) {
		return repository.findByTitulOnlyNames(titul);
	}
	
	public Collection<NameAndSurnameOnly> findByTitulNameAndSurnameOnly(String titul) {
		return repository.findBySkratkaakadtitul(titul);
	}
	
	/* 
	 * bez indexu dopyt trva cca 25ms 
	 * s indexom dopyt trva cca 19ms
	 */
	public List<MongoStudent> findByRokAndStudijnyProgram(int rok, MongoStudijnyProgram studijnyProgram) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date lastDayOfYear = null;
		Date firstDayOfYear = null;
		try {
			lastDayOfYear = dateFormat.parse("31.12." + rok);
			firstDayOfYear = dateFormat.parse("1.1." + rok);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long start = System.currentTimeMillis();
		List<MongoStudent> students = repository.findByRokAndStudijnyProgram(lastDayOfYear, firstDayOfYear, studijnyProgram);
		System.out.println("time: " + (System.currentTimeMillis() - start));
		
		return students;
	}
	
	
	public void groupByRokAndStudijnyProgram() {
		String mapFunction = 
				"function() {" + 
				"	this.studium.forEach(function(studium) {" + 
				"		var startYear = studium.zaciatokStudia.getFullYear();" + 
				"		var endYear = startYear;" + 
				"		if (studium.koniecStudia) {" + 
				"			endYear = studium.koniecStudia.getFullYear();" + 
				"		}" + 
				"		for (var year = startYear; year <= endYear; year++) {" + 
				"			emit({rok: year, studijnyProgram: studium.studijnyProgram}, 1);" + 
				"		}" + 
				"	});" + 
				"}";
		String reduceFunction = 
				"function(studProgram, pocty) {" + 
				"	return Array.sum(pocty);" + 
				"}";
		
		
		MapReduceResults<RokStudijnyProgramCountGroupBy> results = 
				mongoTemplate.mapReduce(
						"students", 
						mapFunction, 
						reduceFunction,
						new MapReduceOptions().outputTypeInline(),
						RokStudijnyProgramCountGroupBy.class);
		printGroupByRokAndStudijnyProgram(results);
	}
	
	public void printGroupByRokAndStudijnyProgram(MapReduceResults<RokStudijnyProgramCountGroupBy> results) {
		System.out.println("-----------------------------------------------------------------------------");
	    System.out.printf("%5s %45s %8s", "ROK", "ŠTUDIJNÝ PROGRAM", "POČET");
	    System.out.println();
	    System.out.println("-----------------------------------------------------------------------------");
		for (RokStudijnyProgramCountGroupBy result : results) {
			System.out.format("%5d%45s%8.0f", result.getId().getRok(), result.getId().getStudijnyProgram().getPopis(), result.getValue());
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------------------");
	}
	
}
