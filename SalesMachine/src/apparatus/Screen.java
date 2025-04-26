package apparatus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.StartUp;

public class Screen extends JPanel{

	//Order Alterations
	JTextField removeMinerTarget;
	JButton removeMiner;
	
	JTextField mergeKeptMiner;
	JTextField mergeRemovedMiner;
	JButton mergeMiners;
	
	JTextField changeMinerTarget;
	JTextField changeMinerToPrice;
	JTextField changeMinerFromPrice;
	JButton changeMiners;
	
	//DP and COD
	JTextField dpTarget;
	JTextField dpAmount;
	JButton addDP;
	
	JTextField toggleTarget;
	JButton toggleCOD;
	JButton togglePaid;
	
	//Exit
	JButton exit;
	
	//Simple Output
	JTextField printTarget;
	JTextField printPrice;
	public JButton printOrder;
	
	public static JTextField simpleOutput;
	
	//Show Datas
	JButton showAll;
	JTextField showTarget;
	JButton showLike;
	JButton showCOD;
	JButton showPaid;
	
	public static JTextArea shownData;
	
	//Save/load
	JButton load;
	JButton prevDate;
	public static JTextField dateShown;
	JButton nextDate;
	
	//Data Entry
	JTextField addOrderTarget;
	JTextField addOrderPrice;
	JButton addOrder;
	JButton removeOrder;

	//Organize and print grid
	JButton printGrid;
	JTextField directionShown;
	JButton Asc;
	JButton Desc;
	JButton sortAlpha;
	JButton sortItems;
	
	public Screen() {
		this.setDoubleBuffered(false);
		this.setLayout(null);
	
	//Simple Output
		 simpleOutput = new JTextField();
		 simpleOutput.setFont(new Font("honeybee", Font.BOLD, 20));
		 simpleOutput.setSize(300, 40);
		 simpleOutput.setLocation(710, 240);
		 simpleOutput.setEditable(false);
		 simpleOutput.setBackground(Color.black);
		 simpleOutput.setForeground(Color.green);
		 simpleOutput.setHorizontalAlignment(SwingConstants.CENTER);
		 
		printTarget = new JTextField();
		printPrice = new JTextField();
		printOrder = new JButton();
		
		printTarget.setSize(300, 40);
		printTarget.setBackground(Color.black);
		printTarget.setForeground(Color.green);
		printTarget.setFont(new Font("honeybee", Font.BOLD, 20));
		printTarget.setLocation(310,0);
		printTarget.setHorizontalAlignment(SwingConstants.CENTER);
		
		printPrice.setSize(100, 40);
		printPrice.setBackground(Color.black);
		printPrice.setForeground(Color.green);
		printPrice.setFont(new Font("honeybee", Font.BOLD, 20));
		printPrice.setLocation(610,0);
		printPrice.setHorizontalAlignment(SwingConstants.CENTER);
		
		printOrder = new JButton();
		printOrder.setText("Print Only");
		printOrder.setSize(150, 40);
		printOrder.setLocation(710, 0);
		printOrder.setFont(new Font("honeybee", Font.BOLD, 25));
		printOrder.setBackground(Color.pink);
		
		printOrder.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){
				try {
					StartUp.CommandList("printOrder "+printTarget.getText()+" "+printPrice.getText());
				}catch(Exception ex) {
					printSimpleError("Print Only Error");
				}
			}        
		}); 
		
	//Load Data with Date Selection
		load = new JButton();
		load.setText("Load Data");
		load.setSize(300, 40);
		load.setLocation(0, 0);
		load.setFont(new Font("honeybee", Font.BOLD, 25));
		load.setBackground(Color.pink);
		
		prevDate = new JButton();
		dateShown = new JTextField();
		nextDate = new JButton();
		
		prevDate.setText("<");
		dateShown.setText(StartUp.time.toString());
		nextDate.setText(">");
		
		prevDate.setSize(50, 40);
		dateShown.setSize(200, 40);
		nextDate.setSize(50, 40);
		
		prevDate.setLocation(0, 40);
		dateShown.setLocation(50, 40);
		nextDate.setLocation(250, 40);
		
		prevDate.setBackground(Color.pink);
		dateShown.setBackground(Color.black);
		dateShown.setForeground(Color.green);
		nextDate.setBackground(Color.pink);
		
		prevDate.setFont(new Font("honeybee", Font.BOLD, 25));
		nextDate.setFont(new Font("honeybee", Font.BOLD, 25));
		dateShown.setFont(new Font("honeybee", Font.BOLD, 25));
		dateShown.setHorizontalAlignment(SwingConstants.CENTER);
		
		prevDate.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){
				StartUp.time = StartUp.time.minusDays(1);
				dateShown.setText(StartUp.time.toString());
				
				resetDataShown();
				StartUp.time = LocalDate.parse(dateShown.getText());
				StartUp.CommandList("load "+StartUp.time.toString());
				printDataShown("Loading: "+StartUp.time.toString()+" Data\n\n");
				StartUp.CommandList("showDataAll");
			}        
		}); 
		nextDate.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				StartUp.time = StartUp.time.plusDays(1);
			 	dateShown.setText(StartUp.time.toString());
			 	
				resetDataShown();
				StartUp.time = LocalDate.parse(dateShown.getText());
				StartUp.CommandList("load "+StartUp.time.toString());
				printDataShown("Loading: "+StartUp.time.toString()+" Data\n\n");
				StartUp.CommandList("showDataAll");
			}        
		}); 
		load.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){
				resetDataShown();
				StartUp.time = LocalDate.parse(dateShown.getText());
				StartUp.CommandList("load "+StartUp.time.toString());
				printDataShown("Loading: "+StartUp.time.toString()+" Data\n\n");
				StartUp.CommandList("showDataAll");
			}        
		});
		
		//Show Data
		shownData = new JTextArea();
		shownData.setEditable(false);
		shownData.setFont(new Font("honeybee", Font.BOLD, 30));
		shownData.setForeground(Color.green);
		shownData.setBackground(Color.black);
		JScrollPane scrollingPane = new JScrollPane( shownData, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		scrollingPane.setSize(1920, 800);
		scrollingPane.setLocation(0, 1080-800);
		 
		showAll = new JButton();
		showTarget = new JTextField();
		showLike = new JButton();
		showCOD = new JButton();
		showPaid = new JButton();
		
		showAll.setText("Show All");
		showAll.setSize(150, 40);
		showAll.setLocation(1920-150, 240);
		showAll.setFont(new Font("honeybee", Font.BOLD, 25));
		showAll.setBackground(Color.pink);
		
		showPaid.setText("Show Paid");
		showPaid.setSize(150, 40);
		showPaid.setLocation(1920-300, 240);
		showPaid.setFont(new Font("honeybee", Font.BOLD, 25));
		showPaid.setBackground(Color.pink);
		
		showCOD.setText("Show COD");
		showCOD.setSize(150, 40);
		showCOD.setLocation(1920-450, 240);
		showCOD.setFont(new Font("honeybee", Font.BOLD, 25));
		showCOD.setBackground(Color.pink);
		
		showLike.setText("Show Like");
		showLike.setSize(150, 40);
		showLike.setLocation(1920-600, 240);
		showLike.setFont(new Font("honeybee", Font.BOLD, 25));
		showLike.setBackground(Color.pink);
		
		showTarget.setText("");
		showTarget.setSize(300, 40);
		showTarget.setLocation(1920-900, 240);
		showTarget.setFont(new Font("honeybee", Font.BOLD, 20));
		showTarget.setHorizontalAlignment(SwingConstants.CENTER);
		showTarget.setForeground(Color.green);
		showTarget.setBackground(Color.black);
		
		showLike.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){
				try {
					resetDataShown();
					StartUp.CommandList("showDataLike "+showTarget.getText());
				}catch(Exception ex) {
					printSimpleError("Show DataOne Error");
				}
			}        
		}); 	
		showAll.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){
				try {
					resetDataShown();
					StartUp.CommandList("showDataAll");
				}catch(Exception ex) {
					printSimpleError("Show Data Error");
				}
			}        
		}); 	
		showCOD.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){
				try {
					resetDataShown();
					StartUp.CommandList("showDataCOD");
				}catch(Exception ex) {
					printSimpleError("Show COD Error");
				}
			}        
		}); 	
		showPaid.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){
				try {
					resetDataShown();
					StartUp.CommandList("showDataPaid");
				}catch(Exception ex) {
					printSimpleError("Show Paid Error");
				}
			}        
		}); 

		//Add Data
		addOrderTarget = new JTextField();
		addOrderPrice = new JTextField();
		addOrder = new JButton();
		removeOrder = new JButton();
		
		addOrderTarget.setSize(300, 40);
		addOrderTarget.setLocation(0, 240);
		addOrderTarget.setFont(new Font("honeybee", Font.BOLD, 20));
		addOrderTarget.setHorizontalAlignment(SwingConstants.CENTER);
		addOrderTarget.setBackground(Color.black);
		addOrderTarget.setForeground(Color.green);
		
		addOrderPrice.setSize(100, 40);
		addOrderPrice.setLocation(300, 240);
		addOrderPrice.setFont(new Font("honeybee", Font.BOLD, 20));
		addOrderPrice.setHorizontalAlignment(SwingConstants.CENTER);
		addOrderPrice.setBackground(Color.black);
		addOrderPrice.setForeground(Color.green);
		
		addOrder.setText("Add Order");
		addOrder.setSize(150, 40);
		addOrder.setLocation(400, 240);
		addOrder.setFont(new Font("honeybee", Font.BOLD, 20));
		addOrder.setBackground(Color.pink);
		
		removeOrder.setText("Remove Order");
		removeOrder.setSize(150, 40);
		removeOrder.setLocation(550, 240);
		removeOrder.setFont(new Font("honeybee", Font.BOLD, 18));
		removeOrder.setBackground(Color.pink);
		
		addOrder.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){
				try {
					StartUp.CommandList("addOrder "+addOrderTarget.getText()+" "+addOrderPrice.getText());
				}catch(Exception ex) {
					printSimpleError("Adding Order Error");
				}
				addOrderTarget.setText("");
				addOrderPrice.setText("");
			}        
		}); 	
		removeOrder.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				try {
					StartUp.CommandList("removeOrder "+addOrderTarget.getText()+" "+addOrderPrice.getText());
				}catch(Exception ex) {
					printSimpleError("Removing Order Error");
				}
				addOrderTarget.setText("");
				addOrderPrice.setText("");
			}        
		}); 
		
		//Organize and print grid
		printGrid = new JButton();
		directionShown = new JTextField();
		Asc = new JButton();
		Desc = new JButton();
		sortAlpha = new JButton();
		sortItems = new JButton();
		
		directionShown.setText(StartUp.sortDir);
		directionShown.setHorizontalAlignment(SwingConstants.CENTER);
		directionShown.setBackground(Color.black);
		directionShown.setForeground(Color.green);
		directionShown.setSize(150, 40);
		directionShown.setLocation(1920-450,150);
		directionShown.setEditable(false);
		directionShown.setFont(new Font("honeybee", Font.BOLD, 25));
		
		printGrid.setText("Print Grid");
		printGrid.setSize(150, 40);
		printGrid.setLocation(1920-450, 190);
		printGrid.setBackground(Color.pink);
		printGrid.setFont(new Font("honeybee", Font.BOLD, 25));
		
		sortAlpha.setText("Sort Alpha");
		sortAlpha.setSize(150, 40);
		sortAlpha.setLocation(1920-300, 190);
		sortAlpha.setBackground(Color.pink);
		sortAlpha.setFont(new Font("honeybee", Font.BOLD, 25));
		
		sortItems.setText("Sort Items");
		sortItems.setSize(150, 40);
		sortItems.setLocation(1920-150, 190);
		sortItems.setBackground(Color.pink);
		sortItems.setFont(new Font("honeybee", Font.BOLD, 25));
		
		Desc.setText("Desc");
		Desc.setSize(150, 40);
		Desc.setLocation(1920-150, 150);
		Desc.setBackground(Color.pink);
		Desc.setFont(new Font("honeybee", Font.BOLD, 25));
		
		Asc.setText("Asc");
		Asc.setSize(150, 40);
		Asc.setLocation(1920-300, 150);
		Asc.setBackground(Color.pink);
		Asc.setFont(new Font("honeybee", Font.BOLD, 25));
		
		sortAlpha.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				try {
					resetDataShown();
					StartUp.CommandList("sortMinersAlpha "+StartUp.sortDir);
					StartUp.CommandList("showDataAll");
				}catch(Exception ex) {
					printSimpleError("Sorting Alpha Error");
				}
			}        
		});
		
		sortItems.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				try {
					resetDataShown();
					StartUp.CommandList("sortMinersItems "+StartUp.sortDir);
					StartUp.CommandList("showDataAll");
				}catch(Exception ex) {
					printSimpleError("Sorting Items Error");
				}
			}        
		});
		
		printGrid.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				try {
					resetDataShown(28);
					StartUp.CommandList("printGrid 7");
				}catch(Exception ex) {
					printSimpleError("Printing Grid Error");
				}
			}        
		});
		
		Asc.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				StartUp.sortDir="asc";
				directionShown.setText(StartUp.sortDir);
			}        
		}); 
		
		Desc.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				StartUp.sortDir="desc";
				directionShown.setText(StartUp.sortDir);
			}        
		}); 
		
		//Exit
		exit = new JButton();
		exit.setSize(50,50);
		exit.setLocation(1920-50, 0);
		exit.setFont(new Font("honeybee", Font.BOLD, 27));
		exit.setText("X");
		exit.setBackground(Color.pink);
		
		exit.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				System.exit(0);
			}        
		}); 
		
		//Order Alterations		
		removeMinerTarget = new JTextField();
		removeMiner = new JButton();
		
		removeMinerTarget.setSize(300, 40);
		removeMinerTarget.setLocation(920,50);
		removeMinerTarget.setHorizontalAlignment(SwingConstants.CENTER);
		removeMinerTarget.setFont(new Font("honeybee", Font.BOLD, 20));
		removeMinerTarget.setBackground(Color.black);
		removeMinerTarget.setForeground(Color.green);
		
		removeMiner.setText("Remove Miner");
		removeMiner.setSize(200, 40);
		removeMiner.setLocation(1220,50);
		removeMiner.setFont(new Font("honeybee", Font.BOLD, 25));
		removeMiner.setBackground(Color.pink);
		
		mergeKeptMiner=new JTextField();
		mergeRemovedMiner = new JTextField();
		mergeMiners = new JButton();
		
		mergeKeptMiner.setSize(300, 40);
		mergeKeptMiner.setLocation(0,100);
		mergeKeptMiner.setHorizontalAlignment(SwingConstants.CENTER);
		mergeKeptMiner.setFont(new Font("honeybee", Font.BOLD, 20));
		mergeKeptMiner.setBackground(Color.black);
		mergeKeptMiner.setForeground(Color.green);
		
		mergeRemovedMiner.setSize(300, 40);
		mergeRemovedMiner.setLocation(300,100);
		mergeRemovedMiner.setHorizontalAlignment(SwingConstants.CENTER);
		mergeRemovedMiner.setFont(new Font("honeybee", Font.BOLD, 20));
		mergeRemovedMiner.setBackground(Color.black);
		mergeRemovedMiner.setForeground(Color.green);
		
		mergeMiners.setText("Merge Miners");
		mergeMiners.setSize(200, 40);
		mergeMiners.setLocation(600,100);
		mergeMiners.setFont(new Font("honeybee", Font.BOLD, 25));
		mergeMiners.setBackground(Color.pink);

		removeMiner.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				try {
					StartUp.CommandList("removeMiner "+removeMinerTarget.getText());
					removeMinerTarget.setText("");
				}catch(Exception ex) {
					printSimpleError("Removing Miner Error");
				}
			}        
		});
		
		mergeMiners.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				try {
					StartUp.CommandList("mergeMiners "+mergeKeptMiner.getText()+" "+mergeRemovedMiner.getText());
					mergeKeptMiner.setText("");
					mergeRemovedMiner.setText("");
				}catch(Exception ex) {
					printSimpleError("Merging Miner Error");
				}
			}        
		});
		
		//DP and COD
		dpTarget = new JTextField();
		dpAmount = new JTextField();
		addDP = new JButton();
		
		dpTarget.setSize(300, 40);
		dpTarget.setLocation(870, 0);
		dpTarget.setBackground(Color.black);
		dpTarget.setForeground(Color.green);
		dpTarget.setFont(new Font("honeybee", Font.BOLD, 20));
		dpTarget.setHorizontalAlignment(SwingConstants.CENTER);
		
		dpAmount.setSize(100, 40);
		dpAmount.setLocation(1170, 0);
		dpAmount.setHorizontalAlignment(SwingConstants.CENTER);
		dpAmount.setBackground(Color.black);
		dpAmount.setForeground(Color.green);
		dpAmount.setFont(new Font("honeybee", Font.BOLD, 20));
		
		addDP.setText("Add DP");
		addDP.setSize(150, 40);
		addDP.setLocation(1270, 0);
		addDP.setBackground(Color.pink);
		addDP.setFont(new Font("honeybee", Font.BOLD, 20));
		
		toggleTarget = new JTextField();
		toggleCOD = new JButton();
		togglePaid = new JButton();
		
		toggleTarget.setSize(300, 40);
		toggleTarget.setLocation(310, 50);
		toggleTarget.setHorizontalAlignment(SwingConstants.CENTER);
		toggleTarget.setBackground(Color.black);
		toggleTarget.setForeground(Color.green);
		toggleTarget.setFont(new Font("honeybee", Font.BOLD, 20));
		
		toggleCOD.setText("Toggle COD");
		toggleCOD.setSize(150, 40);
		toggleCOD.setLocation(610, 50);
		toggleCOD.setBackground(Color.pink);
		toggleCOD.setFont(new Font("honeybee", Font.BOLD, 20));
		
		togglePaid.setText("Toggle Paid");
		togglePaid.setSize(150, 40);
		togglePaid.setLocation(760, 50);
		togglePaid.setBackground(Color.pink);
		togglePaid.setFont(new Font("honeybee", Font.BOLD, 20));
		
		addDP.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				try {
					StartUp.CommandList("addDP "+dpTarget.getText()+" "+dpAmount.getText());
					dpTarget.setText("");
					dpAmount.setText("");
				}catch(Exception ex) {
					printSimpleError("Adding DP Error");
				}
			}        
		});
		
		toggleCOD.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				try {
					StartUp.CommandList("toggleCOD "+toggleTarget.getText());
					toggleTarget.setText("");
				}catch(Exception ex) {
					printSimpleError("Toggle COD Error");
				}
			}        
		});
		
		togglePaid.addActionListener(new ActionListener(){            
			@Override            
			public void actionPerformed(ActionEvent e){                
				try {
					StartUp.CommandList("togglePaid "+toggleTarget.getText());
					toggleTarget.setText("");
				}catch(Exception ex) {
					printSimpleError("Toggle Paid Error");
				}
			}        
		});
		
		//Order Alterations	
		this.add(removeMiner);
		this.add(removeMinerTarget);
		
		this.add(mergeKeptMiner);
		this.add(mergeRemovedMiner);
		this.add(mergeMiners);
		
		//Add the UI
		this.add(dpTarget);
		this.add(dpAmount);
		this.add(addDP);
		this.add(toggleTarget);
		this.add(toggleCOD);
		this.add(togglePaid);
		
		this.add(printGrid);
		this.add(directionShown);
		this.add(Asc);
		this.add(Desc);
		this.add(sortAlpha);
		this.add(sortItems);
		
		this.add(load);
		this.add(prevDate);
		this.add(dateShown);
		this.add(nextDate);
		this.add(scrollingPane);
		
		this.add(exit);
		this.add(simpleOutput);
		
		this.add(showAll);
		this.add(showTarget);
		this.add(showLike);
		this.add(showCOD);
		this.add(showPaid);
		
		this.add(addOrderTarget);
		this.add(addOrderPrice);
		this.add(addOrder);
		this.add(removeOrder);
		
		this.add(printTarget);
		this.add(printPrice);
		this.add(printOrder);
		
	}

	//Class Methods
	@Override
	public void paintComponent(Graphics g) {	
		g.setColor(new Color(0, 100, 0));
		g.fillRect(0, 0, 1920, 1080);
	}
	
	public void update() {
		dateShown.setText(StartUp.time.toString());
		repaint();
	}
	
	
	//Print Data Output
	public static void printDataShown(String text) {
		shownData.setText(shownData.getText()+text);
	}
	
	public static void resetDataShown() {
		shownData.setText("");
		shownData.setFont(new Font("honeybee", Font.BOLD, 30));
	}
	
	public static void resetDataShown(int size) {
		shownData.setText("");
		shownData.setFont(new Font("honeybee", Font.BOLD, size));
	}
	
	
	//Print Simple Errors
	public static void printSimpleGood(String text) {
		simpleOutput.setForeground(Color.green);
		simpleOutput.setText(text);
	}
	
	public static void printSimpleError(String text) {
		simpleOutput.setForeground(Color.red);
		simpleOutput.setText(text);
	}
	
}
