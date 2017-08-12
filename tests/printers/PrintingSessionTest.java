package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrintingSessionTest {
	
	private PrintingSession session;
	private PrintingSession session2;
	private PrintingSession session3;
	private PrintingSession session4;
	private String content;
	
	@Before
	public void before(){
		content = "ABCDEFGHIJKLMNOPQRSTUWYXZabcdefghijklmnopqrstuvwxyz";
		session = new PrintingSession(11, content, PrintingMode.GRAYSCALE);
		session2 = new PrintingSession(12, content, PrintingMode.GRAYSCALE);
		session3 = new PrintingSession(57, content, PrintingMode.GRAYSCALE);
		session4 = new PrintingSession(101, content, PrintingMode.GRAYSCALE);
	}
	
	@Test
	public void canSetAndGetPagesToPrint() {
		assertEquals(11, session.getPages());
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
	
	@Test
	public void canDefineNumOfPagesForPrinting(){
		assertEquals(6, session.getNumOfSheetsNeeded());
		assertEquals(6, session2.getNumOfSheetsNeeded());
		assertEquals(29, session3.getNumOfSheetsNeeded());
		assertEquals(51, session4.getNumOfSheetsNeeded());
	}

}
