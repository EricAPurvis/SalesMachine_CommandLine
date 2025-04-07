package main;

import java.util.ArrayList;

public class Miner {

	private String name;
	private float dp;
	private ArrayList<Order> Cart = new ArrayList<Order>();
	
	private boolean COD=false;
	private boolean paid=false;
	private boolean uploaded=false;
	
	public Miner(String name, float price, float dp) {
		this.name=name;
		this.dp=dp;
		addOrder(price);
	}
	
	public Miner(String name, float price, float dp, boolean COD, boolean paid) {
		this.name=name;
		this.dp=dp;
		this.COD=COD;
		this.paid=paid;
		addOrder(price);
	}
	
	public Miner(String name, float dp, boolean COD, boolean paid, boolean uploaded) {
		this.name=name;
		this.dp=dp;
		this.COD=COD;
		this.paid=paid;
		this.uploaded=uploaded;
	}
	
	protected void addOrder(float price) {
		Cart.add(new Order(price));
	}
	
	protected boolean removeOrder(float price) {
		for(int i=0; i<Cart.size(); i++) {
			if(Cart.get(i).getPrice()==price) {
				Cart.remove(i);
				return true;
			}
		}
		return false;
	}
	
	protected void setDP(float dp) {
		this.dp=dp;
	}
	protected void setCOD(boolean COD) {
		this.COD=COD;
	}
	protected void setPaid(boolean paid) {
		this.paid=paid;
	}
	protected void setUploaded(boolean uploaded) {
		this.uploaded=uploaded;
	}
	
	protected String getName() {
		return(name);
	}
	
	protected float getDP() {
		return(dp);
	}
	
	protected boolean getCOD() {
		return(COD);
	}
	
	protected boolean getPaid() {
		return(paid);
	}
	
	protected boolean getUploaded() {
		return(uploaded);
	}
	
	protected ArrayList<Order> getCart() {
		return(Cart);
	}
	
}
