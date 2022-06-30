package kh.java.pattern.strategy;

/**
 * 전략패턴 
 * 	- Context - 전략을 사용하는 맥락. Strategy 타입을 알고, 설정할 수 있다. Person
 * 	- Strategy - 인터페이스/추상클래스. Context가 의존하는 타입으로 다양한 자식타입으로 대체될 수 있다. Pet
 * 	- ConcreteStrategy - Strategy 구현체/자식타입. Strategy 역할을 동일하게 수행할 수 있어야 한다. Dog, Cat 
 *
 */

public class Person {
	private String name;
	private Pet pet;  // Person은 Dog나 Cat의 존재를 몰라도 Pet타입으로 제어할 수 있다(다형성)
	
	public Person(String name, Pet pet) {
		super();
		this.name = name;
		this.pet = pet;
	}
	
	public void playWithPet() {
		System.out.println(pet.getName() + "야~");
		System.out.println(pet.getReply());
	}
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", pet=" + pet + "]";
	}
	
}
