package com.sjzl.york.core.param;

public class PageParam {

	
	/**
	 * 第几页（从0开始）
	 */
	private Integer pageNum;
	/**
	 * 每页的信息数，默认20
	 */
	private Integer pageSize;
	
	
	public Integer getOffset() {
		return this.getPageNum() * this.getPageSize();
	}
	
	public Integer getPageNum() {
		if (pageNum == null || pageNum <= 0) {
			return 0;
		}
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		if (pageSize == null || pageSize <= 0) {
			return 10;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
