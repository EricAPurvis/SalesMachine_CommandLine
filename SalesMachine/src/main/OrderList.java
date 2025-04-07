package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrderList {

	private ArrayList<Miner> Orders = new ArrayList<Miner>();
	
	protected ArrayList<Miner> getOrders() {
		return Orders;
	}
	
	protected void mergeMiners(String kept, String removed) {
		Miner toRemove=null;
		for(int i=0; i<Orders.size(); i++) {
			if(Orders.get(i).getName().equals(removed)) {
				toRemove = Orders.remove(i);
				break;
			}
		}
		for(int i=0; i<Orders.size(); i++) {
			if(Orders.get(i).getName().equals(kept)) {
				ArrayList<Order> temp = toRemove.getCart();
				for(int x=0; x<temp.size(); x++) {
					Orders.get(i).addOrder(temp.get(x).getPrice());
				}
				break;
			}
		}
	}
	
	protected void changeMiners(String to, String from, float price) {
		Order takenOrder=null;
		for(int i=0; i<Orders.size(); i++) {
			if(Orders.get(i).getName().equals(from)) {
				for(int x=0; x<Orders.get(i).getCart().size(); x++) {
					if(Orders.get(i).getCart().get(x).getPrice()==price) {
						takenOrder = Orders.get(i).getCart().remove(x);
						if(Orders.get(i).getCart().size()==0) {
							Orders.remove(i);
						}
						break;
					}
				}
			}
		}
		if(takenOrder!=null) {
			for(int i=0; i<Orders.size(); i++) {
				if(Orders.get(i).getName().equals(to)) {
					Orders.get(i).addOrder(takenOrder.getPrice());
					break;
				}
			}
		}else {
			System.out.println("Target(s) or Target Value Do Not Exist");
		}
	}
	
	protected void addMiner(Miner miner) {
		Orders.add(miner);
	}
	
	protected void addOrder(String name, float price) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				Orders.get(x).addOrder(price);
				return;
			}
		}
		Orders.add(new Miner(name, price, 0));
	}
	
	protected void toggleCOD(String name) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				Orders.get(x).setCOD( !Orders.get(x).getCOD());
				return;
			}
		}
		System.out.println("Failed: User not found!");
	}
	
	protected void togglePaid(String name) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				Orders.get(x).setPaid( !Orders.get(x).getPaid() );
				return;
			}
		}
		System.out.println("Failed: User not found!");
	}
	
	protected void setDP(String name, float dp) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				Orders.get(x).setDP(dp);
				return;
			}
		}
		System.out.println("Failed: User not found!");
	}
	
	protected void addDP(String name, float dp) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				Orders.get(x).setDP(Orders.get(x).getDP()+dp);
				return;
			}
		}
		System.out.println("Failed: User not found!");
	}
	
	protected boolean removeOneOrder(String name, float price) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				boolean ans = Orders.get(x).removeOrder(price);
				if(Orders.get(x).getCart().size()==0) {
					Orders.remove(x);
				}
				return ans;
			}
		}
		System.out.println("Miner not found for one removal!");
		return false;
	}
	
	protected void removeMiner(String name) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				Orders.remove(x);
				return;
			}
		}
		System.out.println("Miner not found for removal!");
	}
	
	protected Miner getCart(String name) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				return(Orders.get(x));
			}
		}
		System.out.println("User not found");
		return(null);
	}
	
	protected void showData() {
		
		int MinerTotal = Orders.size();
		float totalTotal=0.0f;
		int totalItems=0;
		float dpTotal=0;
		
		for(int x=0; x<Orders.size(); x++) {
			System.out.print(Orders.get(x).getName()+"\n\t");
			dpTotal+=Orders.get(x).getDP();
			float total=0;
			for(int y=0; y<Orders.get(x).getCart().size(); y++) {
				float temp=Orders.get(x).getCart().get(y).getPrice();
				total+=temp;
				if(y!=Orders.get(x).getCart().size()-1) {
					System.out.print(temp+", ");
				}else {
					System.out.print(temp);
				}
			}
			totalTotal+=total;
			totalItems+=Orders.get(x).getCart().size();
			System.out.print("\n\t\tTotal = "+total+"\tItems = "+Orders.get(x).getCart().size()+"\tDP = "+Orders.get(x).getDP()+"\tCOD = "+Orders.get(x).getCOD()+"\tPaid = "+Orders.get(x).getPaid()+"\tPayment Needed: "+(total-Orders.get(x).getDP())+"\n\n");
		}
		
		
		System.out.println("\nTotal Totals = "+totalTotal+"\t Total Items = "+totalItems+"\t Total Miners = "+MinerTotal+"\t Total DP = "+dpTotal+"\t Average Item Cost = "+(float)(totalTotal/(float)totalItems));
	}
	
	protected void showDataOne(String target) {
		for(int x=0; x<Orders.size(); x++) {
			if(Orders.get(x).getName().equals(target)) {
				System.out.print(Orders.get(x).getName()+"\n\t");
				float total=0;
				for(int y=0; y<Orders.get(x).getCart().size(); y++) {
					float temp=Orders.get(x).getCart().get(y).getPrice();
					total+=temp;
					if(y!=Orders.get(x).getCart().size()-1) {
						System.out.print(temp+", ");
					}else {
						System.out.print(temp);
					}
				}
				System.out.print("\n\t\tTotal = "+total+"\tItems = "+Orders.get(x).getCart().size()+"\tDP = "+Orders.get(x).getDP()+"\tCOD = "+Orders.get(x).getCOD()+"\tPaid = "+Orders.get(x).getPaid()+"\tPayment Needed: "+(total-Orders.get(x).getDP())+"\n\n");
			}
		}
		
	}
	
	protected void showDataCOD() {
		
		int MinerTotal = Orders.size();
		float totalTotal=0.0f;
		int totalItems=0;
		float dpTotal=0;
		
		for(int x=0; x<Orders.size(); x++) {
			if(Orders.get(x).getCOD()) {
				System.out.print(Orders.get(x).getName()+"\n\t");
				dpTotal+=Orders.get(x).getDP();
				float total=0;
				for(int y=0; y<Orders.get(x).getCart().size(); y++) {
					float temp=Orders.get(x).getCart().get(y).getPrice();
					total+=temp;
					if(y!=Orders.get(x).getCart().size()-1) {
						System.out.print(temp+", ");
					}else {
						System.out.print(temp);
					}
				}
				totalTotal+=total;
				totalItems+=Orders.get(x).getCart().size();
				System.out.print("\n\t\tTotal = "+total+"\tItems = "+Orders.get(x).getCart().size()+"\tDP = "+Orders.get(x).getDP()+"\tCOD = "+Orders.get(x).getCOD()+"\tPaid = "+Orders.get(x).getPaid()+"\tPayment Needed: "+(total-Orders.get(x).getDP())+"\n\n");
			}
		}
		
		System.out.println("\nTotal Totals = "+totalTotal+"\t Total Items = "+totalItems+"\t Total Miners = "+MinerTotal+"\t Total DP = "+dpTotal+"\t Average Item Cost = "+(float)(totalTotal/(float)totalItems));
	}
	
	protected void showDataPaid() {
		
		int MinerTotal = Orders.size();
		float totalTotal=0.0f;
		int totalItems=0;
		float dpTotal=0;
		
		for(int x=0; x<Orders.size(); x++) {
			if(Orders.get(x).getPaid()) {
				System.out.print(Orders.get(x).getName()+"\n\t");
				dpTotal+=Orders.get(x).getDP();
				float total=0;
				for(int y=0; y<Orders.get(x).getCart().size(); y++) {
					float temp=Orders.get(x).getCart().get(y).getPrice();
					total+=temp;
					if(y!=Orders.get(x).getCart().size()-1) {
						System.out.print(temp+", ");
					}else {
						System.out.print(temp);
					}
				}
				totalTotal+=total;
				totalItems+=Orders.get(x).getCart().size();
				System.out.print("\n\t\tTotal = "+total+"\tItems = "+Orders.get(x).getCart().size()+"\tDP = "+Orders.get(x).getDP()+"\tCOD = "+Orders.get(x).getCOD()+"\tPaid = "+Orders.get(x).getPaid()+"\tPayment Needed: "+(total-Orders.get(x).getDP())+"\n\n");
			}
		}
		
		System.out.println("\nTotal Totals = "+totalTotal+"\t Total Items = "+totalItems+"\t Total Miners = "+MinerTotal+"\t Total DP = "+dpTotal+"\t Average Item Cost = "+(float)(totalTotal/(float)totalItems));
	}
	
	protected void printGrid(int width) {
		
		ArrayList<Miner> temp = new ArrayList<Miner>();
		ArrayList<Miner> ones = new ArrayList<Miner>();
		
		//Copy Orders List
		for(int i=0; i<Orders.size(); i++) {
			temp.add(Orders.get(i));
		}
		
		//Remove one of carts
		for(int i=0; i<temp.size(); i++) {
			if(temp.get(i).getCart().size()==1) {
				ones.add(temp.remove(i));
			}
		}
		
		//Find Largest Name
		int largest=0;
		for(int i=0; i<temp.size(); i++) {
			if(temp.get(i).getName().length()>largest) {
				largest=temp.get(i).getName().length();
			}
		}
		
		System.out.println("2+ Items grid: ");
		System.out.print("   ");
		
		//Column numbers
		for(int x=0; x<width; x++) {
			for(int z=0; z<largest/2; z++) {
				System.out.print(" ");
			}
			String test = ""+(x+1)+"    ";
			System.out.print(test);
			for(int z=0; z<largest/2-1; z++) {
				System.out.print(" ");
			}
		}
		
		System.out.println("");
		
		//Sort 2+ carts
		for(int y=0; y<(int)Math.ceil((float)temp.size()/(float)width); y++) {
			for(int x=0; x<width; x++) {
				if(x==0) {
					System.out.print((y+1)+"  ");
				}
				if( y*width+x < temp.size()) {
					System.out.print(temp.get(y*width+x).getName()+"    ");
					for(int z=0; z<largest-temp.get(y*width+x).getName().length(); z++) {
						System.out.print(" ");
					}
				}
			}
			System.out.println("\n");
		}
		
		//Print 1 of carts
		System.out.println("\n1's:");
		int oneOfWidth=5;
		for(int y=0; y<(int)Math.ceil((float)ones.size()/(float)oneOfWidth); y++) {
			for(int x=0; x<oneOfWidth; x++) {
				if( y*width+x < ones.size()) {
					System.out.print(ones.get(y*oneOfWidth+x).getName()+" ");
				}
			}
		}
		
		System.out.println("\n");
		
	}
	
	protected void sortMinersAlphabeticlyAsc() {
		Collections.sort(Orders, new Comparator<Miner>() {
		    public int compare(Miner m1, Miner m2) {
		        return m1.getName().toLowerCase().compareTo(m2.getName().toLowerCase());
		    }
		});
	}
	
	protected void sortMinersAlphabeticlyDesc() {
		Collections.sort(Orders, new Comparator<Miner>() {
		    public int compare(Miner m1, Miner m2) {
		        return m2.getName().toLowerCase().compareTo(m1.getName().toLowerCase());
		    }
		});
	}
	
	protected void sortMinersBySizeDesc() {
		Collections.sort(Orders, new Comparator<Miner>() {
		    public int compare(Miner m1, Miner m2) {
		        return m2.getCart().size() - m1.getCart().size();
		    }
		});
	}
	
	protected void sortMinersBySizeAsc() {
		Collections.sort(Orders, new Comparator<Miner>() {
		    public int compare(Miner m1, Miner m2) {
		        return m1.getCart().size() - m2.getCart().size();
		    }
		});
	}
	
}
