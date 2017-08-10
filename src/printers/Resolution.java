package printers;

public enum Resolution {
	
	HIGH(300),
	MEDIUM(200),
	LOW(100);
	
	private int dpi;
	
	Resolution(int dpi){
		this.dpi = dpi;
	}

	public int getInkUseRate() {
		return dpi;
	}
}
