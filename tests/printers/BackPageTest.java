package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BackPageTest{
	
	private Paper sheet1;
	
	@Before
	public void before(){
		sheet1 = new Paper(PaperType.MATT);
	}
	
	@Test
	public void theBackPageGetsPrinted() {
		sheet1.getBackPage().printed();
		assertEquals(true, sheet1.getBackPage().getStatus());
	}
}
