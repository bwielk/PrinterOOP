package printers;

public class Printer {

	private String brand;
	private String model;
	protected int count;
	protected PaperTray paperTray;
	protected boolean statusON;

	public Printer(String brand, String model, int limit) {
		this.brand = brand;
		this.model = model;
		this.count = 0;
		this.paperTray = new PaperTray(limit);
		this.statusON = false;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getCount() {
		return count;
	}

	public PaperTray getPaperTray() {
		return paperTray;
	}

	public boolean isOn() {
		return statusON;
	}

	public void switchON() {
		this.statusON = true;
	}

	public void switchOFF() {
		this.statusON = false;
	}
	
	//mainly used for quicker testing(helper method)
	public void addPaper(Paper sheet) {
		getPaperTray().addPaper(sheet);
	}

	public int paperInTheTray() {
		return getPaperTray().paperInTheTray();
	}
	
	public boolean isEnoughSheetsBySizeNeeded(PrintingSession session) {
		int counter = 0;
		for (Paper paper : paperTray.getTray()) {
			if (paper.getSize() == session.getSize()) {
				counter++;
			}
		}
		return (session.getNumOfSheetsNeeded() <= counter);
	}

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
