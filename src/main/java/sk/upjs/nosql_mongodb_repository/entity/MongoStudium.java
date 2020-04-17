package sk.upjs.nosql_mongodb_repository.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import sk.upjs.nosql_data_source.entity.StudijnyProgram;
import sk.upjs.nosql_data_source.entity.Studium;

public class MongoStudium {

	private Long id;
	@DateTimeFormat(iso = ISO.DATE)
	private Date zaciatokStudia;
	@DateTimeFormat(iso = ISO.DATE)
	private Date koniecStudia;
	@Indexed
	private MongoStudijnyProgram studijnyProgram;

	public MongoStudium() {

	}

	public MongoStudium(Studium s) {
		this.id = s.getId();
		this.studijnyProgram = new MongoStudijnyProgram(s.getStudijnyProgram());

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			this.zaciatokStudia = format.parse(s.getZaciatokStudia());
		} catch (ParseException e) {
			this.zaciatokStudia = null;
//			e.printStackTrace();
		}
		try {
			this.koniecStudia = format.parse(s.getKoniecStudia());
		} catch (ParseException e) {
			this.koniecStudia = null;
//			e.printStackTrace();
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getZaciatokStudia() {
		return zaciatokStudia;
	}

	public void setZaciatokStudia(Date zaciatokStudia) {
		this.zaciatokStudia = zaciatokStudia;
	}

	public Date getKoniecStudia() {
		return koniecStudia;
	}

	public void setKoniecStudia(Date koniecStudia) {
		this.koniecStudia = koniecStudia;
	}

	public MongoStudijnyProgram getStudijnyProgram() {
		return studijnyProgram;
	}

	public void setStudijnyProgram(MongoStudijnyProgram studijnyProgram) {
		this.studijnyProgram = studijnyProgram;
	}

	@Override
	public String toString() {
		return "MongoStudium [id=" + id + ", zaciatokStudia=" + zaciatokStudia + ", koniecStudia=" + koniecStudia
				+ ", studijnyProgram=" + studijnyProgram + "]";
	}

}
