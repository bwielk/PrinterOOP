package printers;

public enum PaperSize {
	
	A3(100),
	A4(50),
	A5(10);
	
	private int capacity;
	
	PaperSize(int capacity){
		this.capacity = capacity;
	}
	
	public int getCapacity(){
		return capacity;
	}
}
