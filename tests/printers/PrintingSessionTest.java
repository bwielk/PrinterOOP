package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrintingSessionTest {
	
	private PrintingSession session;
	private PrintingSession session2;
	private PrintingSession session3;
	private String content;
	
	@Before
	public void before(){
		content = "ABCDEFGHIJKLMNOPQRSTUWYXZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWYXZabcdefghijklmnopqrstuvwxyz";
		session = new PrintingSession(content, PrintingMode.GRAYSCALE, PaperSize.A3);
		session2 = new PrintingSession(content, PrintingMode.GRAYSCALE, PaperSize.A4);
		session3 = new PrintingSession(content, PrintingMode.GRAYSCALE, PaperSize.A5);
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
	public void canGenerateNumOfPagesByContent(){
		//System.out.println(session3.getContent().length()%session3.getSize().getCapacity());
		assertEquals(2, session.getPages());
		assertEquals(3, session2.getPages());
		assertEquals(11, session3.getPages());
	}
	
	@Test
	public void canDefineNumOfSheetsForPrinting(){
		assertEquals(1, session.getNumOfSheetsNeeded());
		assertEquals(2, session2.getNumOfSheetsNeeded());
		assertEquals(6, session3.getNumOfSheetsNeeded());
	}
	
	@Test
	public void canDivideContentByPagesCapacity(){
		assertEquals(11, session3.splitContentIntoPages().size());
		assertEquals(3, session2.splitContentIntoPages().size());
		//System.out.println(session.splitContentIntoPages().get(0));
		//assertEquals(2, session.pageSplit().length());
		//assertEquals("yz", session.getContentByPage(2));
	}
}
