package main;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class FileManager {

	private String path="res/saves/";
	private String ext=".MSS";
	
	public OrderList load(LocalDate date) {
		OrderList list = new OrderList();
		
		try {
			File file = new File(path+date+ext);
			if(file.exists()) {
				System.out.println("Loading Date "+date.toString());
				StartUp.time = date;
				Scanner scanFile = new Scanner(file);
				String temp="";
				while(scanFile.hasNext()) {

					temp=scanFile.nextLine();
					
					if(temp.equals("User")) {
						
						String name="";
						float dp=0.0f;
						boolean COD=false;
						boolean paid=false;
						boolean uploaded=false;
						Miner person;
						
						//Name
						name = scanFile.nextLine();
						scanFile.nextLine();
						//DP
						dp = Float.parseFloat(scanFile.nextLine());
						scanFile.nextLine();
						//DP
						COD = 1==Integer.parseInt(scanFile.nextLine());
						scanFile.nextLine();
						//Paid
						paid = 1==Integer.parseInt(scanFile.nextLine());
						scanFile.nextLine();
						//Uploaded
						uploaded = 1==Integer.parseInt(scanFile.nextLine());
						scanFile.nextLine();
						
						//Create Miner Instance
						person = new Miner(name, dp, COD, paid, uploaded);
						
						//Items
						String[] items = scanFile.nextLine().split(",");
						for(int i=0; i<items.length; i++) {
							person.addOrder(Float.parseFloat(items[i]));
						}
						
						list.addMiner(person);
								
					}
					
				}
				
			}else {
				System.out.println("File "+date+" not found!");
				System.out.println("Load yesterday's data? (y/n)");
				Scanner userInput = new Scanner(System.in);
				String input = userInput.nextLine();
				switch(input) {
					case("y"):
						date = date.minusDays(1);
						list = load(date);
						break;
					case("n"):
						break;
				}
			}
			
		}catch(Exception ex) {
			System.out.println(date+" Failed to load!");
		}
		
		return(list);
	}
	
	public void save(LocalDate date, OrderList orderList) {
		
		try {
			System.out.println("Saving as "+date.toString()+ext);
			File file = new File(path+date.toString()+ext);
			PrintWriter pw = new PrintWriter(file);
			
			ArrayList<Miner> miners = orderList.getOrders();
			for(int y=0; y<miners.size(); y++) {
				
				pw.write("User\n");
				pw.write(miners.get(y).getName()+"\n");
				
				pw.write("DP\n");
				pw.write(miners.get(y).getDP()+"\n");
				
				pw.write("COD\n");
				if(miners.get(y).getCOD()) {
					pw.write("1\n");
				}else {
					pw.write("0\n");
				}
				pw.write("Paid\n");
				if(miners.get(y).getPaid()) {
					pw.write("1\n");
				}else {
					pw.write("0\n");
				}
				
				pw.write("Uploaded\n");
				if(miners.get(y).getUploaded()) {
					pw.write("1\n");
				}else {
					pw.write("0\n");
				}
				
				pw.write("Items\n");
				ArrayList<Order> orders = miners.get(y).getCart();
				for(int x=0; x<orders.size(); x++) {
					if(x==orders.size()-1) {
						pw.write(miners.get(y).getCart().get(x).getPrice()+"");
					}else {
						pw.write(miners.get(y).getCart().get(x).getPrice()+",");
					}
				}
				
				pw.write("\n");
			}
			
			pw.write("-endOfMajesticSalesLineFooter-");
			pw.flush();
			pw.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage()+"issue");
		}
		
	}
	
}
