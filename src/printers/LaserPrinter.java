package printers;

public class LaserPrinter extends Printer{

	public LaserPrinter(String brand, String model, int limit) {
		super(brand, model, limit);
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
