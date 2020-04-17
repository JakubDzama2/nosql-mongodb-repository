package sk.upjs.nosql_mongodb_repository.entity;

import sk.upjs.nosql_data_source.entity.StudijnyProgram;

public class MongoStudijnyProgram {

	private Long id;
	private String skratka;
	private String popis;
	
	public MongoStudijnyProgram() {
	}

	public MongoStudijnyProgram(StudijnyProgram s) {
		this.id = s.getId();
		this.skratka = s.getSkratka();
		this.popis = s.getPopis();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkratka() {
		return skratka;
	}

	public void setSkratka(String skratka) {
		this.skratka = skratka;
	}

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

	@Override
	public String toString() {
		return "MongoStudijnyProgram [id=" + id + ", skratka=" + skratka + ", popis=" + popis + "]";
	}
	
}
