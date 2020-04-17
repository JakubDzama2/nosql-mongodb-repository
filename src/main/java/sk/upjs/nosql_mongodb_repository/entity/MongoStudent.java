package sk.upjs.nosql_mongodb_repository.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import sk.upjs.nosql_data_source.entity.Student;
import sk.upjs.nosql_data_source.entity.Studium;

@Document(collection = "students")
public class MongoStudent {

	@Id
	private Long id;
	private String meno;
	private String priezvisko;
	private char kodpohlavie;
	@Field("titul")
	private String skratkaakadtitul;
	private List<MongoStudium> studium = new ArrayList<MongoStudium>();
	
	public MongoStudent() {
	}

	public MongoStudent(Student s) {
		this.id = s.getId();
		this.meno = s.getMeno();	
		this.priezvisko = s.getPriezvisko();
		this.kodpohlavie = s.getKodpohlavie();
		this.skratkaakadtitul = s.getSkratkaakadtitul();
		this.studium = s.getStudium().stream().map(studium -> new MongoStudium(studium)).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public String getPriezvisko() {
		return priezvisko;
	}

	public void setPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}

	public char getKodpohlavie() {
		return kodpohlavie;
	}

	public void setKodpohlavie(char kodpohlavie) {
		this.kodpohlavie = kodpohlavie;
	}

	public String getSkratkaakadtitul() {
		return skratkaakadtitul;
	}

	public void setSkratkaakadtitul(String skratkaakadtitul) {
		this.skratkaakadtitul = skratkaakadtitul;
	}

	public List<MongoStudium> getStudium() {
		return studium;
	}

	public void setStudium(List<MongoStudium> studium) {
		this.studium = studium;
	}

	@Override
	public String toString() {
		return "MongoStudent [id=" + id + ", meno=" + meno + ", priezvisko=" + priezvisko + ", kodpohlavie="
				+ kodpohlavie + ", skratkaakadtitul=" + skratkaakadtitul + ", studium=" + studium + "]";
	}
	
	
	
	
}
