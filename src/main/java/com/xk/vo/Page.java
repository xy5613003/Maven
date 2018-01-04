package com.xk.vo;

import java.io.Serializable;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public class Page implements Serializable {
	
	private static final long serialVersionUID = -3198048449643774660L;  
	  
    private int page;
    private int pageSize;
    private List<Object> rows;
    private int total;
    
	public Page(PageBounds pb, List<Object> records, int total) {
		super();
		this.page = pb.getPage();
		this.pageSize = pb.getLimit();
		this.rows = records;
		this.total = total;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
