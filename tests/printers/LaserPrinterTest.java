package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LaserPrinterTest{
	
	private LaserPrinter printer1;
	private LaserCartridge cartridge1;
	private LaserCartridge cartridge2;
	private LaserCartridge cartridge3;
	private LaserCartridge cartridge4;
	
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
	public void before(){
		printer1 = new LaserPrinter("Dell", "hdha", 200);
		cartridge1 = new LaserCartridge(CMYK.CYAN);
		cartridge2 = new LaserCartridge(CMYK.MAGENTA);
		cartridge3 = new LaserCartridge(CMYK.YELLOW);
		cartridge4 = new LaserCartridge(CMYK.KEY);
	}
	
	@Test
	public void canGetCartridges(){
		printer1.acceptCartridge(cartridge1);
		assertEquals(1, printer1.getCartridges().size());
	}
	
	@Test
	public void canAccept4Cartridges(){
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		printer1.acceptCartridge(cartridge3);
		printer1.acceptCartridge(cartridge4);
		assertEquals(4, printer1.getCartridges().size());
	}
	
	@Test
	public void cannotAcceptTheSameCartridges(){
		printer1.acceptCartridge(cartridge3);
		assertEquals("The YELLOW cartridge is already in",printer1.acceptCartridge(cartridge3));
	}
	
	@Test
	public void checksTonerLevels(){
		cartridgesIn(80.0, 100.00, 140.0, 160.0);
		assertEquals("Toner levels : \nCYAN: 40.0 % \nMAGENTA: 50.0 % \nYELLOW: 70.0 % \nKEY(BLACK): 80.0 %", printer1.tonerReport());
		//System.out.println(printer1.tonerReport());
	}
	
	@Test
	public void checksTonerLevels2(){
		cartridge1.setLevel(140.0);
		cartridge2.setLevel(60.0);
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		assertEquals("Toner levels : \nCYAN: 70.0 % \nMAGENTA: 30.0 % \nYELLOW: n/a \nKEY(BLACK): n/a", printer1.tonerReport());
		System.out.println(printer1.tonerReport());
	}
	
	@Test
	public void checksTonerLowLevels(){
		cartridgesIn(100.0, 120.00, 20.0, 20.0);
		assertEquals("ATTENTION! The levels of toners: YELLOW, KEY are low!", printer1.lowLevel());
		System.out.println(printer1.lowLevel());
	}
	
}
