package printers;

public class PrintingSession{
	
	private int pages;
	private String content;
	private PrintingMode mode;
	private PaperSize size;
	

	public PrintingSession(String content, PrintingMode mode, PaperSize size) {
		this.content = content;
		this.mode = mode;
		this.size = size;
		this.pages = setNumOfPages();
	}

	public int getPages() {
		return pages;
	}
	
	public int setNumOfPages(){
		int numOfCharsInString = getContent().length();
		int result = (numOfCharsInString%this.size.getCapacity() > 0) ? numOfCharsInString/this.size.getCapacity()+1 : numOfCharsInString/this.size.getCapacity();
		return result;
	}

	public String getContent(){
		return content;
	}
	
	public PrintingMode getMode(){
		return mode;
	}
	
	public PaperSize getSize(){
		return size;
	}
	
	public int getNumOfSheetsNeeded(){
		int num = (getPages()%2 == 1) ? getPages()/2 + 1 : getPages()/2;
		return num;
	}
}
