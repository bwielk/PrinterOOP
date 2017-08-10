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
	
	@Before
	public void test() {
		printer1 = new InkjetPrinter("HP", "443", 200); 
		cartridge1 = new Cartridge(CMYK.CYAN);
		cartridge2 = new Cartridge(CMYK.MAGENTA);
		cartridge3 = new Cartridge(CMYK.YELLOW);
		cartridge4 = new Cartridge(CMYK.KEY);
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
		cartridge1.setLevel(20.0);
		cartridge2.setLevel(30.0);
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		assertEquals("Ink levels : CYAN: 20.0 MAGENTA: 30.0 YELLOW: n/a KEY: n/a", printer1.inkReport());
	}
}
