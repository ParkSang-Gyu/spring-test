package kr.green.test.pagination;

public class Criteria {

	private int page;
	private int perPageNum;
	private int type;
	private String search;

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
		this.type = 0;
		this.search = "";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <= 0)
			this.page = 1;
		else 
			this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100)
			this.perPageNum = 10;
		else
			this.perPageNum = perPageNum;
	}
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	public int getPageStart() {
		return (this.page-1)*perPageNum;
	}
}
