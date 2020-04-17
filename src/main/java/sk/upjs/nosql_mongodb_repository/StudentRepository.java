package sk.upjs.nosql_mongodb_repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import sk.upjs.nosql_mongodb_repository.entity.MongoStudent;
import sk.upjs.nosql_mongodb_repository.entity.MongoStudijnyProgram;
import sk.upjs.nosql_mongodb_repository.entity.NameAndSurnameOnly;

public interface StudentRepository extends MongoRepository<MongoStudent, Long> {

	@Query(value = "{ titul: ?0 }", fields = "{ _id: 0, meno: 1, priezvisko: 1 }")
	List<MongoStudent> findByTitulOnlyNames(String titul);
	
	Collection<NameAndSurnameOnly> findBySkratkaakadtitul(String titul);
	
	@Query(value = "{ 'studium.zaciatokStudia' : { '$lte': ?0 }," + 
					" 'studium.koniecStudia' : { '$gte': ?1 }," + 
					" 'studium.studijnyProgram' : ?2 }")
	List<MongoStudent> findByRokAndStudijnyProgram(Date lastDayOfYear, Date firstDayOfYear, MongoStudijnyProgram studijnyProgram);
}
