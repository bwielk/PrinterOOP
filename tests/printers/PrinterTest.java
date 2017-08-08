package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrinterTest{

	private Printer printer1;
	
	@Before
	public void before(){
		printer1 = new Printer("Dell");
	}
	
	@Test
	public void hasBrand(){
		String brand = printer1.getBrand();
		assertEquals("Dell", brand);
	}
	
}
