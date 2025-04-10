package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.time.LocalDate;
import java.time.LocalDateTime;

import apparatus.Screen;
import loader.ImageGetter;

public class PrinterHandler implements Printable{
	
	private static BufferedImage emblem=ImageGetter.get("res/MajesticStylesEmblem.png");
	private PrinterJob pj;
	private String target;
	private float price;
	public static LocalDateTime time;
	
    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }
        
        time = LocalDateTime.now();
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        //width = 281.62204724409446, height = 139.748031496063
        g2d.setFont(new Font("honeybee", Font.BOLD, 40));
        g.drawString("Majestic-Styles", 20, 30 );
        
        g2d.drawImage(emblem, 0, 40, null);
        
        g2d.setFont(new Font("honeybee", 0, 20));
        g2d.drawString("Miner: ", 105, 70);
        g2d.drawString(target, 107, 90);
        g2d.drawString("Price: ", 105, 115);
        g2d.drawString(""+((int)price), 107, 135);
        
        g2d.drawString(""+time, 105, 165);
        
        g.dispose();
        g2d.dispose();
        
        return PAGE_EXISTS;
    }
 
    public void selectPrinter() {
    	pj = PrinterJob.getPrinterJob();
    	pj.printDialog();
        PageFormat pf = pj.defaultPage();
        pf.setOrientation(PageFormat.LANDSCAPE);
        pj.setPrintable(this, pf);
       // System.out.println(pf.getImageableWidth()+ ", " + pf.getImageableHeight());
    }
    
    public void printData(String target, float price) {
    	this.target=target;
    	this.price=price;
        try {
        	pj.print();
        } catch (Exception ex) {
           // ex.printStackTrace();
        	Screen.printSimpleError("Printer Not Working!");
          //  System.err.println(ex.getMessage());
        }
    }
	
}
