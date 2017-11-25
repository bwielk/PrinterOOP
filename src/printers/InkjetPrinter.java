package printers;

import java.util.ArrayList;
import java.util.HashMap;

public class InkjetPrinter extends Printer {

	private HashMap<CMYK, InkCartridge> cartridges;
	private PrintingSession lastSession;
	private ArrayList<Paper> output;

	public InkjetPrinter(String brand, String model, int paperTrayLimit) {
		super(brand, model, paperTrayLimit);
		this.cartridges = new HashMap<CMYK, InkCartridge>();
		this.lastSession = null;
		this.output = new ArrayList<Paper>();
	}

	public HashMap<CMYK, InkCartridge> getCartridges() {
		return this.cartridges;
	}

	public ArrayList<Paper> getOutput() {
		return output;
	}

	public PrintingSession getLastFile() {
		return lastSession;
	}

	public void setLastFile(PrintingSession file) {
		this.lastSession = file;
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
		String cyan = (cartridges.containsKey(CMYK.CYAN)) ? "" + cartridges.get(CMYK.CYAN).getLevel()/1000*100+ " %" : "n/a";
		String magenta = (cartridges.containsKey(CMYK.MAGENTA)) ? "" + cartridges.get(CMYK.MAGENTA).getLevel()/1000*100 + " %"
				: "n/a";
		String yellow = (cartridges.containsKey(CMYK.YELLOW)) ? "" + cartridges.get(CMYK.YELLOW).getLevel()/1000*100 + " %"
				: "n/a";
		String key = (cartridges.containsKey(CMYK.KEY)) ? "" + cartridges.get(CMYK.KEY).getLevel()/1000*100 + " %" : "n/a";
		return "Ink levels : \nCYAN: " + cyan + " \nMAGENTA: " + magenta + " \nYELLOW: " + yellow + " \nKEY(BLACK): "
				+ key + "";
	}

	public String lowLevel() {
		ArrayList<String> report = new ArrayList<String>();
		for (InkCartridge cartridge : cartridges.values()) {
			if (cartridge.getLevel() <= (double)200.0) {
				report.add(cartridge.getColor().toString());
			}
		}
		String summary = String.join(", ", report);
		return "ATTENTION! The levels of inks: " + summary + " are low!";
	}

	public boolean cartridgesEnoughInk() {
		for (InkCartridge cartridge : cartridges.values()) {
			if (cartridge.getLevel() <= (double) 200.0) {
				return false;
			}
		}
		return true;
	}

	public InkCartridge updateCartridge(CMYK color, double value, double percentageOfUse) {
		double state = this.cartridges.get(color).getLevel();
		System.out.println("State of " + color + ": " + state );
		System.out.println("The new state of the cartridge is : " + (state - value*percentageOfUse));
		System.out.println("The " + color + " cartridge has been upddated ");
		this.cartridges.get(color).setLevel(state - value*percentageOfUse);
		return this.cartridges.get(color);
	}

	public double calcDecreaseCartridgeRate(PrintingSession session) {
		double a = (double) session.getRes().getInkUseRate();
		double b = (double) session.getContent().length();
		double c = (double) session.getSize().getCapacity();
		double result = (a + (b * c)) / (double) 100;
		System.out.println("Res " + a + "; length " + b + " ;capacity "+ c + " ; result " + result);
		if (session.getMode() == PrintingMode.GRAYSCALE) {
			if (getCartridges().containsKey(CMYK.KEY)) {
				this.cartridges.put(CMYK.KEY, updateCartridge(CMYK.KEY, result, 0.66));
				this.cartridges.put(CMYK.CYAN, updateCartridge(CMYK.CYAN, result, 0.34));
			}
		} else if (session.getMode() == PrintingMode.COLOUR) {
			this.cartridges.put(CMYK.CYAN, updateCartridge(CMYK.CYAN, result, 0.35));
			this.cartridges.put(CMYK.MAGENTA, updateCartridge(CMYK.MAGENTA, result, 0.25));
			this.cartridges.put(CMYK.YELLOW, updateCartridge(CMYK.YELLOW, result, 0.30));
			this.cartridges.put(CMYK.KEY, updateCartridge(CMYK.KEY, result, 0.10));
		}
		return result;
	}

	public String printOff(PrintingSession session) {
		
	    int numOfSheets = session.getNumOfSheetsNeeded();
	    if (this.statusON == true) {
	        if (isEnoughSheetsBySizeNeeded(session) && cartridgesEnoughInk()) {
	        	ArrayList<Paper> output = new ArrayList<Paper>();
	        	int pageToPrint = 0;
	            if (session.isDuplex()) { //DUPLEX
	                for (int i = 0; i < numOfSheets; i++) {
	                    pageToPrint++;
	                    Paper sheetToPrint = getPaperTray().getTray().remove(0);
	                    sheetToPrint.getFrontPage().writeContent(session.getContentByPage(pageToPrint));
	                    pageToPrint++;
	                    if(session.getPages()>1){
	                    	sheetToPrint.getBackPage().writeContent(session.getContentByPage(pageToPrint));
	                    }
	                    output.add(sheetToPrint);
	                }
	            } else {//NO DUPLEX
	                for (int i = 0; i < numOfSheets; i++) {
	                    Paper sheetToPrint = getPaperTray().getTray().remove(0);
	                    String pageToPrintFromSheetToPrint = session.getContentByPage(i+1);
	                    sheetToPrint.getFrontPage().writeContent(pageToPrintFromSheetToPrint);
	                    output.add(sheetToPrint);
	                    pageToPrint = i+1;	                    
	                }
	            }
	            this.count += pageToPrint;
	            for(int i=0; i<output.size(); i++){
                    this.output.add(output.get(i));
                }
	            calcDecreaseCartridgeRate(session);
	            setLastFile(session);
	            return "The process is complete";
	        } else {
	            return "Not enough paper";
	        }
	    } else {
	        return "The printer is OFF. Switch it on";
	    }
	}
	
	public void printLastSession(){
		printOff(this.lastSession);
	}
	
	public void printOffSpecificPage(PrintingSession session, int pageNum){
		PrintingSession newSession = new PrintingSession(session.getContentByPage(pageNum), session.getMode(), session.getSize(), session.isDuplex(), session.getRes());
		printOff(newSession);
	}
}
