package kh.java.pattern.strategy;

public class Cat extends Pet {
	
	public Cat(String name) {
		super(name);
	}

	@Override
	String getReply() {
		return "야옹~";
	}
	
	
}
