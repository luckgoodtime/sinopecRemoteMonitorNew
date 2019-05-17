package com.lng.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class Page {
	private int totalRows; // 总行数
	private int pageSize = 20; // 每页显示的行数
	private int currentPage; // 当前页号
	private int totalPages; // 总页数
	private int startRow; // 当前页在数据库中的起始行
	private List dataList;

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public static Page getPager(HttpServletRequest httpServletRequest,
			int totalRows, int pageSize) {
		
		//参数里面可能有页面长度
		String pageSizeStr =  httpServletRequest.getParameter("rows");
		if(!StringUtils.isEmpty(pageSizeStr)) pageSize = Integer.parseInt(pageSizeStr);

		// 定义Pager对象，用于传到页面
		Page pager = new Page(totalRows, pageSize);

		// 从Request对象中获取当前页号
		String currentPage = httpServletRequest.getParameter("currentPage");
		if(StringUtils.isEmpty(currentPage))currentPage=httpServletRequest.getParameter("page");
		
		if(StringUtils.isEmpty(currentPage)) currentPage = "1";

		// 如果当前页号为空，表示为首次查询该页
		// 如果不为空，则刷新page对象，输入当前页号等信息
		if (currentPage != null && !"".equals(currentPage)
				&& !"null".equals(currentPage)) {
			pager.refresh(Integer.parseInt(currentPage));
		} 
		
		// 获取当前执行的方法，首页，前一页，后一页，尾页。
		String pagerMethod = httpServletRequest.getParameter("pageMethod");

		if (pagerMethod != null) {
			if (pagerMethod.equals("first")) {
				pager.first();
			} else if (pagerMethod.equals("previous")) {
				pager.previous();
			} else if (pagerMethod.equals("next")) {
				pager.next();
			} else if (pagerMethod.equals("last")) {
				pager.last();
			} else if (pagerMethod.equals("go")) {
				pager.go();
			}
		} else {
			pager.go();			
		}
		
		return pager;
	}

	public Page(int _totalRows, int _pageSize) {
		totalRows = _totalRows;
		pageSize = _pageSize;
		totalPages = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPages++;
		}
		currentPage = 1;
		startRow = 0;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void first() {
		currentPage = 1;
		startRow = 0;
	}

	public void previous() {
		if (currentPage == 1) {
			return;
		}
		currentPage--;
		 go();//startRow = (currentPage - 1) * pageSize;
	}

	public void next() {
		if (currentPage < totalPages) {
			currentPage++;
		}
		 go();//startRow = (currentPage - 1) * pageSize;
	}

	public void last() {
		currentPage = totalPages;
		 go();//startRow = (currentPage - 1) * pageSize;
	}

	public void go() {
		startRow = (currentPage - 1) * pageSize;
	}

	public void refresh(int _currentPage) {
		currentPage = _currentPage;
		if (currentPage > totalPages) {
			last();
		}
	}
}
