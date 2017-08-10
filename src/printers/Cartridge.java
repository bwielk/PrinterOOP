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
	
	//helper testing method
	public String setLevel(double value){
		if(value <= (double) 100){
			this.level = value;
		}else{
			return "The value is too high";
		}
		return "Level updated to the value of " + value
				;
	}
}
