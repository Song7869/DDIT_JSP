package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public interface ReplyService {
	public ServiceResult createReply(ReplyVO reply);
	public List<ReplyVO> retrieveReplyList(PagingVO<ReplyVO> pagingVO);
	public ServiceResult modifyReply(ReplyVO reply);
	public ServiceResult removeReply(ReplyVO reply);
}
