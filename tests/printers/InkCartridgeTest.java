package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InkCartridgeTest {
	
	private InkCartridge cartridge;

	@Before
	public void before(){
		cartridge = new InkCartridge(CMYK.MAGENTA);
	}
	
	@Test
	public void canGetColor(){
		assertEquals(CMYK.MAGENTA, cartridge.getColor());
	}
	
	@Test
	public void canSetLevel(){
		cartridge.setLevel(90.0);
		assertEquals(90.0, cartridge.getLevel(), 0.1);
	}
	
	@Test
	public void inkLevelCannotBeSetToHigherLevelThanTheMaxCapacity(){
		assertEquals("The value is too high", cartridge.setLevel(101.0));
	}
}
