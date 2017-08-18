package printers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrinterTest {

	private Printer printer1;
	private Printer printer2;
	private Paper sheet1;
	private Paper sheet2;

	@Before
	public void before() {
		printer1 = new Printer("Dell", "1234", 100);
		printer2 = new Printer("HP", "x434", 123);
		sheet1 = new Paper(PaperType.MATT, PaperSize.A3);
		sheet2 = new Paper(PaperType.REGULAR, PaperSize.A3);
	}

	@Test
	public void hasBrand() {
		String brand = printer1.getBrand();
		assertEquals("Dell", brand);
	}

	@Test
	public void hasModel() {
		String model = printer1.getModel();
		assertEquals("1234", model);
	}

	@Test
	public void hasTotalPagesPrintedCount() {
		int count = printer1.getCount();
		assertEquals(0, count);
	}

	@Test
	public void hasPaperTrayPrinter1() {
		int result = printer1.getPaperTray().getLimit();
		assertEquals(100, result);
	}

	@Test
	public void hasPaperTrayPrinter2() {
		int result = printer2.getPaperTray().getLimit();
		assertEquals(123, result);
	}

	@Test
	public void printerCanBeSwtichedON() {
		printer1.switchON();
		boolean status = printer1.isOn();
		assertEquals(true, status);
	}

	@Test
	public void printerCanBeSwitchedOFF() {
		printer1.switchON();
		boolean status = printer1.isOn();
		printer1.switchOFF();
		status = printer1.isOn();
		assertEquals(false, status);
	}

	@Test
	public void doesNotPrintIfNoPaper() {
		printer1.addPaper(sheet1);
		printer1.addPaper(sheet2);
		printer1.switchON();
		printer1.printOff();
		printer1.printOff();
		assertEquals("No paper", printer1.printOff());
		assertEquals(2, printer1.getCount());
	}

	@Test
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

	@Test
	public void doesntPrintOffIfSwitchedOff() {
		printer1.addPaper(sheet1);
		printer1.switchOFF();
		assertEquals("The printer is OFF. Switch it on", printer1.printOff());
	}
}
