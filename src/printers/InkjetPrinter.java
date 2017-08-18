package printers;

import java.util.ArrayList;
import java.util.HashMap;

public class InkjetPrinter extends Printer {
	
	private HashMap<CMYK, InkCartridge> cartridges;
	private PrintingSession file;
	private boolean duplex;
	private ArrayList<Paper> output;
	

	public InkjetPrinter(String brand, String model, int limit) {
		super(brand, model, limit);
		this.cartridges = new HashMap<CMYK, InkCartridge>();
		this.file = null;
		this.duplex = false;
		this.output = new ArrayList<Paper>();
	}
	
	public HashMap getCartridges(){
		return this.cartridges;
	}
	
	public PrintingSession getLastFile() {
		return file;
	}

	public void setLastFile(PrintingSession file) {
		this.file = file;
	}

	public String acceptCartridge(InkCartridge cartridge){
		if(cartridges.containsKey(cartridge.getColor())){
			return "The " + cartridge.getColor() + " cartridge is already in";
		}else{
			cartridges.put(cartridge.getColor(), cartridge);
			return "The " + cartridge.getColor() + " cartridge has been loaded";
		}
	}
	
	public int numberOfCartridges(){
		return this.cartridges.size();
	}
	
	public String inkReport(){
		String cyan = (cartridges.containsKey(CMYK.CYAN))? "" + cartridges.get(CMYK.CYAN).getLevel() + " %": "n/a";
		String magenta = (cartridges.containsKey(CMYK.MAGENTA))? "" + cartridges.get(CMYK.MAGENTA).getLevel() + " %": "n/a";
		String yellow = (cartridges.containsKey(CMYK.YELLOW))? "" + cartridges.get(CMYK.YELLOW).getLevel() + " %": "n/a";
		String key = (cartridges.containsKey(CMYK.KEY))? "" + cartridges.get(CMYK.KEY).getLevel() + " %": "n/a";
		return "Ink levels : \nCYAN: " + cyan + " \nMAGENTA: " + magenta + " \nYELLOW: " + yellow + " \nKEY(BLACK): " + key + "";
	}

	public String lowLevel() {
		ArrayList<String> report = new ArrayList<String>();
		for(InkCartridge cartridge : cartridges.values()){
			if(cartridge.getLevel() <= (double) 20.0){
				report.add(cartridge.getColor().toString());
			}
		}
		String summary = String.join(", ", report);
		return "ATTENTION! The levels of inks: " + summary + " are low!";
	}
	
	public boolean cartridgesFull(){
		for(InkCartridge cartridge : cartridges.values()){
			if(cartridge.getLevel() <= (double) 20.0){
				return false;
			}
		}
		return true;
	}
	
	public String printOff(PrintingSession session) {
		int numOfSheets = session.getNumOfSheetsNeeded();
		System.out.println("NEW SESSION: SHEEETS : " + numOfSheets);
		if (this.statusON == true){
			if (getPaperTray().paperInTheTray() > 0 && numOfSheets <= getPaperTray().paperInTheTray()) {
				int indexOfContent = 0;
				for(int i=0; i<numOfSheets; i++){
					indexOfContent++;
					Paper sheetToPrint = getPaperTray().getTray().remove(0);
					sheetToPrint.getFrontPage().writeContent(session.getContentByPage(indexOfContent));
					indexOfContent++;
					sheetToPrint.getBackPage().writeContent(session.getContentByPage(indexOfContent));
					this.output.add(sheetToPrint);
					this.count += indexOfContent;
					setLastFile(session);
					if(getPaperTray().paperInTheTray() == getPaperTray().paperInTheTray()- numOfSheets){
						break;
					};
				}
				return "The process is complete";
			} else {
				return "Not enough paper";
			}
		} else {
			return "The printer is OFF. Switch it on";
		}
	}
}
