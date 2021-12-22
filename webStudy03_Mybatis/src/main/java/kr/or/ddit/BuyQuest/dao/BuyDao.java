package kr.or.ddit.BuyQuest.dao;

import java.util.List;

import kr.or.ddit.vo.BuyQuestVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public interface BuyDao {
	/**
	 * 바이어 목록 조회
	 * @return 조회 데이터가 없다면, size()==0
	 */
	public List<BuyQuestVO> selectBQList();
}
