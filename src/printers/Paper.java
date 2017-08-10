package printers;

public class Paper {
	
	private FrontPage front;
	private BackPage back;
	private PaperType type;
	
	public Paper(PaperType type){
		this.front = new FrontPage();
		this.back = new BackPage();
		this.type = type;
	}
	
	public FrontPage getFrontPage() {
		return front;
	}
	public BackPage getBackPage() {
		return back;
	}
	
	public PaperType getType(){
		return type;
	}
	
	public int getAbsorption(){
		return type.getAbsorption();
	}
}
