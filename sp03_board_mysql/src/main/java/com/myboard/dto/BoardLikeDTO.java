package com.myboard.dto;

public class BoardLikeDTO {
	
	private int bnum;	//pkey, fkey
	private String userid; //pkey fkey
	private int likecnt = 0;	// true 좋아요 누른 상태 false 좋아요를 누르지 않은 상태 
	private int dislikecnt = 0;	// 
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public void setDislikecnt(int dislikecnt) {
		this.dislikecnt = dislikecnt;
	}
	@Override
	public String toString() {
		return "BoardLikeDTO [bnum=" + bnum + ", userid=" + userid + ", likecnt=" + likecnt + ", dislikecnt="
				+ dislikecnt + "]";
	}
	public BoardLikeDTO(int bnum, String userid, int likecnt, int dislikecnt) {
		super();
		this.bnum = bnum;
		this.userid = userid;
		this.likecnt = likecnt;
		this.dislikecnt = dislikecnt;
	}
	public BoardLikeDTO() {
		super();
	}
	
	
	
}
