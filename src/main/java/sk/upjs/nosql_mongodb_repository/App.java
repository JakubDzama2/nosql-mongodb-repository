package sk.upjs.nosql_mongodb_repository;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sk.upjs.nosql_mongodb_repository.business.MongoStudentService;
import sk.upjs.nosql_mongodb_repository.entity.MongoStudent;
import sk.upjs.nosql_mongodb_repository.entity.MongoStudijnyProgram;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MongoConfig.class);
		MongoStudentService studentService = context.getBean(MongoStudentService.class);
		studentService.fillDb();
		List<MongoStudent> all = studentService.findAll();
//		all.stream()
//			.forEach(s -> System.out.println(s));
//
		// 1. uloha
		// metoda vracia list studentov s danym titulom, ale vsetky atributy okrem mena a priezviska (a id) maju hodnotu null
		studentService.findByTitulNameAndSurnameOnly("Mgr.")
			.stream()
			.forEach(s -> System.out.println(s));
		System.out.println();
		// druha moznost projekcie je implementovana v tejto metode (v tomto pripade bude null aj id):
		// studentService.findByTitulOnlyNames("Mgr.")
		//	.stream()
		//	.forEach(s -> System.out.println(s));
		
		MongoStudijnyProgram studijnyProgram = all.get(0).getStudium().get(0).getStudijnyProgram();
//		System.out.println(studijnyProgram);
//		System.out.println();

		// 2. a 3. uloha
		// kvoli tejto som ukladal fieldy Studia: zaciatokStudia a koniecStudia vo formate Date, nie String (dufam, ze som mohol :D )
		// bez indexu dopyt trva cca 25ms ... s indexom dopyt trva cca 19ms
		studentService.findByRokAndStudijnyProgram(2001, studijnyProgram)
		.stream()
		.forEach(s -> System.out.println(s));
		System.out.println();
		
		// 4. uloha
		// riesil som to pomocou MapReduce
		studentService.groupByRokAndStudijnyProgram();
		context.close();
	}
}
