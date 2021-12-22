package kr.or.ddit.board.service;

import java.io.FileNotFoundException;
import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public interface ReplyService {
	public ServiceResult insertReply(ReplyVO replyVO);
	public List<ReplyVO> retrieveReplyList(PagingVO<ReplyVO> pagingVO);
	public ServiceResult modifyBoard(ReplyVO replyVO);
	public ServiceResult removeBoard(ReplyVO replyVO);
}
