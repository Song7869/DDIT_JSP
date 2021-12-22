package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.ReplyDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.util.CryptoUtils;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO dao;
	
	private void encryptPassword(ReplyVO reply) {
		reply.setRepPass(CryptoUtils.sha512EncryptBase64(reply.getRepPass()));
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
		encryptPassword(reply);
		int rowcnt = dao.updateReply(reply);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}

	@Override
	public ServiceResult removeReply(ReplyVO reply) {
		encryptPassword(reply);
		int rowcnt = dao.deleteReply(reply);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}

}
