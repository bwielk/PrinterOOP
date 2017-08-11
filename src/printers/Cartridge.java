package printers;

public class Cartridge {
	
	protected CMYK color;
	
	public Cartridge(CMYK color){
		this.color = color;
	}
	
	public CMYK getColor(){
		return color;
	}
}
