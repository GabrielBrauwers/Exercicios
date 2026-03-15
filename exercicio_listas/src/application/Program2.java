package application;


import java.util.ArrayList;
import java.util.List;
import entities.Object;


public class Program2 {
	public static void main(String[]args) {
		
		List<Object> lista2 = new ArrayList<>();
		String name = "gabriel";
		Integer number = 7;
		
		Object obj = new Object(name, number);
		lista2.add(obj);
		
		for(Object obj2 : lista2) {
			if(number > 5) {
				obj2.toString2();
			}
			
		}
		
	}
}
