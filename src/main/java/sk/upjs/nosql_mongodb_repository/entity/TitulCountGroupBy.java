package sk.upjs.nosql_mongodb_repository.entity;

public class TitulCountGroupBy {
	
	private String id;
	private Double value;
	
	public TitulCountGroupBy() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TitulCountGroupBy [id=" + id + ", value=" + value + "]";
	}
	
	
}
