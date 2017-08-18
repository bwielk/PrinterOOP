package printers;

import java.util.ArrayList;

public class PrintingSession{
	
	private int pages;
	private String content;
	private PrintingMode mode;
	private PaperSize size;
	private ArrayList<String> pagesContent;
	private boolean duplex;
	

	public PrintingSession(String content, PrintingMode mode, PaperSize size, boolean duplex) {
		this.content = content;
		this.mode = mode;
		this.size = size;
		this.duplex = duplex;
		this.pagesContent = new ArrayList<String>();
		this.pages = setNumOfPages();
		splitContentIntoPages();
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
		if(isDuplex() == true){
		int num = (getPages()%2 == 1) ? getPages()/2 + 1 : getPages()/2;
		return num;
		}else{
			return getPages();
		}
	}
	
	public ArrayList<String> splitContentIntoPages(){
		int startPosition = 0;
		int endIndexCalc = 0;
		for(int i=0; i<getPages(); i++){
			if((getContent().length() - endIndexCalc) >= getSize().getCapacity()){	
				int endIndex = startPosition + (getSize().getCapacity());
				endIndexCalc = endIndex;
				this.pagesContent.add(getContent().substring(startPosition, endIndex));
				startPosition += (getSize().getCapacity());
			}else{
				this.pagesContent.add(getContent().substring(startPosition));
			}
		}
		return this.pagesContent;
	}
	
	public String getContentByPage(int pageNum){
		return this.pagesContent.get(pageNum-1);
	}

	public boolean isDuplex() {
		return duplex;
	}
	
	
}
