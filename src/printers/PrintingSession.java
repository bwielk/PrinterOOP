package printers;

public class PrintingSession{
	
	private int pages;
	private String content;
	private PrintingMode mode;
	private PaperSize size;

	public PrintingSession(int pages, String content, PrintingMode mode, PaperSize size) {
		this.pages = pages;
		this.content = content;
		this.mode = mode;
		this.size = size;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public PrintingMode getMode(){
		return this.mode;
	}
	
	public int getNumOfSheetsNeeded(){
		int num = (getPages()%2 == 1) ? getPages()/2 + 1 : getPages()/2;
		return num;
	}
}
