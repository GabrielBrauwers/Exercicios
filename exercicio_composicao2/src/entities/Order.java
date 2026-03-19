package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private Date moment;
	private OrderStatus status;
	
	private List<OrderItem> items = new ArrayList<>();
	private Client client;
	
	public Order() {
	}

	public Order(Date moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}
	
	public Date getDate() {
		return moment;
	}
	
	public void setDate(Date moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public void removeItem(OrderItem item) {
		items.remove(item);
	}
	
	public Double total() {
		
		double sum = 0.0;
		
		for(OrderItem item : items) {
			sum += item.getPrice();
		}
		
		return sum;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Oredr moment: " + moment + "\n");
		sb.append("Order status: " + status + "\n");
		sb.append("Client: " + client.getName() + " " + client.getBirthDate() + " - " + client.getEmail() + "\n");
		sb.append("Order items: \n");
		
		for(OrderItem item : items) {
			sb.append(item.getProduct().getName() + 
					", " + item.getProduct().getPrice() + 
					", " + "Quantity: " + item.getQuantuty() + 
					" Subtotal: " + item.getPrice());
		}
		
		sb.append("Total price: " + total());
		
		return sb.toString();
	}
}
