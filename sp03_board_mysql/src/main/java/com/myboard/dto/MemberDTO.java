package com.myboard.dto;

import java.util.Date;

public class MemberDTO{
	private String userid;
	private String passwd;
	private String email;
	private String filename;
	private Date regdate;
	
	public MemberDTO() {
		super();
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		return "MemberDTO [userid=" + userid + ", passwd=" + passwd + ", email=" + email + ", filename=" + filename
				+ ", regdate=" + regdate + "]";
	}
	public MemberDTO(String userid, String passwd, String email, String filename, Date regdate) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.email = email;
		this.filename = filename;
		this.regdate = regdate;
	}
	
}
	

