package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.OrderStatus;
import entities.Product;

public class Program {
	public void main(String[]args) {
		
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Enter client data: ");
		
		System.out.print("\n\nName: ");
		String name = sc.nextLine();
		
		System.out.print("\nE-mail: ");
		String email = sc.nextLine();
		
		System.out.print("\nBirth date (DD/MM/YYYY): ");
		LocalDate birthDate = LocalDate.parse(sc.next(), fmt);
		
		Client client = new Client(name, email, birthDate);
		
		System.out.println("Enter order data: ");
		System.out.print("Status: ");
		sc.nextLine();
		OrderStatus status = OrderStatus.valueOf(sc.nextLine());
		Date dataAtual = new Date();
		
		Order order = new Order(dataAtual, status, client);
		
		System.out.print("How many items to this order? ");
		int n = sc.nextInt();
		
		for(int i = 1; i <= n; i++) {
			
			System.out.printf("\nEnter number #%d item data: ", i);
			
			sc.nextLine();
			
			System.out.print("\n\nItem name: ");
			String itemName = sc.nextLine();
			
			System.out.print("\nItem price: ");
			Double itemPrice = sc.nextDouble();
			
			System.out.print("\nQuantity: ");
			Integer quantity = sc.nextInt();
			
			Double subPrice = 0.0;
			
			Product product = new Product(itemName, itemPrice);
		
			OrderItem item = new OrderItem(quantity,subPrice, product);
			
			order.addItem(item);
		}
		
		System.out.println("Order sumary: ");
	
		System.out.println(order);
		
		sc.close();
	}
}
