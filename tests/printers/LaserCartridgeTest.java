package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LaserCartridgeTest {
	
	private LaserCartridge cartridge;
	
	@Before
	public void before(){
		cartridge = new LaserCartridge(CMYK.CYAN);
	}
	
	@Test
	public void canGetLevel(){
		assertEquals(200.0, cartridge.getLevel(), 0.1);
	}
	
	@Test
	public void canGetColor(){
		assertEquals(CMYK.CYAN, cartridge.getColor());
	}
	
	@Test
	public void canSetLevel(){
		cartridge.setLevel(180.0);
		assertEquals(180.0, cartridge.getLevel(), 0.1);
	}
	
	@Test
	public void cannotSetLevelHigherThanTheMaximumLevelOfTheCartridge(){
		assertEquals("The value is too high", cartridge.setLevel(201.0));
	}
}
