package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CartridgeTest{
	
	private Cartridge cartridge;
	
	@Before
	public void before(){
		cartridge = new Cartridge(CMYK.CYAN);
	}
	
	@Test
	public void aCartridgeHasProperties() {
		assertEquals(CMYK.CYAN, cartridge.getColor());
		assertEquals(100.0, cartridge.getLevel(), 0.1);
	}
}
