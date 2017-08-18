package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PaperTest{

	private Printer printer1;
	private Paper sheet1;
	private Paper sheet2;
	private String content1;
	private String content2;

	@Before
	public void before() {
		printer1 = new Printer("Toshiba", "0008", 100);
		printer1.switchON();
		sheet1 = new Paper(PaperType.MATT, PaperSize.A4);
		sheet2 = new Paper(PaperType.CAST, PaperSize.A4);
		content1 = "ABCDEFG11";
		content2 = "JKLMNOPdd";
	}

	@Test
	public void paperCanBeAddedToPrinter() {
		printer1.addPaper(sheet1);
		printer1.addPaper(sheet2);
		int result = printer1.paperInTheTray();
		assertEquals(2, result);
	}

	@Test
	public void paperCannotBeAddedToPrinter() {
		for (int i = 0; i < 100; i++) {
			Paper paper = new Paper(PaperType.MATT, PaperSize.A4);
			printer1.addPaper(paper);
		}
		Paper paper101 = new Paper(PaperType.CAST, PaperSize.A4);
		System.out.println(printer1.getPaperTray().paperInTheTray());
		String notification = printer1.getPaperTray().addPaper(paper101);
		System.out.println(printer1.getPaperTray().paperInTheTray()); // still displays 100(maximum)
		assertEquals("Cannot be added. The tray is full", notification);
	}
	
	@Test
	public void canGetPaperAbsorptionRate(){
		assertEquals(4, sheet1.getAbsorption());
		assertEquals(2, sheet2.getAbsorption());
	}
	
	@Test
	public void canReceiveContent(){
		sheet1.writeContentFront(content1);
		sheet1.writeContentBack(content2);
		assertEquals(content1, sheet1.getContentFront());
		assertEquals(content2, sheet1.getContentBack());
	}
}
