package com.myboard.dto;

import java.util.Date;

public class MemberDTO{
	private String userid;
	private String passwd;
	private String name;
	private String email;
	private String filename;
	private int joinflag; //0 일반회원가입, 1 네이버 회원가입,
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getJoinflag() {
		return joinflag;
	}

	public void setJoinflag(int joinflag) {
		this.joinflag = joinflag;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "MemberDTO [userid=" + userid + ", passwd=" + passwd + ", name=" + name + ", email=" + email
				+ ", filename=" + filename + ", joinflag=" + joinflag + ", regdate=" + regdate + "]";
	}

	public MemberDTO(String userid, String passwd, String name, String email, String filename, int joinflag,
			Date regdate) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
		this.email = email;
		this.filename = filename;
		this.joinflag = joinflag;
		this.regdate = regdate;
	}

}
	

