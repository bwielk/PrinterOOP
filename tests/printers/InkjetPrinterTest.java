package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InkjetPrinterTest {
	
	private InkjetPrinter printer1;
	private Cartridge cartridge1;
	private Cartridge cartridge2;
	private Cartridge cartridge3;
	private Cartridge cartridge4;
	private Paper sheet1;
	private Paper sheet2;
	
	private void cartridgesIn(double a, double b, double c, double d){
		cartridge1.setLevel(a);
		cartridge2.setLevel(b);
		cartridge3.setLevel(c);
		cartridge4.setLevel(d);
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		printer1.acceptCartridge(cartridge3);
		printer1.acceptCartridge(cartridge4);
	}
	
	@Before
	public void test() {
		printer1 = new InkjetPrinter("HP", "443", 200); 
		cartridge1 = new Cartridge(CMYK.CYAN);
		cartridge2 = new Cartridge(CMYK.MAGENTA);
		cartridge3 = new Cartridge(CMYK.YELLOW);
		cartridge4 = new Cartridge(CMYK.KEY);
		sheet1 = new Paper(PaperType.MATT);
		sheet2 = new Paper(PaperType.LIGHTWEIGHT);
	}
	
	@Test
	public void canAcceptCartridges(){
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		assertEquals(2, printer1.getCartridges().size());
	}
	
	@Test 
	public void doesNOTacceptCartridgesAlreadyIn(){
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		assertEquals("The MAGENTA cartridge is already in", printer1.acceptCartridge(cartridge2));
	}
	
	@Test
	public void checksTheNumberOfCartridgesIn(){
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		assertEquals(2, printer1.numberOfCartridges());
		printer1.acceptCartridge(cartridge3);
		printer1.acceptCartridge(cartridge4);
		assertEquals(4, printer1.numberOfCartridges());
	}
	
	@Test
	public void checksInkLevels(){
		cartridge1.setLevel(30.0);
		cartridge2.setLevel(30.0);
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		assertEquals("Ink levels : \nCYAN: 30.0 % \nMAGENTA: 30.0 % \nYELLOW: n/a \nKEY(BLACK): n/a", printer1.inkReport());
		System.out.println(printer1.inkReport());
	}
	
	@Test
	public void notifiesOnLowLevels(){
		cartridgesIn(10.0, 20.0, 10.0, 20.0);
		assertEquals("ATTENTION! The levels of inks: YELLOW, CYAN, MAGENTA, KEY are low!", printer1.lowLevel());
	}
	
	@Test
	public void notifiesOnLowLevels2(){
		cartridgesIn(10.0, 20.0, 10.0, 30.0);
		assertEquals("ATTENTION! The levels of inks: YELLOW, CYAN, MAGENTA are low!", printer1.lowLevel());
	}
	
	@Test
	public void notifiesOnLowLevels3(){
		cartridgesIn(90.0, 20.0, 90.0, 90.0);
		assertEquals("ATTENTION! The levels of inks: MAGENTA are low!", printer1.lowLevel());
	}
	
	@Test
	public void areCartridgesFull(){
		cartridgesIn(20.0, 34.0, 100.0, 100.0);
		assertEquals(false, printer1.cartridgesFull());
	}
	
	@Test
	public void areCartridgesFull2(){
		cartridgesIn(100.0, 210.0, 30.0, 21.0);
		assertEquals(true, printer1.cartridgesFull());
	}
	
	@Test //if inherits
	public void doesNotPrintIfNoPaper() {
		printer1.addPaper(sheet1);
		printer1.addPaper(sheet2);
		printer1.switchON();
		printer1.printOff();
		printer1.printOff();
		assertEquals("No paper", printer1.printOff());
		assertEquals(2, printer1.getCount());
	}

	@Test //if inherits
	public void doesPrintIfPaperIn() {
		printer1.addPaper(sheet1);
		printer1.addPaper(sheet2);
		printer1.addPaper(sheet1);
		printer1.addPaper(sheet2);
		printer1.switchON();
		printer1.printOff();
		printer1.printOff();
		printer1.printOff();
		assertEquals("A page has been printed off", printer1.printOff());
		assertEquals(4, printer1.getCount());
	}

	@Test //if inherits
	public void doesntPrintOffIfSwitchedOff() {
		printer1.addPaper(sheet1);
		printer1.switchOFF();
		assertEquals("The printer is OFF. Switch it on", printer1.printOff());
	}
	
}
