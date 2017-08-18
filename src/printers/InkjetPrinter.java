package printers;

import java.util.ArrayList;
import java.util.HashMap;

public class InkjetPrinter extends Printer {

	private HashMap<CMYK, InkCartridge> cartridges;
	private PrintingSession file;
	private ArrayList<Paper> output;

	public InkjetPrinter(String brand, String model, int limit) {
		super(brand, model, limit);
		this.cartridges = new HashMap<CMYK, InkCartridge>();
		this.file = null;
		this.output = new ArrayList<Paper>();
	}

	public HashMap<CMYK, InkCartridge> getCartridges() {
		return this.cartridges;
	}

	public PrintingSession getLastFile() {
		return file;
	}

	public void setLastFile(PrintingSession file) {
		this.file = file;
	}

	public String acceptCartridge(InkCartridge cartridge) {
		if (cartridges.containsKey(cartridge.getColor())) {
			return "The " + cartridge.getColor() + " cartridge is already in";
		} else {
			cartridges.put(cartridge.getColor(), cartridge);
			return "The " + cartridge.getColor() + " cartridge has been loaded";
		}
	}

	public int numberOfCartridges() {
		return this.cartridges.size();
	}

	public String inkReport() {
		String cyan = (cartridges.containsKey(CMYK.CYAN)) ? "" + cartridges.get(CMYK.CYAN).getLevel() + " %" : "n/a";
		String magenta = (cartridges.containsKey(CMYK.MAGENTA)) ? "" + cartridges.get(CMYK.MAGENTA).getLevel() + " %"
				: "n/a";
		String yellow = (cartridges.containsKey(CMYK.YELLOW)) ? "" + cartridges.get(CMYK.YELLOW).getLevel() + " %"
				: "n/a";
		String key = (cartridges.containsKey(CMYK.KEY)) ? "" + cartridges.get(CMYK.KEY).getLevel() + " %" : "n/a";
		return "Ink levels : \nCYAN: " + cyan + " \nMAGENTA: " + magenta + " \nYELLOW: " + yellow + " \nKEY(BLACK): "
				+ key + "";
	}

	public String lowLevel() {
		ArrayList<String> report = new ArrayList<String>();
		for (InkCartridge cartridge : cartridges.values()) {
			if (cartridge.getLevel() <= (double) 20.0) {
				report.add(cartridge.getColor().toString());
			}
		}
		String summary = String.join(", ", report);
		return "ATTENTION! The levels of inks: " + summary + " are low!";
	}

	public boolean cartridgesFull() {
		for (InkCartridge cartridge : cartridges.values()) {
			if (cartridge.getLevel() <= (double) 20.0) {
				return false;
			}
		}
		return true;
	}
	
	public InkCartridge updateCartridge(CMYK color, double value, double percentage){
		double state = this.cartridges.get(color).getLevel();
		this.cartridges.get(color).setLevel(state - value*percentage);
		return this.cartridges.get(color);
	}
	
	public double calcDecreaseCartridgeRate(PrintingSession session){
		double a = (double)session.getRes().getInkUseRate();
		double b = (double)session.getContent().length();
		double c = (double)session.getSize().getCapacity();
		double result = (a+(b*c))/(double)100;
		if(session.getMode() == PrintingMode.GRAYSCALE){
			if(getCartridges().containsKey(CMYK.KEY)){
				this.cartridges.put(CMYK.KEY, updateCartridge(CMYK.KEY, result, 0.66));
				this.cartridges.put(CMYK.CYAN, updateCartridge(CMYK.CYAN, result, 0.34));
			}else if(session.getMode() == PrintingMode.COLOUR){
				this.cartridges.put(CMYK.CYAN, updateCartridge(CMYK.CYAN, result, 0.35));
				this.cartridges.put(CMYK.MAGENTA, updateCartridge(CMYK.MAGENTA, result, 0.25));
				this.cartridges.put(CMYK.YELLOW, updateCartridge(CMYK.YELLOW, result, 0.30));
				this.cartridges.put(CMYK.KEY, updateCartridge(CMYK.KEY, result, 0.10));
			}
		}
		return result;
	}

	public String printOff(PrintingSession session) {
		int numOfSheets = session.getNumOfSheetsNeeded();
		if (this.statusON == true) {
			if (getPaperTray().paperInTheTray() > 0 && numOfSheets <= getPaperTray().paperInTheTray()) {
				if (session.isDuplex() == true) {
					int indexOfContent = 0;
					for (int i = 0; i < numOfSheets; i++) {
						indexOfContent++;
						Paper sheetToPrint = getPaperTray().getTray().remove(0);
						sheetToPrint.getFrontPage().writeContent(session.getContentByPage(indexOfContent));
						indexOfContent++;
						sheetToPrint.getBackPage().writeContent(session.getContentByPage(indexOfContent));
						this.output.add(sheetToPrint);
						this.count += indexOfContent;
						setLastFile(session);
						calcDecreaseCartridgeRate(session);
					}
				}else{
					int indexOfContent = 0;
					for (int i = 0; i < numOfSheets; i++) {
						indexOfContent++;
						Paper sheetToPrint = getPaperTray().getTray().remove(0);
						sheetToPrint.getFrontPage().writeContent(session.getContentByPage(indexOfContent));
						this.output.add(sheetToPrint);
						this.count += indexOfContent;
						setLastFile(session);
					}
				}
				return "The process is complete";

			}else{
				return "Not enough paper";
			}
		} else {
			return "The printer is OFF. Switch it on";
		}
	}
}
