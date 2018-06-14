package kr.green.springwebproject.pagenation;

public class PageMaker {

	private int totalCount;						// 검색된 총 게시글의 수
	private int startPage, endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 10 ;			// 한번에 보여줄 페이지의 수 = 10개
	Criteria criteria;
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calc();										// totalCount가 설정이 된 후에 calc 호출
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		
		if(startPage <= 0)
			this.startPage = 1;
		else
			this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	// page의 수를 최소 5개 이상으로 하려고 할 때
	public void setDisplayPageNum(int displayPageNum) {
		
		if(displayPageNum <= 5)
			this.displayPageNum = 5;
		else
			this.displayPageNum = displayPageNum;
	}
	public Criteria getCriteria() {
		return criteria;
	}
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	
	/* endPage, startPage, prev, next 값 계산 */
	void calc() {
		
		// 현재페이지 : 11, 한페이지에 보이는 게시글 : 10
		// 페이지메이커가 보여주는 페이지 수 : 10
		// totalCount(검색된 총 게시글의 수) = 181
		// endPage = (int) (Math.ceil(11/10.0) * displayPageNum); = (Math.ceil(1.1) * displayPageNum);
		// = (2 * 10); = 20
		endPage = (int) (Math.ceil(criteria.getPage()/(double) displayPageNum)*displayPageNum);
		
		
		// startPage = (20 - 10) + 1; = 11
		startPage = (endPage - displayPageNum)+1;
		
		
		/* 총 콘텐츠 갯수를 이용하여 마지막 페이지 번호를 계산 */
		// int tempEndPage = (int)(Math.ceil(181/10.0); = (Math.ceil(18.1); = 19 
		int tempEndPage = (int)(Math.ceil(totalCount/(double)criteria.getPerPageNum()));
		
		if( endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		
		/* 현재 페이지메이커에 시작페이지가 11이면 prev가 있어야 하므로 true */
		prev = startPage == 1 ? false : true; 
		
		
		/* 현재 페이지메이커에 마지막 페이지에 컨텐츠의 마지막이 포함되어 있으면 next가 없어야 함 */
		next = endPage * criteria.getPerPageNum() >= totalCount ? false:true;
		
	}
	
	
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", criteria=" + criteria + "]";
	}
	
	
	
	
	
	
}









