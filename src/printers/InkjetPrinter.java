package printers;

import java.util.ArrayList;
import java.util.HashMap;

public class InkjetPrinter extends Printer {
	
	private HashMap<CMYK, InkCartridge> cartridges;

	public InkjetPrinter(String brand, String model, int limit) {
		super(brand, model, limit);
		this.cartridges = new HashMap<CMYK, InkCartridge>();
	}
	
	public HashMap getCartridges(){
		return this.cartridges;
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
	
	@Override
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
	}
}
