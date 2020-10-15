package com.myboard.dto;

import java.util.Date;

public class BoardDTO {
	private int bnum;
	private String writer;
	private String email;
	private String subject;
	private String content;
	private int readcnt;
	private int replycnt;
	private Date regdate;
	private int likecnt;
	private int dislikecnt;
	private Date updatedate;
	public BoardDTO() {
		super();
	}
	public BoardDTO(int bnum, String writer, String email, String subject, String content, int readcnt, int replycnt,
			Date regdate, int likecnt, int dislikecnt, Date updatedate) {
		super();
		this.bnum = bnum;
		this.writer = writer;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.readcnt = readcnt;
		this.replycnt = replycnt;
		this.regdate = regdate;
		this.likecnt = likecnt;
		this.dislikecnt = dislikecnt;
		this.updatedate = updatedate;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public int getReplycnt() {
		return replycnt;
	}
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public int getLikecnt() {
		return likecnt;
	}
	public void setLikecnt(int likecnt) {
		this.likecnt = likecnt;
	}
	public int getDislikecnt() {
		return dislikecnt;
	}
	public void setDislike(int dislikecnt) {
		this.dislikecnt = dislikecnt;
	}
	@Override
	public String toString() {
		return "BoardDTO [bnum=" + bnum + ", writer=" + writer + ", email=" + email + ", subject=" + subject
				+ ", content=" + content + ", readcnt=" + readcnt + ", replycnt=" + replycnt + ", regdate=" + regdate
				+ ", likecnt=" + likecnt + ", dislikecnt=" + dislikecnt + ", updatedate=" + updatedate + "]";
	}
	
	
}
