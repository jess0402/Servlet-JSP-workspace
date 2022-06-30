package kh.java.pattern.strategy;

public class Dog extends Pet {
	
	
	// 생성자는 상속이 안되므로 이렇게 만들어줘야함.
	public Dog(String name) {
		super(name);
	}

	@Override
	String getReply() {
		return "멍멍";
	}
	

}
