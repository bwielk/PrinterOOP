package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PaperTrayTest{

	private Printer printer1;
	private PaperTray paperTray;
	
	@Before
	public void before(){
		printer1 = new Printer("Toshiba", "0008", 100);
		paperTray = new PaperTray(100);
	}
	
	@Test
	public void paperTrayHasPaperLimit(){
		int limit = paperTray.getLimit();
		assertEquals(100, limit);
	}
}
