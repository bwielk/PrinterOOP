package printers;

import java.util.ArrayList;

public class PrintingSession{
	
	private int pages;
	private String content;
	private PrintingMode mode;
	private PaperSize size;
	private ArrayList<String> pagesContent;
	private boolean duplex;
	private Resolution res;
	

	public PrintingSession(String content, PrintingMode mode, PaperSize size, boolean duplex, Resolution res) {
		this.content = content;
		this.mode = mode;
		this.size = size;
		this.duplex = duplex;
		this.res = res;
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
		ArrayList<String> content = new ArrayList<String>();
		int startPosition = 0;
		int endIndexCalc = 0;
		for(int i=0; i<getPages(); i++){
			if((getContent().length() - endIndexCalc) >= getSize().getCapacity()){	
				endIndexCalc = startPosition + (getSize().getCapacity());
				content.add(getContent().substring(startPosition, endIndexCalc));
				startPosition += getSize().getCapacity();
			}else{
				content.add(getContent().substring(startPosition));
			}
		}
		for(int i=0; i<content.size(); i++){
			this.pagesContent.add(content.get(i));
		}
		return content;
	}
	
	public String getContentByPage(int pageNum){
		return this.pagesContent.get(pageNum-1);
	}

	public boolean isDuplex() {
		return duplex;
	}

	public Resolution getRes() {
		return res;
	}
	
}
