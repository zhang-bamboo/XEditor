package printer;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class MyPrinter {
    
    private final PrinterJob job=PrinterJob.getPrinterJob();
    private String printStr;
    private String jobName="print";
    private class PrintPage implements Printable{
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
	    int x=(int)pageFormat.getImageableX();
	    int y=(int)pageFormat.getImageableY();
	    graphics.drawString(printStr, x, y);
	    return Printable.NO_SUCH_PAGE;
	}
    }
    public MyPrinter(String printString) {
	printStr=printString;
	    
    }
    public void printString() throws PrinterException{
	if(!job.printDialog())
	    return;
	job.setPrintable(new PrintPage());
	job.setJobName(jobName);
	job.print();
    }

}
