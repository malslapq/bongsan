package com.myboard.dto;

import java.util.Date;

public class BFileDTO {
	private int bnum; //게시물번호
	private int fnum; //파일번호
	private String filename; //파일이름
	private Date regdate; //등록일자
	public BFileDTO() {
		super();
	}
	public BFileDTO(int bnum, int fnum, String filename, Date regdate) {
		super();
		this.bnum = bnum;
		this.fnum = fnum;
		this.filename = filename;
		this.regdate = regdate;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public int getFnum() {
		return fnum;
	}
	public void setFnum(int fnum) {
		this.fnum = fnum;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "BFileDTO [bnum=" + bnum + ", fnum=" + fnum + ", filename=" + filename + ", regdate=" + regdate + "]";
	}

}
