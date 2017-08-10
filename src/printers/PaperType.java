package printers;

public enum PaperType {

	SATIN(1), 
	CAST(2), 
	LIGHTWRIGHT(3), 
	MATT(4), 
	REGULAR(5);

	private int absorption;

	PaperType(int absorptionRate) {
		this.absorption = absorptionRate;
	}

	public int getAbsorption() {
		return absorption;
	}
}
