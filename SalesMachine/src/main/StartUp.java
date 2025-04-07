package main;

import java.util.Scanner;
import java.time.LocalDate;

public class StartUp {

	public static LocalDate time;
	public static boolean printerModeOn=true;
	public static boolean autoSave=true;
	public static OrderList orders;
	
	public static void main(String args[]) {
		
		//Load and Select Printer for use
        PrinterHandler rp = new PrinterHandler();
        rp.selectPrinter();
		
        //Get data structure ready
		orders = new OrderList();
		
		//Load Existing Data
		FileManager fManager = new FileManager();
		time = LocalDate.now();
		orders = fManager.load(time);
		
		//Take Command Line input!
		Scanner input = new Scanner(System.in);
		String line = "";
		while( !line.equals("end") ) {
			try {
				System.out.println("\nAwaiting Command!: ");
				line = input.nextLine();
				CommandList(line, fManager, rp);
			}catch(Exception ex) {
				System.out.println("Error With Command!");
				System.err.println(ex.getMessage());
			}
		}
		input.close();
		
		System.exit(0);
		
	}
	/*Command List
	 * 
	 * showDataAll          
	 * showDataOne Target
	 * showDataCOD
	 * showDataPaid
	 *  
	 * addOrder Target Price
	 * removeOrder Target Price
	 * removeMiner Target
	 * changeOrder Target toPrice fromPrice
	 * printOrder Target price 
	 * mergeMiners Target(Kept) Target(Removed)
	 * changeMiner Target(To) Target(From) Price
	 * 
	 * setDP Target Price
	 * addDP Target Price
	 * 
	 * toggleCOD Target
	 * togglePaid Target
	 * 
	 * toggleSave
	 * togglePrint
	 * printGrid desiredWidth (Height is calculated based on Width)
	 * sortMinersAlpha(asc(default) /desc)
	 * sortMinersItems Direction(asc/desc (default) )
	 * 
	 * save
	 * load Date(example = 2025-04-02)
	 * loadToday
	 * 
	*/
	public static void CommandList(String line, FileManager fManager, PrinterHandler rp) {
		String test[] = line.split(" ");
		switch(test[0]) {
		
			case "showDataAll":
				orders.showData();
				break;
			case "showDataOne":
				orders.showDataOne(test[1]);
				break;
			case "showDataCOD":
				orders.showDataCOD();
				break;
			case "showDataPaid":
				orders.showDataPaid();
				break;
			
			case "printOrder":
				rp.printData(test[1], Float.parseFloat(test[2]));
				
			case "addOrder":
				orders.addOrder(test[1], Float.parseFloat(test[2]) );
				if(printerModeOn) {
					rp.printData(test[1], Float.parseFloat(test[2]));
				}
				if(autoSave) {
					fManager.save(time, orders);	
				}
				break;
			case "removeOrder":
				orders.removeOneOrder(test[1], Float.parseFloat(test[2]) );
				if(autoSave) {
					fManager.save(time, orders);	
				}
				break;
			case "changeOrder":
				boolean ans = orders.removeOneOrder(test[1], Float.parseFloat(test[3]) );
				if(ans) {
					orders.addOrder(test[1], Float.parseFloat(test[2]));
					if(autoSave) {
						fManager.save(time, orders);	
					}
				}
				break;
			case "removeMiner":
				orders.removeMiner(test[1]);
				if(autoSave) {
					fManager.save(time, orders);	
				}
				break;
				
			case "setDP":
				orders.setDP(test[1], Float.parseFloat(test[2]) );
				if(autoSave) {
					fManager.save(time, orders);	
				}
				break;
			case "addDP":
				orders.addDP(test[1], Float.parseFloat(test[2]) );
				if(autoSave) {
					fManager.save(time, orders);	
				}
				break;
				
			case "toggleCOD":
				orders.toggleCOD(test[1]);
				break;
			case "togglePaid":
				orders.togglePaid(test[1]);
				break;

			case "changeMiner":
				orders.changeMiners(test[1], test[2], Float.parseFloat(test[3]));
				if(autoSave) {
					fManager.save(time, orders);	
				}
				break;
			case "mergeMiners":
				orders.mergeMiners(test[1], test[2]);
				if(autoSave) {
					fManager.save(time, orders);	
				}
				break;
				
			case "togglePrint":
				printerModeOn = !printerModeOn;
				break;
			case "toggleSave":
				autoSave = !autoSave;
				
			case "printGrid":
				orders.printGrid(Integer.parseInt(test[1]));
				break;
			case "sortMinersAlpha":
				if(test[1].equals("desc")) {
					orders.sortMinersAlphabeticlyDesc();
				}else {
					orders.sortMinersAlphabeticlyAsc();
				}
				if(autoSave) {
					fManager.save(time, orders);	
				}
				break;
			case "sortMinersItems":
				if(test[1].equals("asc")) {
					orders.sortMinersBySizeAsc();
				}else {
					orders.sortMinersBySizeDesc();
				}
				if(autoSave) {
					fManager.save(time, orders);	
				}
				break;
				
			case "save":
				fManager.save(time, orders);
				break;
			case "load":
				LocalDate temp1 = LocalDate.parse(test[1]);
				orders = fManager.load(temp1);
				break;
			case "loadToday":
				LocalDate temp2=LocalDate.now();
				orders = fManager.load(temp2);
				break;
				
			default:
				System.out.println("No Such Command!");
				break;
		}
		
	}
}
