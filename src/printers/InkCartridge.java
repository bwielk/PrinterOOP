package printers;

public class InkCartridge extends Cartridge {
	
	private double level;
	
	public InkCartridge(CMYK color){
		super(color);
		this.level = 100.0;
	}
	
	public double getLevel(){
		return level;
	}
	
	//helper testing method
	public String setLevel(double value){
		if(value <= (double) 100){
			this.level = value;
			return "Level updated to the value of " + value + "";
		}else{
			return "The value is too high";
		}	
	}
}
