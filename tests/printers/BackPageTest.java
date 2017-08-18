package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BackPageTest{
	
	private Paper sheet1;
	private String content1;
	
	@Before
	public void before(){
		sheet1 = new Paper(PaperType.MATT, PaperSize.A4);
		content1 = "Lorem Ipsum";
	}
	
	@Test
	public void theBackPageGetsPrinted() {
		sheet1.getBackPage().writeContent(content1);
		assertEquals(true, sheet1.getBackPage().getStatus());
	}
	
	@Test
	public void theFrontPageCannotAcceptMoreContentAfterPreviousPrinting(){
		sheet1.getBackPage().writeContent(content1);
		assertEquals("The page is printed", sheet1.getBackPage().writeContent(content1));
		assertEquals(content1, sheet1.getBackPage().getContent());
	}
	
	@Test
	public void thePageIsBlank(){
		assertEquals("The page is blank", sheet1.getBackPage().getContent());
	}
}
