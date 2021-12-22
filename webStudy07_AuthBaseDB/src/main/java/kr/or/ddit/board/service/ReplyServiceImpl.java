package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.ReplyDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO dao;
	
	@Inject
	private PasswordEncoder encoder;
	
	private void encryptPassword(ReplyVO reply) {
		reply.setRepPass(encoder.encode(reply.getRepPass()));
	}
	
	@Override
	public ServiceResult createReply(ReplyVO reply) {
		encryptPassword(reply);
		int rowcnt = dao.insertReply(reply);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}

	@Override
	public List<ReplyVO> retrieveReplyList(PagingVO<ReplyVO> pagingVO) {
		pagingVO.setTotalRecord(dao.selectTotalRecord(pagingVO));
		List<ReplyVO> replyList = dao.selectReplyList(pagingVO);
		pagingVO.setDataList(replyList);
		return replyList;
	}

	
	@Override
	public ServiceResult modifyReply(ReplyVO reply) {
		ServiceResult result = null;
		if(encoder.matches(reply.getRepPass(), dao.selectRepPass(reply.getRepNo()))) {
			int rowcnt = dao.updateReply(reply);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public ServiceResult removeReply(ReplyVO reply) {
		ServiceResult result = null;
		if(encoder.matches(reply.getRepPass(), dao.selectRepPass(reply.getRepNo()))) {
			int rowcnt = dao.deleteReply(reply);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

}
