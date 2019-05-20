package com.jk.utils;

public class EasyuiPageUtil {
	private Integer total; //当前页
	private Object rows;  //每页几条
	
	
	@Override
	public String toString() {
		return "EasyuiPageUtil [total=" + total + ", rows=" + rows + "]";
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	
	
}
