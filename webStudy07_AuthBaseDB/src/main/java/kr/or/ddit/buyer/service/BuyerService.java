package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 거래처 관리 Business Logic Layer
 *
 */
public interface BuyerService {
	public int retrieveBuyerCount(PagingVO<BuyerVO> pagingVO);
	public List<BuyerVO> retrieveBuyerList(PagingVO<BuyerVO> pagingVO);
	public BuyerVO retrieveBuyer(String buyer_id);
	public ServiceResult createBuyer(BuyerVO buyer);
	public ServiceResult modifyBuyer(BuyerVO buyer);
}
