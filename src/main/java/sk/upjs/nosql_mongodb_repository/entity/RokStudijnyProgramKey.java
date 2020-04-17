package sk.upjs.nosql_mongodb_repository.entity;

public class RokStudijnyProgramKey {
	
	private int rok;
	private MongoStudijnyProgram studijnyProgram;
	
	public RokStudijnyProgramKey() {
	}

	public int getRok() {
		return rok;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}

	public MongoStudijnyProgram getStudijnyProgram() {
		return studijnyProgram;
	}

	public void setStudijnyProgram(MongoStudijnyProgram studijnyProgram) {
		this.studijnyProgram = studijnyProgram;
	}

	@Override
	public String toString() {
		return "RokStudijnyProgramKey [rok=" + rok + ", studijnyProgram=" + studijnyProgram + "]";
	}
	
}
