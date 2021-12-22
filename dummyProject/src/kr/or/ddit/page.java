package kr.or.ddit;

public class page {
	public static void main(String[] args) {
		
		int totalRecord = 100;
		int blockSize = 5;
		int screenSize = 10;
		
		int currentPage = 8;
		
		int totalPage = (totalRecord + (screenSize - 1)) / screenSize;
		
		int endRow = currentPage * screenSize;
		
		int startRow = endRow - (screenSize - 1);
		
		int endPage = blockSize * ((currentPage + (blockSize-1)))/blockSize;
		
		int startPage = endPage - (blockSize - 1);
	}
}
