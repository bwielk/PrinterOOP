package printers;

public enum Resolution {
	
	HIGH(3),
	MEDIUM(2),
	LOW(1);
	
	private int inkUseRate;
	
	Resolution(int inkUseRate){
		this.inkUseRate = inkUseRate;
	}

	public int getInkUseRate() {
		return inkUseRate;
	}
	
}
