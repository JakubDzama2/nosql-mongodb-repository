package sk.upjs.nosql_mongodb_repository.entity;

public class RokStudijnyProgramCountGroupBy {
	
	private RokStudijnyProgramKey id;
	private Double value;
	
	public RokStudijnyProgramCountGroupBy() {
		super();
	}

	public RokStudijnyProgramKey getId() {
		return id;
	}

	public void setId(RokStudijnyProgramKey id) {
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
		return "RokStudijnyProgramCountGroupBy [id=" + id + ", value=" + value + "]";
	}
	
}
