package printers;

import java.util.ArrayList;
import java.util.HashMap;

public class InkjetPrinter extends Printer {
	
	private HashMap<CMYK, Cartridge> cartridges;

	public InkjetPrinter(String brand, String model, int limit) {
		super(brand, model, limit);
		this.cartridges = new HashMap<CMYK, Cartridge>();
	}
	
	public HashMap getCartridges(){
		return this.cartridges;
	}
	
	public String acceptCartridge(Cartridge cartridge){
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
		String cyan = (cartridges.containsKey(CMYK.CYAN))? "" + cartridges.get(CMYK.CYAN).getLevel() + "": "n/a";
		String magenta = (cartridges.containsKey(CMYK.MAGENTA))? "" + cartridges.get(CMYK.MAGENTA).getLevel() + "": "n/a";
		String yellow = (cartridges.containsKey(CMYK.YELLOW))? "" + cartridges.get(CMYK.YELLOW).getLevel() + "": "n/a";
		String key = (cartridges.containsKey(CMYK.KEY))? "" + cartridges.get(CMYK.KEY).getLevel() + "": "n/a";
		return "Ink levels : CYAN: " + cyan + " MAGENTA: " + magenta + " YELLOW: " + yellow + " KEY: " + key + "";
	}

	public String lowLevel() {
		ArrayList<String> report = new ArrayList<String>();
		for(Cartridge cartridge : cartridges.values()){
			if(cartridge.getLevel() <= (double) 20.0){
				report.add(cartridge.getColor().toString());
			}
		}
		String summary = String.join(", ", report);
		return "ATTENTION! The levels of inks: " + summary + " are low!";
	}
	
	public boolean cartridgesFull(){
		for(Cartridge cartridge : cartridges.values()){
			if(cartridge.getLevel() <= (double) 20.0){
				return false;
			}
		}
		return true;
	}
}
