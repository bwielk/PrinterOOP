package printers;

import java.util.HashMap;

public class InkjetPrinter extends Printer {
	
	private HashMap<CMYK, Cartridge> cartridges;

	public InkjetPrinter(String brand, String model, int limit) {
		super(brand, model, limit);
		this.cartridges = new HashMap<CMYK, Cartridge>();
	}

}
