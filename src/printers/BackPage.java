package printers;

public class BackPage {
	
private boolean status;
	
	public BackPage(){
		this.status = false;
	}

	public void printed(){
		this.status = true;
	}
	
	public boolean getStatus(){
		return status;
	}
}
