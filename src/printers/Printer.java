package printers;

public class Printer {
	
	private String brand;
	private String model;
	private int count;
	private PaperTray paperTray;
	private boolean statusON;
	
	public Printer(String brand, String model, int limit){
		this.brand = brand;
		this.model = model;
		this.count = 0;
		this.paperTray = new PaperTray(limit);
		this.statusON = false;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public void setCount(int count) {
		this.count = count;
	}

	public PaperTray getPaperTray() {
		return paperTray;
	}

	public void setPaperTray(PaperTray paperTray) {
		this.paperTray = paperTray;
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
	
	public void addPaper(Paper sheet) {
		getPaperTray().addPaper(sheet);
	}
	
	public int paperInTheTray(){
		return getPaperTray().paperInTheTray();
	}
	
	public String printOff(){
		if(this.statusON == true){
			if(getPaperTray().paperInTheTray() > 0){
				int sheetsIn = getPaperTray().getTray().size();
				getPaperTray().getTray().remove(sheetsIn-1);
				this.count +=1;
				return "A page has been printed off";
			}else{
				return "No paper";
			}
		}else{
			return "The printer is OFF. Switch it on";
		}
	}
}
