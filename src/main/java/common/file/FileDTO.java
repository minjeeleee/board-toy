package common.file;

import java.sql.Date;

public class FileDTO {

	private int flIdx;
	private int bdIdx;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	private Date regDate;
	private int isDel;
	
	public FileDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getFlIdx() {
		return flIdx;
	}

	public void setFlIdx(int flIdx) {
		this.flIdx = flIdx;
	}

	public int getBdIdx() {
		return bdIdx;
	}

	public void setBdIdx(int bdIdx) {
		this.bdIdx = bdIdx;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getRenameFileName() {
		return renameFileName;
	}

	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	
	public String getDownloadURL() {
		return"/file/"+ savePath + renameFileName + "?originName=" + originFileName;
	}

	@Override
	public String toString() {
		return "FileDTO [flIdx=" + flIdx + ", bdIdx=" + bdIdx + ", originFileName=" + originFileName
				+ ", renameFileName=" + renameFileName + ", savePath=" + savePath + ", regDate=" + regDate + ", isDel="
				+ isDel + "]";
	}
	
}
