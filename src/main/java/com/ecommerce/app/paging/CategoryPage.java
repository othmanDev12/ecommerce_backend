package com.ecommerce.app.paging;


import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;



public class CategoryPage {
	
	private int pageNumber = 0;
	private int pageSize = 6;
	private Sort.Direction sortDirection = Direction.ASC;
	private String sortedBy = "categoryName";
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Sort.Direction getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(Sort.Direction sortDirection) {
		this.sortDirection = sortDirection;
	}
	public String getSortedBy() {
		return sortedBy;
	}
	public void setSortedBy(String sortedBy) {
		this.sortedBy = sortedBy;
	}
	
	
	

}
