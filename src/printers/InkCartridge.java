package printers;

public class InkCartridge extends Cartridge {
	
	private double level;
	
	public InkCartridge(CMYK color){
		super(color);
		this.level = 1000.0;
	}
	
	public double getLevel(){
		return level;
	}

	public void setLevel(double value){
		if(value <= (double) 1000.0 && value >= 0){
			this.level = value;
			System.out.println("The cartridge is upddated " + value);
			return;
		}else{
			System.out.println("The value is wrong");
		}	
	}
}
