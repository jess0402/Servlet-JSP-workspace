package kh.java.pattern.strategy;

public abstract class Pet {
	private String name;

	public Pet() {
		super();
	}

	public Pet(String name) {
		super();
		this.name = name;
	}
	
	abstract String getReply();

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Pet [name=" + name + "]";
	}
	
}
