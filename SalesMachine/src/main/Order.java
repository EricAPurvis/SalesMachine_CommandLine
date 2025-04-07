package main;

public class Order {

	private float price;
	
	public Order(float price) {
		this.price=price;
	}
	
	protected float getPrice() {
		return(price);
	}
	
}
