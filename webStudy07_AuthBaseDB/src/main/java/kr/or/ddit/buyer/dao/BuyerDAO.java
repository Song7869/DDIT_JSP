package kr.or.ddit.buyer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 거래처 관리 Persistence Layer
 *
 */
@Repository
public interface BuyerDAO {
	public int selectBuyerCount(PagingVO<BuyerVO> pagingVO);
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO);
	public BuyerVO selectBuyer(String buyer_id);
	public int insertBuyer(BuyerVO buyer);
	public int updateBuyer(BuyerVO buyer);
}
