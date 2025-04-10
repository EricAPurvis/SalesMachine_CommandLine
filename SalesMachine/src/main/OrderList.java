package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import apparatus.Screen;

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
		
		if(toRemove==null) {
			Screen.printSimpleError("From Not Found");
			return;
		}
		
		for(int i=0; i<Orders.size(); i++) {
			if(Orders.get(i).getName().equals(kept)) {
				ArrayList<Order> temp = toRemove.getCart();
				for(int x=0; x<temp.size(); x++) {
					Orders.get(i).addOrder(temp.get(x).getPrice());
				}
				Screen.printSimpleGood("Merge Sucessful!");
				return;
			}
		}
		Orders.add(toRemove);
		Screen.printSimpleError("To Not Found");
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
	
	//Done
	protected void addMiner(Miner miner) {
		Orders.add(miner);
	}
	
	//Done
	protected void addOrder(String name, float price) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				Orders.get(x).addOrder(price);
				Screen.printSimpleGood("Order Added");
				return;
			}
		}
		Orders.add(new Miner(name, price, 0));
		Screen.printSimpleGood("Miner+Order Added");
	}
	
	//Done
	protected void toggleCOD(String name) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				Orders.get(x).setCOD( !Orders.get(x).getCOD());
				return;
			}
		}
		Screen.printSimpleError("User not found!");
	}
	
	//Done
	protected void togglePaid(String name) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				Orders.get(x).setPaid( !Orders.get(x).getPaid() );
				return;
			}
		}
		Screen.printSimpleError("User not found!");
	}
	
	//Don't use directly at this time
	protected void setDP(String name, float dp) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				Orders.get(x).setDP(dp);
				return;
			}
		}
		Screen.printSimpleError("User not found!");
	}
	
	//Done
	protected void addDP(String name, float dp) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				Orders.get(x).setDP(Orders.get(x).getDP()+dp);
				Screen.printSimpleGood("DP Added!");
				return;
			}
		}
		Screen.printSimpleGood("Making User Slot");
		addMiner(new Miner(name, dp));
	}
	
	//Done
	protected boolean removeOneOrder(String name, float price) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				boolean ans = Orders.get(x).removeOrder(price);
				if(Orders.get(x).getCart().size()==0) {
					Orders.remove(x);
				}
				if(ans) {
					Screen.printSimpleGood("Order Removed");
				}else {
					Screen.printSimpleGood("Order not found");
				}
				return ans;
			}
		}
		Screen.printSimpleGood("Miner not found");
		return false;
	}
	
	//Done
	protected void removeMiner(String name) {
		for(int x=0; x<Orders.size(); x++) {
			if( Orders.get(x).getName().equals(name) ) {
				Orders.remove(x);
				Screen.printSimpleGood("Remove Sucessful!");
				return;
			}
		}
		Screen.printSimpleError("Miner not found!");
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

	//Done
	protected void showData() {
		
		int MinerTotal = Orders.size();
		float totalTotal=0.0f;
		int totalItems=0;
		float dpTotal=0;
		
		for(int x=0; x<Orders.size(); x++) {
			Screen.printDataShown(Orders.get(x).getName()+"\n\t");
			dpTotal+=Orders.get(x).getDP();
			float total=0;
			for(int y=0; y<Orders.get(x).getCart().size(); y++) {
				float temp=Orders.get(x).getCart().get(y).getPrice();
				total+=temp;
				if(y%10==0) {
					Screen.printDataShown("\n\t");
				}
				if(y!=Orders.get(x).getCart().size()-1) {
					Screen.printDataShown(temp+", ");
				}else {
					Screen.printDataShown(""+temp);
				}
			}
			totalTotal+=total;
			totalItems+=Orders.get(x).getCart().size();
			Screen.printDataShown("\n\t\tTotal = "+total+"\tItems = "+Orders.get(x).getCart().size()+"\tDP = "+Orders.get(x).getDP()+"\tCOD = "+Orders.get(x).getCOD()+"\tPaid = "+Orders.get(x).getPaid()+"\tPayment Needed: "+(total-Orders.get(x).getDP())+"\n\n");
		}
		
		
		Screen.printDataShown("\nTotal Totals = "+totalTotal+"    Total Items = "+totalItems+"    Total Miners = "+MinerTotal+"    Total DP = "+dpTotal+"    Average Item Cost = "+(float)(totalTotal/(float)totalItems));
	}

	//Done
	protected void showDataLike(String target) {
		int MinerTotal = 0;
		float totalTotal=0.0f;
		int totalItems=0;
		float dpTotal=0;
		
		for(int x=0; x<Orders.size(); x++) {
			if(Orders.get(x).getName().toLowerCase().startsWith(target.toLowerCase())) {
				MinerTotal++;
				Screen.printDataShown(Orders.get(x).getName()+"\n\t");
				float total=0;
				for(int y=0; y<Orders.get(x).getCart().size(); y++) {
					float temp=Orders.get(x).getCart().get(y).getPrice();
					total+=temp;
					if(y!=Orders.get(x).getCart().size()-1) {
						Screen.printDataShown(temp+", ");
					}else {
						Screen.printDataShown(""+temp);
					}
				}
				Screen.printDataShown("\n\t\tTotal = "+total+"\tItems = "+Orders.get(x).getCart().size()+"\tDP = "+Orders.get(x).getDP()+"\tCOD = "+Orders.get(x).getCOD()+"\tPaid = "+Orders.get(x).getPaid()+"\tPayment Needed: "+(total-Orders.get(x).getDP())+"\n\n");
			}
		}
		
		Screen.printDataShown("\nTotal Totals = "+totalTotal+"    Total Items = "+totalItems+"    Total Miners = "+MinerTotal+"    Total DP = "+dpTotal+"    Average Item Cost = "+(float)(totalTotal/(float)totalItems));
		
	}
	//Done	
	protected void showDataCOD() {
		
		int MinerTotal = 0;
		float totalTotal=0.0f;
		int totalItems=0;
		float dpTotal=0;
		
		for(int x=0; x<Orders.size(); x++) {
			if(Orders.get(x).getCOD()) {
				MinerTotal++;
				Screen.printDataShown(Orders.get(x).getName()+"\n\t");
				dpTotal+=Orders.get(x).getDP();
				float total=0;
				for(int y=0; y<Orders.get(x).getCart().size(); y++) {
					float temp=Orders.get(x).getCart().get(y).getPrice();
					total+=temp;
					if(y!=Orders.get(x).getCart().size()-1) {
						Screen.printDataShown(temp+", ");
					}else {
						Screen.printDataShown(""+temp);
					}
				}
				totalTotal+=total;
				totalItems+=Orders.get(x).getCart().size();
				Screen.printDataShown("\n\t\tTotal = "+total+"\tItems = "+Orders.get(x).getCart().size()+"\tDP = "+Orders.get(x).getDP()+"\tCOD = "+Orders.get(x).getCOD()+"\tPaid = "+Orders.get(x).getPaid()+"\tPayment Needed: "+(total-Orders.get(x).getDP())+"\n\n");
			}
		}
		
		Screen.printDataShown("\nTotal Totals = "+totalTotal+"    Total Items = "+totalItems+"    Total Miners = "+MinerTotal+"    Total DP = "+dpTotal+"    Average Item Cost = "+(float)(totalTotal/(float)totalItems));
	}
	//Done
	protected void showDataPaid() {
		
		int MinerTotal = 0;
		float totalTotal=0.0f;
		int totalItems=0;
		float dpTotal=0;
		
		for(int x=0; x<Orders.size(); x++) {
			if(Orders.get(x).getPaid()) {
				MinerTotal++;
				Screen.printDataShown(Orders.get(x).getName()+"\n\t");
				dpTotal+=Orders.get(x).getDP();
				float total=0;
				for(int y=0; y<Orders.get(x).getCart().size(); y++) {
					float temp=Orders.get(x).getCart().get(y).getPrice();
					total+=temp;
					if(y!=Orders.get(x).getCart().size()-1) {
						Screen.printDataShown(temp+", ");
					}else {
						Screen.printDataShown(""+temp);
					}
				}
				totalTotal+=total;
				totalItems+=Orders.get(x).getCart().size();
				Screen.printDataShown("\n\t\tTotal = "+total+"\tItems = "+Orders.get(x).getCart().size()+"\tDP = "+Orders.get(x).getDP()+"\tCOD = "+Orders.get(x).getCOD()+"\tPaid = "+Orders.get(x).getPaid()+"\tPayment Needed: "+(total-Orders.get(x).getDP())+"\n\n");
			}
		}
		
		Screen.printDataShown("\nTotal Totals = "+totalTotal+"    Total Items = "+totalItems+"    Total Miners = "+MinerTotal+"    Total DP = "+dpTotal+"    Average Item Cost = "+(float)(totalTotal/(float)totalItems));
	}

	//Done algorithm fixed I hope
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
				i--;
			}
		}
		
		//Find Largest Name
		int largest=0;
		for(int i=0; i<temp.size(); i++) {
			if(temp.get(i).getName().length()>largest) {
				largest=temp.get(i).getName().length();
			}
		}
		
		Screen.printDataShown("2+ Items grid: \n");
		Screen.printDataShown("   ");
		
		//Column numbers
		for(int x=0; x<width; x++) {
			for(int z=0; z<largest/2; z++) {
				Screen.printDataShown("  ");
			}
			String test = ""+(x+1)+"";
			Screen.printDataShown(test);
			for(int z=0; z<largest/2-1; z++) {
				Screen.printDataShown("  ");
			}
		}
		
		Screen.printDataShown("\n");
		
		//Sort 2+ carts
		for(int y=0; y<(int)Math.ceil((float)temp.size()/(float)width); y++) {
			for(int x=0; x<width; x++) {
				if(x==0) {
					Screen.printDataShown((y+1)+"  ");
				}
				if( y*width+x < temp.size()) {
					
					for(int z=0; z<(largest-temp.get(y*width+x).getName().length())/2; z++) {
						Screen.printDataShown("  ");
					}
					
					Screen.printDataShown(temp.get(y*width+x).getName());
					
					for(int z=0; z<(largest-temp.get(y*width+x).getName().length())/2; z++) {
						Screen.printDataShown("  ");
					}
					
				}
			}
			Screen.printDataShown("\n\n");
		}
		
		//Find Largest Name
		largest=0;
		for(int i=0; i<ones.size(); i++) {
			if(ones.get(i).getName().length()>largest) {
				largest=ones.get(i).getName().length();
			}
		}
		
		//Print 1 of carts
		Screen.printDataShown("\n\n1's:\n");
		int oneOfWidth=5;
		for(int y=0; y<(int)Math.ceil((float)ones.size()/(float)oneOfWidth); y++) {
			for(int x=0; x<oneOfWidth; x++) {
				if( y*oneOfWidth+x < ones.size()) {
					for(int z=0; z<(largest-ones.get(y*oneOfWidth+x).getName().length())/2; z++) {
						Screen.printDataShown("  ");
					}
					
					Screen.printDataShown(ones.get(y*oneOfWidth+x).getName()+"       ");
					
					for(int z=0; z<(largest-ones.get(y*oneOfWidth+x).getName().length())/2; z++) {
						Screen.printDataShown("  ");
					}
				}
			}
			Screen.printDataShown("\n\n");
		}
		
		Screen.printDataShown("\n\n");
		
	}
	
	//Done
	protected void sortMinersAlphabeticlyAsc() {
		Collections.sort(Orders, new Comparator<Miner>() {
		    public int compare(Miner m1, Miner m2) {
		        return m1.getName().toLowerCase().compareTo(m2.getName().toLowerCase());
		    }
		});
	}
	
	//Done
	protected void sortMinersAlphabeticlyDesc() {
		Collections.sort(Orders, new Comparator<Miner>() {
		    public int compare(Miner m1, Miner m2) {
		        return m2.getName().toLowerCase().compareTo(m1.getName().toLowerCase());
		    }
		});
	}
	
	//Done
	protected void sortMinersBySizeDesc() {
		Collections.sort(Orders, new Comparator<Miner>() {
		    public int compare(Miner m1, Miner m2) {
		        return m2.getCart().size() - m1.getCart().size();
		    }
		});
	}
	
	//Done
	protected void sortMinersBySizeAsc() {
		Collections.sort(Orders, new Comparator<Miner>() {
		    public int compare(Miner m1, Miner m2) {
		        return m1.getCart().size() - m2.getCart().size();
		    }
		});
	}
	
}
