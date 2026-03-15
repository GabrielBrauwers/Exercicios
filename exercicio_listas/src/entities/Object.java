package entities;

public class Object {

	private String name;
	private Integer number;
	
	public Object(String name, Integer number) {
		this.name = name;
		this.number = number;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public void toString2() {
		System.out.println("nome: " + name);
		System.out.println("\nnumero: " + number);
	}
	
}
