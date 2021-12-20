package board.model.dto;

import java.util.Date;

public class BoardDTO {

	private int bdIdx;
	private String writer;
	private Date regDate;
	private String title;
	private String content;
	private int isDel;
	private int views;
	private int password;
	
	public BoardDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getBdIdx() {
		return bdIdx;
	}

	public void setBdIdx(int bdIdx) {
		this.bdIdx = bdIdx;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "BoardDTO [bdIdx=" + bdIdx + ", writer=" + writer + ", regDate=" + regDate + ", title=" + title
				+ ", content=" + content + ", isDel=" + isDel + ", views=" + views + ", password=" + password + "]";
	}

	
	
}
