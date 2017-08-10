package printers;

public class Paper {
	
	private FrontPage front;
	private BackPage back;
	
	public Paper(){
		this.front = new FrontPage();
		this.back = new BackPage();
	}
	
	public FrontPage getFrontPage() {
		return front;
	}
	public BackPage getBackPage() {
		return back;
	}
	}
