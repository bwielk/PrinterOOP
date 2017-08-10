package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FrontPageTest{
	
	private Paper sheet1;

	@Before
	public void before() {
		sheet1 = new Paper();
	}
	
	@Test
	public void theFrontPageGetsPrinted() {
		sheet1.getFrontPage().printed();
		assertEquals(true, sheet1.getFrontPage().getStatus());
	}
}
