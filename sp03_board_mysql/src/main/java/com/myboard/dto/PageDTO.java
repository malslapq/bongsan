package com.myboard.dto;
//페이징처리와 검색처리
public class PageDTO {
	private int curPage = 1; //현재페이지
	private int perPage = 10; //한페이지당 게시물수
	private int perBlock = 5; //한화면의 페이지수
	private int totPage; //전체페이지수
	private int startNo; //시작번호
	private int endNo; //끝번호
	private int startPage; //시작페이지
	private int endPage;   //끝번호
	private String findKey;  //검색키
	private String findValue; //검색값
	public PageDTO() {
		super();
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getPerBlock() {
		return perBlock;
	}
	public void setPerBlock(int perBlock) {
		this.perBlock = perBlock;
	}
	public int getTotPage() {
		return totPage;
	}
	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	public String getFindKey() {
		return findKey;
	}
	public void setFindKey(String findKey) {
		this.findKey = findKey;
	}
	public String getFindValue() {
		return findValue;
	}
	public void setFindValue(String findValue) {
		this.findValue = findValue;
	}
	@Override
	public String toString() {
		return "PageDTO [curPage=" + curPage + ", perPage=" + perPage + ", perBlock=" + perBlock + ", totPage="
				+ totPage + ", startPage=" + startPage + ", endPage=" + endPage + ", startNo=" + startNo + ", endNo="
				+ endNo + ", findKey=" + findKey + ", findValue=" + findValue + "]";
	}
	
	
}
