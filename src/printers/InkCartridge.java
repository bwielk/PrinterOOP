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

	public void setLevel(double value){
		if(value <= (double) 100 && value > 0){
			this.level = value;
		}else{
			System.out.println("The value is wrong");
		}	
	}
}
