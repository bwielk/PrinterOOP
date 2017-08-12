package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrintingSessionTest {
	
	private PrintingSession session;
	private String content;
	
	@Before
	public void before(){
		content = "ABCDEFGHIJKLMNOPQRSTUWYXZabcdefghijklmnopqrstuvwxyz";
		session = new PrintingSession(10, content, PrintingMode.GRAYSCALE);
	}
	
	@Test
	public void canSetAndGetPagesToPrint() {
		assertEquals(10, session.getPages());
		session.setPages(20);
		assertEquals(20, session.getPages());
	}
	
	@Test
	public void containsContent(){
		assertEquals(content, session.getContent());
	}
	
	@Test
	public void canGetPrintingMode(){
		assertEquals(PrintingMode.GRAYSCALE, session.getMode());
	}

}
