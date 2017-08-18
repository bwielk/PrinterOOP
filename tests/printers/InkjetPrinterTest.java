package printers;

import static org.junit.Assert.*;

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
	private String content;
	private String content2;
	
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
			String sample = "Sjdnfjdnbfjdsbfkdbsksjabdbdkdafbfvvsdfd";
			content += sample;
		}
		
		content2 = "QWERTQWERTPLKOIPLKOI";
		printer1 = new InkjetPrinter("HP", "443", 200); 
		cartridge1 = new InkCartridge(CMYK.CYAN);
		cartridge2 = new InkCartridge(CMYK.MAGENTA);
		cartridge3 = new InkCartridge(CMYK.YELLOW);
		cartridge4 = new InkCartridge(CMYK.KEY);
		sheet1 = new Paper(PaperType.MATT, PaperSize.A4);
		sheet2 = new Paper(PaperType.LIGHTWEIGHT, PaperSize.A5);
		session = new PrintingSession(content, PrintingMode.GRAYSCALE, PaperSize.A4, true, Resolution.HIGH);
		session2 = new PrintingSession(content2, PrintingMode.GRAYSCALE, PaperSize.A5, false, Resolution.LOW);
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
		cartridge1.setLevel(30.0);
		cartridge2.setLevel(30.0);
		printer1.acceptCartridge(cartridge1);
		printer1.acceptCartridge(cartridge2);
		assertEquals("Ink levels : \nCYAN: 30.0 % \nMAGENTA: 30.0 % \nYELLOW: n/a \nKEY(BLACK): n/a", printer1.inkReport());
	}
	
	@Test
	public void notifiesOnLowLevels(){
		cartridgesIn(10.0, 20.0, 10.0, 20.0);
		String result = printer1.lowLevel();
		assertEquals(true, result.contains("YELLOW"));
		assertEquals(true, result.contains("CYAN"));
		assertEquals(true, result.contains("MAGENTA"));
		assertEquals(true, result.contains("KEY"));
	}
	
	@Test
	public void notifiesOnLowLevels2(){
		cartridgesIn(10.0, 20.0, 10.0, 30.0);
		String result = printer1.lowLevel();
		assertEquals(true, result.contains("YELLOW"));
		assertEquals(true, result.contains("CYAN"));
		assertEquals(true, result.contains("MAGENTA"));
		assertEquals(false, result.contains("KEY"));
	}
	
	@Test
	public void notifiesOnLowLevels3(){
		cartridgesIn(90.0, 20.0, 90.0, 90.0);
		assertEquals("ATTENTION! The levels of inks: MAGENTA are low!", printer1.lowLevel());
	}
	
	@Test
	public void areCartridgesFull(){
		cartridgesIn(20.0, 34.0, 100.0, 100.0);
		assertEquals(false, printer1.cartridgesFull());
	}
	
	@Test
	public void areCartridgesFull2(){
		cartridgesIn(100.0, 210.0, 30.0, 21.0);
		assertEquals(true, printer1.cartridgesFull());
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
		//System.out.println("The file has pages " + session.getPages());
		printer1.printOff(session);
		assertEquals(16, printer1.getLastFile().getPages());
	}
	
	@Test
	public void canCountTheNumberOfChars(){
		assertEquals(780, content.length());
	}
	
	@Test
	public void cannotPrintIfNotEnoughPaperSizeA4(){
		for(int i=0; i<7; i++){
			printer1.addPaper(sheet1);
		}
		/*System.out.println(printer1.paperInTheTray());
		System.out.println(session.getContentByPage(1));
		System.out.println(session.getContentByPage(2));
		System.out.println("Num of pages " + session.getPages());*/
		//System.out.println("sheets needed : " + session.getNumOfSheetsNeeded());
		printer1.switchON();
		assertEquals("Not enough paper", printer1.printOff(session));
		printer1.addPaper(sheet1);
		//System.out.println("Paper in the tray " + printer1.paperInTheTray());
		assertEquals("The process is complete", printer1.printOff(session));	
		assertEquals(0, printer1.paperInTheTray()); //PRINTING PROCESS
	}
	
	@Test
	public void canPrintOffIfEnoughPaperSizeA4(){
		for(int i=0; i<12; i++){
			printer1.addPaper(sheet1);
		}
		printer1.switchON();
		assertEquals("The process is complete", printer1.printOff(session));
		assertEquals(4, printer1.paperInTheTray()); //PRINTING PROCESS
	}
	
	@Test
	public void canPrintOffWithoutDuplexOnA5(){
		for(int i=0; i<3; i++){
			printer1.addPaper(sheet2);
		}
		printer1.switchON();
		assertEquals("The process is complete", printer1.printOff(session2));
		assertEquals(1, printer1.paperInTheTray());
	}
	
	@Test
	public void greyscalePrintingCalculatesTonerReduction(){
		for(int i=0; i<3; i++){
			printer1.addPaper(sheet2);
		}
		printer1.switchON();
		assertEquals("The process is complete", printer1.printOff(session2));
		System.out.println(session2.getRes().getInkUseRate());
		System.out.println(session2.getSize().getCapacity());
		System.out.println(session2.getContent().length());
		assertEquals(3.0, printer1.calcDecreaseCartridgeRate(session2), 0.1);
	}

	@Test
	public void greyscalePrintingCalculatesTonerReduction2(){
		cartridgesIn(100.0, 100.0, 100.0, 100.0);
		for(int i=0; i<3; i++){
			printer1.addPaper(sheet2);
		}
		printer1.switchON();
		printer1.printOff(session2);
		InkCartridge cartridge = printer1.getCartridges().get(CMYK.KEY);
		double cartridgeLvl = cartridge.getLevel();
		cartridgeLvl = Math.round(cartridgeLvl*1);
		assertEquals(96.0, cartridgeLvl, 0.1);
	}
}
