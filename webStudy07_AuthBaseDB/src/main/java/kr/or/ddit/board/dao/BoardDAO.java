package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

/**
 * FreeBoard 테이블을 대상으로 한 CRUD
 *
 */
@Repository
public interface BoardDAO {
	public int insertBoard(BoardVO board);
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	
	public BoardVO selectBoard(int boNo);
	
	public int updateBoard(BoardVO board);
	public int deleteBoard(int boNo);
	
	/**
	 * 프로시저를 활용한 게시글 삭제 방법
	 * (계층 구조상의 모든 하위 답글을 삭제하는 경우 활용)
	 * @param pMap
	 * @return
	 */
	public int deleteBoardUsingProcedure(Map<String, Object> pMap);
	
	public int incrementCount(Map<String, Object> pMap);
}























