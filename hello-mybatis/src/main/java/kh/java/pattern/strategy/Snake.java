package kh.java.pattern.strategy;

public class Snake extends Pet {
	
	public Snake(String name) {
		super(name);
	}

	@Override
	String getReply() {
		return "쉭쉭~";
	}

}
