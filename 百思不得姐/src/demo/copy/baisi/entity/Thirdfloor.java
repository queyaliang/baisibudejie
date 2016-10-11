package demo.copy.baisi.entity;

import java.util.List;

public class Thirdfloor {
	private int allPages;
	private List<Voice> contentlist;
	private int currentPage;
	private int allNum;
	private int maxResult;
	public int getAllPages() {
		return allPages;
	}
	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}
	public List<Voice> getContentlist() {
		return contentlist;
	}
	public void setContentlist(List<Voice> contentlist) {
		this.contentlist = contentlist;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getAllNum() {
		return allNum;
	}
	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}
	public int getMaxResult() {
		return maxResult;
	}
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	
}
