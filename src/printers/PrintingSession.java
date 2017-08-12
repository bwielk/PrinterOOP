package printers;

public class PrintingSession{
	
	private int pages;
	private String content;
	private PrintingMode mode;
	private PaperSize size;

	public PrintingSession(String content, PrintingMode mode, PaperSize size) {
		this.pages = getNumOfPages();
		this.content = content;
		this.mode = mode;
		this.size = size;
	}

	public int getPages() {
		return getNumOfPages();
	}

	public String getContent(){
		return this.content;
	}
	
	public PrintingMode getMode(){
		return this.mode;
	}
	
	public int getNumOfPages(){
		int numOfCharsInString = this.content.length();
		int result = (numOfCharsInString % this.size.getCapacity() != 0) ? numOfCharsInString%this.size.getCapacity() + 1 : numOfCharsInString%this.size.getCapacity()  ;
		return result;
	}
	
	public int getNumOfSheetsNeeded(){
		int num = (getPages()%2 == 1) ? getPages()/2 + 1 : getPages()/2;
		return num;
	}
}
