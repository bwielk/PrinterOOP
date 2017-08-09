package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PaperTrayTest{

	private Printer printer1;
	private PaperTray paperTray;
	private Paper sheet1;
	private Paper sheet2;
	private Paper sheet3;
	private Paper sheet4;
	
	@Before
	public void before(){
		printer1 = new Printer("Toshiba", "0008", 100);
		printer1.switchON();
		paperTray = new PaperTray(100);
		sheet1 = new Paper();
		sheet2 = new Paper();
		sheet3 = new Paper();
		sheet4 = new Paper();
	}
	
	@Test
	public void paperTrayHasPaperLimit(){
		int limit = paperTray.getLimit();
		assertEquals(100, limit);
	}
	
	@Test
	public void paperTrayHasCanHoldPaper(){
		printer1.addPaper(sheet1);
		printer1.addPaper(sheet2);
		printer1.addPaper(sheet3);
		printer1.addPaper(sheet4);
		int paperAmount = printer1.getPaperTray().paperInTheTray();
		assertEquals(4, paperAmount);
	};
};
