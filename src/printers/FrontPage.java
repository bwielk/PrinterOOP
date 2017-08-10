package printers;

public class FrontPage {
	
	private boolean status;
	
	public FrontPage(){
		this.status = false;
	}

	public void printed(){
		this.status = true;
	}
	
	public boolean getStatus(){
		return status;
	}
}
