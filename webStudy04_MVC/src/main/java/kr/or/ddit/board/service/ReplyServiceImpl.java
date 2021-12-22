package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.ReplyDAO;
import kr.or.ddit.board.dao.ReplyDAOImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.util.CryptoUtils;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public class ReplyServiceImpl implements ReplyService {

	private ReplyDAO dao = new ReplyDAOImpl();
	
	private void passwordEncrypt(ReplyVO replyVO) {
		String password = replyVO.getRepPass();
		password = CryptoUtils.sha512Encryptic(password);
		replyVO.setRepPass(password);
	}
	
	@Override
	public ServiceResult insertReply(ReplyVO replyVO) {
		ServiceResult result = null;
		int res= dao.insertReply(replyVO);
		if(res > 0) {
			result = ServiceResult.OK; 
		}
		else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<ReplyVO> retrieveReplyList(PagingVO<ReplyVO> pagingVO) {
		pagingVO.setTotalRecord(dao.selectTotalRecord(pagingVO));
		List<ReplyVO> repltList = dao.selectReplyList(pagingVO);
		pagingVO.setDataList(repltList);
		return repltList;
	}



	@Override
	public ServiceResult modifyBoard(ReplyVO reply) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeBoard(ReplyVO reply) {
		// TODO Auto-generated method stub
		return null;
	}

}
