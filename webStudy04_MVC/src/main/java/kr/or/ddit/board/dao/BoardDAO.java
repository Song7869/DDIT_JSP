package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

/**
 * FreeBoard 테이블을 대상으로 한 CRUD
 *
 */
public interface BoardDAO {
	public int insertBoard(BoardVO board, SqlSession sqlSession);
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	
	public BoardVO selectBoard(int boNo);
	
	public int updateBoard(BoardVO board, SqlSession sqlSession);
	public int deleteBoard(int boNo, SqlSession sqlSession);
	
	public int incrementCount(Map<String, Object> pMap);
}























