package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PaperTest extends Paper {
	
	private Printer printer1;
	private Paper sheet1;
	private Paper sheet2;
	
	
	@Before
	public void before(){
		printer1 = new Printer("Toshiba", "0008", 100);
		printer1.switchON();
		sheet1 = new Paper();
		sheet2 = new Paper();
	}
	
	@Test
	public void paperCanBeAddedToPrinter(){
		printer1.addPaper(sheet1);
		printer1.addPaper(sheet2);
		int result = printer1.paperInTheTray();
		assertEquals(2, result);
	}
	
	@Test
	public void paperCannotBeAddedToPrinter(){
		for(int i=0; i<100; i++){
			Paper paper = new Paper();
			printer1.addPaper(paper);
		}
		Paper paper101 = new Paper();
		System.out.println(printer1.getPaperTray().paperInTheTray());
		String notification = printer1.getPaperTray().addPaper(paper101);
		System.out.println(printer1.getPaperTray().paperInTheTray()); //still displays 100(maximum)
		assertEquals("Cannot be added. The tray is full", notification);
	}
}
