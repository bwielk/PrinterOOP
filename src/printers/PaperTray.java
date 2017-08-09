package printers;

import java.util.ArrayList;

public class PaperTray {

	private int limit;
	private ArrayList<Paper> paperTray;

	public PaperTray(int limit) {
		this.limit = limit;
		this.paperTray = new ArrayList<Paper>();
	}

	public int getLimit() {
		return limit;
	}

	public ArrayList<Paper> getTray() {
		return paperTray;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int paperInTheTray() {
		return this.paperTray.size();
	}

	public String addPaper(Paper sheet) {
		if (this.paperTray.size() < this.limit) {
			this.paperTray.add(sheet);
			return "The sheet's been added";
		} else {
			return "Cannot be added. The tray is full";
		}
	}
}
