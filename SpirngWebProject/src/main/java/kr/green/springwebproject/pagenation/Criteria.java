package kr.green.springwebproject.pagenation;

public class Criteria {

	private int page;				// 현재 페이지 
	private int perPageNum;			// 한 페이지에 보여줄 게시글의 수
	
	
	// 생성자
	public Criteria() {
		page = 1;
		perPageNum = 10;
	}
	
	public Criteria(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
	}

	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		//현재 페이지 번호를 음수로 설정하려 할 때

		if(page <= 0) {
			this.page = 1;
		}
		else
			this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		
		if(perPageNum <=0 || perPageNum > 100) {
			this.perPageNum = 10;
		}
		else
			this.perPageNum = perPageNum;
	}
	
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}

	
	// 쿼리 limit 0, 10 에서 0에 해당하는 메소드 getPageStart(시작점) -> 0부터 10개
	// limit n, 10 -> n = (page-1)*perPageNum
	public int getPageStart() {
		
		return (page-1)*perPageNum;
	}
	
}









