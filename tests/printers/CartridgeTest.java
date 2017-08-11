package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CartridgeTest{
	
	private InkCartridge cartridge;
	
	@Before
	public void before(){
		cartridge = new InkCartridge(CMYK.CYAN);
	}
	
	@Test
	public void aCartridgeHasProperties() {
		assertEquals(CMYK.CYAN, cartridge.getColor());
		assertEquals(100.0, cartridge.getLevel(), 0.1);
	}
}
