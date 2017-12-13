package printers;

import java.util.ArrayList;
import java.util.HashMap;

public class LaserPrinter extends Printer{
	
	private HashMap<CMYK, LaserCartridge> cartridges;

	public LaserPrinter(String brand, String model, int limit) {
		super(brand, model, limit);
		this.cartridges = new HashMap<CMYK, LaserCartridge>();

	}
	
	public HashMap getCartridges(){
		return this.cartridges;
	}
	
	public String acceptCartridge(LaserCartridge cartridge){
		if(cartridges.containsKey(cartridge.getColor())){
			return "The " + cartridge.getColor() + " cartridge is already in";
		}else{
			cartridges.put(cartridge.getColor(), cartridge);
			return "The " + cartridge.getColor() + " cartridge has been loaded";
		}
	}
	
	public String tonerReport(){
		String cyan = (cartridges.containsKey(CMYK.CYAN))? "" + cartridges.get(CMYK.CYAN).getLevel()/200.0 * 100.0 + " %": "n/a";
		String magenta = (cartridges.containsKey(CMYK.MAGENTA))? "" + cartridges.get(CMYK.MAGENTA).getLevel()/200.0*100.0 + " %": "n/a";
		String yellow = (cartridges.containsKey(CMYK.YELLOW))? "" + cartridges.get(CMYK.YELLOW).getLevel()/200.0*100.0 + " %": "n/a";
		String key = (cartridges.containsKey(CMYK.KEY))? "" + cartridges.get(CMYK.KEY).getLevel()/200.0*100.0 + " %": "n/a";
		return "Toner levels : \nCYAN: " + cyan + " \nMAGENTA: " + magenta + " \nYELLOW: " + yellow + " \nKEY(BLACK): " + key + "";
	}
	
	public String lowLevel() {
		ArrayList<String> report = new ArrayList<String>();
		for(LaserCartridge cartridge : cartridges.values()){
			if(cartridge.getLevel()/200.0*100.0 <= (double) 20.0){
				report.add(cartridge.getColor().toString());
			}
		}
		String summary = String.join(", ", report);
		return "ATTENTION! The levels of toners: " + summary + " are low!";
	}
	
	/*
	public String printOff() {
		if (this.statusON == true) {
			if (getPaperTray().paperInTheTray() > 0) {
				int sheetsIn = getPaperTray().getTray().size();
				getPaperTray().getTray().remove(sheetsIn - 1);
				this.count += 1;
				return "A page has been printed off";
			} else {
				return "No paper";
			}
		} else {
			return "The printer is OFF. Switch it on";
		}
	}*/
}
