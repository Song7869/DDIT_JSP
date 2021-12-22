package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface BoardService {
	public ServiceResult createBoard(BoardVO board);
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO);
	public BoardVO retrieveBoard(int boNo);
	public ServiceResult modifyBoard(BoardVO board);
	public ServiceResult removeBoard(BoardVO board);
	
	public AttatchVO download(int attNo);
}
