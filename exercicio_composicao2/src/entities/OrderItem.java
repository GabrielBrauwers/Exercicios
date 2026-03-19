package entities;

public class OrderItem {

	private Integer quantity;
	private Double price;
	
	private Product product;
	
	public OrderItem() {
	}

	public OrderItem(Integer quantity, Double price, Product product) {
		this.quantity = quantity;
		this.product = product;
		this.price = subTotal();
	}

	public Integer getQuantuty() {
		return quantity;
	}

	public void setQuantuty(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double subTotal() {
		return product.getPrice() * quantity;
	}
}
