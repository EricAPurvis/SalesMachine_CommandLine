package main;

import java.time.LocalDate;

import apparatus.Screen;
import apparatus.Window;

public class StartUp {

	public static LocalDate time;
	public static OrderList orders;
	
	public static String sortDir = "asc";
	
	public static FileManager fManager;
	public static PrinterHandler rp;
	
	public static Screen scr;
	
	public static void main(String args[]) {
		
		//Load and Select Printer for use
        rp = new PrinterHandler();
        rp.selectPrinter();
        
        //Get Date
        time = LocalDate.now();
        
        //GUI
		Window win = new Window();
		scr = new Screen();
		win.add(scr);
		scr.validate();
		win.validate();
		
        //Get data structure ready
		orders = new OrderList();
		fManager = new FileManager();
		
		CommandList("load "+time.toString());
		CommandList("showDataAll");
		
	}
	
//Add combining files to show total over a range set of days
	/*Command List
	 * 
	 * showDataAll           --Added
	 * showDataLike Target   --Added
	 * showDataCOD           --Added
	 * showDataPaid          --Added
	 *  
	 * addOrder Target Price            --Added
	 * removeOrder Target Price         --Added
	 * removeMiner Target               --Added
	 * changeOrder Target toPrice fromPrice  --For now manually do it
	 * printOrder Target price                  --Added
	 * mergeMiners Target(Kept) Target(Removed) --Added
	 * changeMiner Target(To) Target(From) Price --For now manually do it
	 * 
	 * setDP Target Price  //Deprecated? halfy, as command
	 * addDP Target Price  //Make these create new Miner if does not exist yet  --Added
	 * 
	 * toggleCOD Target  --Added
	 * togglePaid Target --Added
	 * 
	 * printGrid desiredWidth (Height is calculated based on Width)  --Added
	 * sortMinersAlpha(asc(default) /desc)                           --Added
	 * sortMinersItems Direction(asc/desc (default) )                --Added
	 * 
	 * load Date(example = 2025-04-02) -- Added
	 * 
	 * 
	*/
	public static void CommandList(String line) {
		String test[] = line.split(" ");
		switch(test[0]) {
		
			case "showDataAll":
				orders.showData();
				break;
			case "showDataLike":
				orders.showDataLike(test[1]);
				break;
			case "showDataCOD":
				orders.showDataCOD();
				break;
			case "showDataPaid":
				orders.showDataPaid();
				break;
			
			case "printOrder":
				rp.printData(test[1], Float.parseFloat(test[2]));
				break;
			case "addOrder":
				orders.addOrder(test[1], Float.parseFloat(test[2]) );
				rp.printData(test[1], Float.parseFloat(test[2]));
				fManager.save(time, orders);	
				break;
			case "removeOrder":
				orders.removeOneOrder(test[1], Float.parseFloat(test[2]) );
				fManager.save(time, orders);	
				break;
			case "changeOrder":
				boolean ans = orders.removeOneOrder(test[1], Float.parseFloat(test[3]) );
				if(ans) {
					orders.addOrder(test[1], Float.parseFloat(test[2]));
					fManager.save(time, orders);	
				}
				break;
			case "removeMiner":
				orders.removeMiner(test[1]);
				fManager.save(time, orders);	
				break;
				
			case "setDP":
				orders.setDP(test[1], Float.parseFloat(test[2]) );
				fManager.save(time, orders);	
				break;
			case "addDP":
				orders.addDP(test[1], Float.parseFloat(test[2]) );
				fManager.save(time, orders);	
				break;
				
			case "toggleCOD":
				orders.toggleCOD(test[1]);
				fManager.save(time, orders);	
				break;
			case "togglePaid":
				orders.togglePaid(test[1]);
				fManager.save(time, orders);	
				break;

			case "changeMiner":
				orders.changeMiners(test[1], test[2], Float.parseFloat(test[3]));
				fManager.save(time, orders);	
				break;
			case "mergeMiners":
				orders.mergeMiners(test[1], test[2]);
				fManager.save(time, orders);	
				break;
	
			case "printGrid":
				orders.printGrid(Integer.parseInt(test[1]));
				break;
			case "sortMinersAlpha":
				if(test[1].equals("desc")) {
					orders.sortMinersAlphabeticlyDesc();
				}else {
					orders.sortMinersAlphabeticlyAsc();
				}
				fManager.save(time, orders);	
				break;
			case "sortMinersItems":
				if(test[1].equals("asc")) {
					orders.sortMinersBySizeAsc();
				}else {
					orders.sortMinersBySizeDesc();
				}
				fManager.save(time, orders);	
				break;
				
			case "load":
				LocalDate temp1 = LocalDate.parse(test[1]);
				orders = fManager.load(temp1);
				scr.update();
				break;
				
			default:
				Screen.printSimpleGood("No Such Command!");
				break;
		}
		
	}
}
