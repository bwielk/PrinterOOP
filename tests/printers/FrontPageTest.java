package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FrontPageTest{
	
	private Paper sheet1;
	private String content;

	@Before
	public void before() {
		sheet1 = new Paper(PaperType.MATT);
		content = "SHDHDSBSBDJB";
	}
	
	@Test
	public void theFrontPageGetsPrinted() {
		sheet1.getFrontPage().writeContent(content);
		assertEquals(true, sheet1.getFrontPage().getStatus());
	}
	
	@Test
	public void theFrontPageCannotAcceptMoreContentAfterPreviousPrinting(){
		sheet1.getFrontPage().writeContent(content);
		assertEquals("The page is printed", sheet1.getFrontPage().writeContent(content));
		assertEquals(content, sheet1.getFrontPage().getContent());
	}
	
	@Test
	public void thePageIsBlank(){
		assertEquals("The page is blank", sheet1.getFrontPage().getContent());
	}
}
