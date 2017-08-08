package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrinterTest{

	private Printer printer1;
	private Printer printer2;
	
	@Before
	public void before(){
		printer1 = new Printer("Dell", "1234", 100);
		printer2 = new Printer("HP", "x434", 123);
	}
	
	@Test
	public void hasBrand(){
		String brand = printer1.getBrand();
		assertEquals("Dell", brand);
	}
	
	@Test
	public void hasModel(){
		String model = printer1.getModel();
		assertEquals("1234", model);
	}
	
	@Test
	public void hasTotalPagesPrintedCount(){
		int count = printer1.getCount();
		assertEquals(0, count);
	}
	
	@Test 
	public void hasPaperTrayPrinter1(){
		int result = printer1.getPaperTray().getLimit();
		assertEquals(100, result);
	}
	
	@Test 
	public void hasPaperTrayPrinter2(){
		int result = printer2.getPaperTray().getLimit();
		assertEquals(123, result);
	}
	
}
