package kh.java.pattern.strategy;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.test1();

	}

	private void test1() {
		Person chulsu = new Person("철수", new Dog("구리구리"));  // 반려견
		Person younghee = new Person("영희", new Cat("이슬")); // 반려묘
		
		chulsu.playWithPet();
		younghee.playWithPet();
		
		Person gd = new Person("홍길동", new Snake("드래곤"));
		gd.playWithPet();
		
	}

}
