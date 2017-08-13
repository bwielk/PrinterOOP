package printers;

public class Paper {
	
	private FrontPage front;
	private BackPage back;
	private PaperType type;
	private PaperSize size;
	
	public Paper(PaperType type, PaperSize size){
		this.front = new FrontPage();
		this.back = new BackPage();
		this.type = type;
		this.size = size;
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
	
	public String getContentFront(){
		return getFrontPage().getContent();
	}
	
	public String getContentBack(){
		return getBackPage().getContent();
	}
	
	public void writeContentFront(String content){
		this.front.writeContent(content);
	}
	
	public void writeContentBack(String content){
		this.back.writeContent(content);
	}
	
	public PaperSize getSize(){
		return size;
	}
}
