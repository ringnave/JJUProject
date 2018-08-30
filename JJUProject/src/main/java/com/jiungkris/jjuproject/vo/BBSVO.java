package com.jiungkris.jjuproject.vo;

import java.util.Date;

public class BBSVO {
	private int b_no;
	private String b_title;
	private String b_pw;
	private String b_content;
	private String b_writer;
	private Date b_date;
	private int b_views;
	private int b_commentCount;

	public int getB_commentCount() {
		return b_commentCount;
	}

	public void setB_commentCount(int b_commentCount) {
		this.b_commentCount = b_commentCount;
	}

	public String getB_pw() {
		return b_pw;
	}

	public void setB_pw(String b_pw) {
		this.b_pw = b_pw;
	}

	public int getB_no() {
		return b_no;
	}
	
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	
	public String getB_title() {
		return b_title;
	}
	
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	
	public String getB_content() {
		return b_content;
	}
	
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	
	public String getB_writer() {
		return b_writer;
	}
	
	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}
	
	public Date getB_date() {
		return b_date;
	}
	
	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}
	
	public int getB_views() {
		return b_views;
	}
	
	public void setB_views(int b_views) {
		this.b_views = b_views;
	}
	
	@Override
	public String toString() {
		return "BBSVO [b_no=" + b_no + ", b_title=" + b_title + ", b_pw=" + b_pw + ", b_content=" + b_content
				+ ", b_writer=" + b_writer + ", b_date=" + b_date + ", b_views=" + b_views + ", b_commentCount="
				+ b_commentCount + "]";
	}

}
