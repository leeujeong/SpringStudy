package com.koreait.test1.dto;

import java.sql.Date;

public class Board {
	
	private int idx;
	private String writer;
	private String title;
	private String content;
	private Date created;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return "Board [idx=" + idx + ", writer=" + writer + ", title=" + title + ", content=" + content + ", created="
				+ created + "]";
	}
	
}
