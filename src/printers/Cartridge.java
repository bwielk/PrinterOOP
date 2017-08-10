package printers;

public class Cartridge {
	
	private CMYK color;
	private double level;
	
	public Cartridge(CMYK color){
		this.color = color;
		this.level = 100.00;
	}
	
	public CMYK getColor(){
		return color;
	}
	
	public double getLevel(){
		return level;
	}
}
