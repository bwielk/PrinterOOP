package printers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class InkjetPrinterTest {
	
	private InkjetPrinter printer1;
	private InkCartridge cartridge1;
	private InkCartridge cartridge2;
	private InkCartridge cartridge3;
	private InkCartridge cartridge4;
	private Paper sheet1;
	private Paper sheet2;
	private PrintingSession session;
	private PrintingSession session2;
	private PrintingSession session3;
	private PrintingSession session4;
	private PrintingSession session5;
	private String content;
	private String content2;
	private String content3;
	
	private void cartridgesIn(double a, double b, double c, double d){
		cartridge1.setLevel(a);
		cartridge2.setLevel(b);
		cartridge3.setLevel(c);
		cartridge4.setLevel(d);
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		printer1.acceptCartridge(cartridge3);
		printer1.acceptCartridge(cartridge4);
		
	}
	
	@Before
	public void test() {
		content = "";
		for(int i=0; i<20; i++){
			String pieceOfText = "Sjdnfjdnbfjdsbfkdbsksjabdbdkdafbfvvsdfd";//780 chars in total
			content += pieceOfText;
		}
		
		content2 = "QWERTQWERTPLKOIPLKOI";//20 chars
		content3 = "QWERTQWERTPLKOIPLKOIQWERTQWERTPLKOIPLKOIQWERTQWERTPLKOIPLKOI";//60 chars
		printer1 = new InkjetPrinter("HP", "443", 200); 
		cartridge1 = new InkCartridge(CMYK.CYAN);
		cartridge2 = new InkCartridge(CMYK.MAGENTA);
		cartridge3 = new InkCartridge(CMYK.YELLOW);
		cartridge4 = new InkCartridge(CMYK.KEY);
		sheet1 = new Paper(PaperType.MATT, PaperSize.A4);
		sheet2 = new Paper(PaperType.LIGHTWEIGHT, PaperSize.A5);
		session = new PrintingSession(content, PrintingMode.GRAYSCALE, PaperSize.A4, true, Resolution.HIGH);//8 sheets
		session2 = new PrintingSession(content2, PrintingMode.GRAYSCALE, PaperSize.A5, false, Resolution.LOW);//2 sheets
		session3 = new PrintingSession(content3, PrintingMode.COLOUR, PaperSize.A4, true, Resolution.MEDIUM);//2 sheets
		session4 = new PrintingSession(content3, PrintingMode.GRAYSCALE, PaperSize.A5, false, Resolution.LOW);
		session5 = new PrintingSession(content, PrintingMode.GRAYSCALE, PaperSize.A3, true, Resolution.LOW);
	}
	
	@Test
	public void canAcceptCartridges(){
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		assertEquals(2, printer1.getCartridges().size());
	}
	
	@Test 
	public void doesNOTacceptCartridgesAlreadyIn(){
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		assertEquals("The MAGENTA cartridge is already in", printer1.acceptCartridge(cartridge2));
	}
	
	@Test
	public void checksTheNumberOfCartridgesIn(){
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		assertEquals(2, printer1.numberOfCartridges());
		printer1.acceptCartridge(cartridge3);
		printer1.acceptCartridge(cartridge4);
		assertEquals(4, printer1.numberOfCartridges());
	}
	
	@Test
	public void checksInkLevels(){
		cartridge1.setLevel(300.0);
		cartridge2.setLevel(300.0);
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		assertEquals("Ink levels : \nCYAN: 30.0 % \nMAGENTA: 30.0 % \nYELLOW: n/a \nKEY(BLACK): n/a", printer1.inkReport());
	}
	
	@Test
	public void notifiesOnLowLevels(){
		cartridgesIn(100.0, 20.0, 100.0, 200.0);
		String result = printer1.lowLevel();
		assertEquals(true, result.contains("YELLOW"));
		assertEquals(true, result.contains("CYAN"));
		assertEquals(true, result.contains("MAGENTA"));
		assertEquals(true, result.contains("KEY"));
	}
	
	@Test
	public void notifiesOnLowLevels2(){
		cartridgesIn(100.0, 200.0, 100.0, 300.0);
		String result = printer1.lowLevel();
		assertEquals(true, result.contains("YELLOW"));
		assertEquals(true, result.contains("CYAN"));
		assertEquals(true, result.contains("MAGENTA"));
		assertEquals(false, result.contains("KEY"));
	}
	
	@Test
	public void notifiesOnLowLevels3(){
		cartridgesIn(900.0, 200.0, 900.0, 900.0);
		assertEquals("ATTENTION! The levels of inks: MAGENTA are low!", printer1.lowLevel());
	}
	
	@Test
	public void areCartridgesFull(){
		cartridgesIn(200.0, 340.0, 1000.0, 1000.0);
		assertEquals(false, printer1.cartridgesEnoughInk());
	}
	
	@Test
	public void areCartridgesFull2(){
		cartridgesIn(1000.0, 210.0, 300.0, 210.0);
		assertEquals(true, printer1.cartridgesEnoughInk());
	}
	
	@Test //if inherits
	public void doesNotPrintIfNoPaper() {
		printer1.addPaper(sheet1);
		printer1.addPaper(sheet2);
		printer1.switchON();
		printer1.printOff();
		printer1.printOff();
		assertEquals("No paper", printer1.printOff());
		assertEquals(2, printer1.getCount());
	}

	@Test //if inherits
	public void doesPrintIfPaperIn() {
		printer1.addPaper(sheet1);
		printer1.addPaper(sheet2);
		printer1.addPaper(sheet1);
		printer1.addPaper(sheet2);
		printer1.switchON();
		printer1.printOff();
		printer1.printOff();
		printer1.printOff();
		assertEquals("A page has been printed off", printer1.printOff());
		assertEquals(4, printer1.getCount());
	}

	@Test //if inherits
	public void doesntPrintOffIfSwitchedOff() {
		printer1.addPaper(sheet1);
		printer1.switchOFF();
		assertEquals("The printer is OFF. Switch it on", printer1.printOff());
	}
	
	@Test
	public void canRememberTheLastPrintedFile(){
		for(int i=0; i<30; i++){
			printer1.addPaper(sheet1);
		}
		printer1.switchON();
		printer1.printOff(session);
		assertEquals(16, printer1.getLastFile().getPages());
	}
	
	@Test
	public void canCountTheNumberOfChars(){
		assertEquals(780, content.length());
	}
	
	@Test
	public void cannotPrintIfNotEnoughPaperSizeA4PrintsIfPaperAdded(){
		cartridgesIn(1000.0, 1000.0, 1000.0, 1000.0);
		for(int i=0; i<7; i++){
			printer1.addPaper(new Paper(PaperType.MATT, PaperSize.A4));
		}
		printer1.switchON();
		assertEquals("Not enough paper", printer1.printOff(session));
		printer1.addPaper(new Paper(PaperType.MATT, PaperSize.A4));
		assertEquals("The process is complete", printer1.printOff(session));	
		assertEquals(0, printer1.paperInTheTray()); //PRINTING PROCESS
		assertEquals("SjdnfjdnbfjdsbfkdbsksjabdbdkdafbfvvsdfdSjdnfjdnbfj", printer1.getOutput().get(0).getContentFront());
		assertEquals("dsbfkdbsksjabdbdkdafbfvvsdfdSjdnfjdnbfjdsbfkdbsksj", printer1.getOutput().get(0).getContentBack());
		assertEquals("abdbdkdafbfvvsdfdSjdnfjdnbfjdsbfkdbsksjabdbdkdafbf", printer1.getOutput().get(1).getContentFront());
		assertEquals("vvsdfdSjdnfjdnbfjdsbfkdbsksjabdbdkdafbfvvsdfdSjdnf", printer1.getOutput().get(1).getContentBack());
	}
	
	@Test
	public void canPrintOffIfEnoughPaperSizeA4p1(){
		cartridgesIn(1000.0, 1000.0, 1000.0, 1000.0);
		for(int i=0; i<20; i++){
			printer1.addPaper(new Paper(PaperType.MATT, PaperSize.A4));
		}
		printer1.switchON();
		assertEquals("The process is complete", printer1.printOff(session3));
		assertEquals(19, printer1.paperInTheTray());
		assertEquals("QWERTQWERTPLKOIPLKOIQWERTQWERTPLKOIPLKOIQWERTQWERT", printer1.getOutput().get(0).getContentFront());
		assertEquals("PLKOIPLKOI", printer1.getOutput().get(0).getContentBack());
		assertEquals("The process is complete", printer1.printOff(session3));
		assertEquals(18, printer1.paperInTheTray());
		assertEquals("QWERTQWERTPLKOIPLKOIQWERTQWERTPLKOIPLKOIQWERTQWERT", printer1.getOutput().get(1).getContentFront());
		assertEquals("PLKOIPLKOI", printer1.getOutput().get(1).getContentBack());
	}
	
	@Test
	public void canPrintOffIfEnoughPaperSizeA4p2(){
		for(int i=0; i<12; i++){
			printer1.addPaper(new Paper(PaperType.LIGHTWEIGHT, PaperSize.A4));
		}
		printer1.switchON();
		assertEquals("The process is complete", printer1.printOff(session));
		assertEquals(4, printer1.paperInTheTray()); //PRINTING PROCESS
	}
	
	@Test
	public void canPrintOffWithoutDuplexOnA5(){
		for(int i=0; i<3; i++){
			printer1.addPaper(new Paper(PaperType.LIGHTWEIGHT, PaperSize.A5));
		}
		printer1.switchON();
		assertEquals("The process is complete", printer1.printOff(session2));
		assertEquals(1, printer1.paperInTheTray());
		assertEquals("QWERTQWERT", printer1.getOutput().get(0).getFrontPage().getContent());
		assertEquals("PLKOIPLKOI", printer1.getOutput().get(1).getFrontPage().getContent());
	}
	
	@Test
	public void greyscalePrintingCalculatesTonerReduction(){
		for(int i=0; i<10; i++){
			printer1.addPaper(sheet2);
		}
		printer1.switchON();
		assertEquals("The process is complete", printer1.printOff(session2));
		assertEquals(3.0, printer1.calcDecreaseCartridgeRate(session2), 0.1);
	}

	@Test
	public void greyscalePrintingReducesAmountOfInkInACartridge(){
		cartridgesIn(1000.0, 1000.0, 1000.0, 1000.0);
		for(int i=0; i<3; i++){
			printer1.addPaper(sheet2);
		}
		printer1.switchON();
		System.out.println("Num of pages to print " + session2.getPages());
		printer1.printOff(session2);
		InkCartridge cartridge = printer1.getCartridges().get(CMYK.KEY);
		double cartridgeLvl = cartridge.getLevel();
		cartridgeLvl = Math.round(cartridgeLvl*1);
		InkCartridge cartridge2 = printer1.getCartridges().get(CMYK.CYAN);
		double cartridgeLvl2 = cartridge2.getLevel();
		cartridgeLvl2 = Math.round(cartridgeLvl2*1);
		InkCartridge cartridge3 = printer1.getCartridges().get(CMYK.YELLOW);
		double cartridgeLvl3 = cartridge3.getLevel();
		cartridgeLvl3 = Math.round(cartridgeLvl3*1);
		assertEquals(998.0, cartridgeLvl, 0.01);
		assertEquals(999.0, cartridgeLvl2, 0.1);
		assertEquals(1000.0, cartridgeLvl3, 0.1);
	}
	
	@Test
	public void colourPrintingCalculatesTheReductionRate(){
		cartridgesIn(1000.0, 1000.0, 1000.0, 1000.0);
		for(int i=0; i<10; i++){
			printer1.addPaper(sheet1);
		}
		printer1.switchON();
		System.out.println("Num of pages to print " + session3.getPages());
		assertEquals("The process is complete", printer1.printOff(session3));
		assertEquals(32.0, printer1.calcDecreaseCartridgeRate(session3), 0.1);
	}
	
	@Test
	public void colourPrintingReducesAmountOfInkInACartridge(){
		cartridgesIn(1000.0, 1000.0, 1000.0, 1000.0);
		for(int i=0; i<20; i++){
			printer1.addPaper(sheet1);
		}
		printer1.switchON();
		System.out.println("Num of pages to print " + session3.getPages());
		printer1.printOff(session3);
		InkCartridge cartridge = printer1.getCartridges().get(CMYK.KEY);
		double cartridgeLvlKEY = cartridge.getLevel();
		cartridgeLvlKEY = Math.round(cartridgeLvlKEY*1);
		InkCartridge cartridge2 = printer1.getCartridges().get(CMYK.CYAN);
		double cartridgeLvl2CYAN = cartridge2.getLevel();
		cartridgeLvl2CYAN = Math.round(cartridgeLvl2CYAN*1);
		InkCartridge cartridge3 = printer1.getCartridges().get(CMYK.YELLOW);
		double cartridgeLvl3YELL = cartridge3.getLevel();
		cartridgeLvl3YELL = Math.round(cartridgeLvl3YELL*1);
		InkCartridge cartridge4 = printer1.getCartridges().get(CMYK.MAGENTA);
		double cartridgeLvl4MAG = cartridge4.getLevel();
		cartridgeLvl4MAG = Math.round(cartridgeLvl4MAG*1);
		assertEquals(997.0, cartridgeLvlKEY, 0.1 );//96,8
		assertEquals(989.0, cartridgeLvl2CYAN, 0.1);//88,8
		assertEquals(990.0, cartridgeLvl3YELL, 0.1);//90,4
		assertEquals(992.0, cartridgeLvl4MAG, 0.1);
	}
	
	@Test
	public void printerDoesntHaveEnoughSheetsOfRequiredSize(){
		for(int i=0; i<2; i++){
			printer1.addPaper(sheet1);
		}
		for(int i=0; i<1; i++){
			printer1.addPaper(sheet2);
		}
		printer1.switchON();
		assertEquals("Not enough paper", printer1.printOff(session2));
	}
	
	@Test
	public void printerHasEnoughSheetsOfRequiredSize(){
		for(int i=0; i<2; i++){
			printer1.addPaper(sheet1);
		}
		for(int i=0; i<3; i++){
			printer1.addPaper(sheet2);
		}
		printer1.switchON();
		assertEquals("The process is complete", printer1.printOff(session2));
	}
	
	@Test
	public void printerRemembersNumbersOfPagesPrintedDuringNumerousSessions(){
		cartridgesIn(1000.0, 1000.0, 1000.0, 1000.0);
		for(int i=0; i<10; i++){
			printer1.addPaper(new Paper(PaperType.MATT, PaperSize.A3));
			printer1.addPaper(new Paper(PaperType.MATT, PaperSize.A4));
			printer1.addPaper(new Paper(PaperType.MATT, PaperSize.A5));
		}
		printer1.switchON();
		printer1.setCount(0);
		assertEquals(0, printer1.getCount());
		printer1.printOff(session2);//nduplex
		printer1.printOff(session5);
		printer1.printOff(session);
		assertEquals(26, printer1.getCount());
	}
	
	@Test
	public void canRepeatPrintingOfTheLastSession(){
		cartridgesIn(1000.0, 1000.0, 1000.0, 1000.0);
		for(int i=0; i<20; i++){
			printer1.addPaper(new Paper(PaperType.MATT, PaperSize.A3));
			printer1.addPaper(new Paper(PaperType.MATT, PaperSize.A4));
			printer1.addPaper(new Paper(PaperType.MATT, PaperSize.A5));
		}
		printer1.switchON();
		printer1.setCount(0);
		assertEquals(0, printer1.getCount());
		printer1.printOff(session2);//nduplex//2
		printer1.printOff(session3);//1
		printer1.printOff(session);//8
		printer1.printLastSession();//8
		assertEquals(19, printer1.getOutput().size());
	}
	
	@Test
	public void cannotRepeatPrintingOfTheLastSessionIfNotEnoughSheetsOfPaper(){
		cartridgesIn(1000.0, 1000.0, 1000.0, 1000.0);
		for(int i=0; i<10; i++){
			printer1.addPaper(new Paper(PaperType.MATT, PaperSize.A3));
			printer1.addPaper(new Paper(PaperType.MATT, PaperSize.A4));
			printer1.addPaper(new Paper(PaperType.MATT, PaperSize.A5));
		}
		printer1.switchON();
		printer1.setCount(0);
		assertEquals(0, printer1.getCount());
		printer1.printOff(session2);//nduplex//2
		printer1.printOff(session3);//1
		printer1.printOff(session);//8
		assertEquals("Not enough paper", printer1.printOff(printer1.getLastFile()));
	}
}
