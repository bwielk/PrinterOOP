package printers;

public class LaserCartridge extends Cartridge {

	private double level;

	public LaserCartridge(CMYK color) {
		super(color);
		this.level = 2000.0;
	}

	public double getLevel() {
		return level;
	}

	public String setLevel(double value) {
		if (value <= (double) 2000) {
			this.level = value;
			return "Level updated to the value of " + value + "";
		} else {
			return "The value is too high";
		}
	}
}
