package printers;

public class FrontPage {
	
	private boolean status;
	private String content;
	
	
	public FrontPage(){
		this.status = false;
		this.content = null;
	}

	public String writeContent(String content){
		if(this.status == false){
			this.status = true;
			this.content = content;
			return "Printing completed";
		}else{
			return "The page is printed";
		}
	}
	
	public boolean getStatus(){
		return status;
	}
	
	public String getContent(){
		if(this.status == true){
			return this.content;
		}
		return "The page is blank";
	}
}
