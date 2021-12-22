package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리용 Persistence Layer
 *
 */
public interface ProdDAO {
	public int insertProd(ProdVO prod);
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO);
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO);
	public ProdVO selectProd(String prodId);
	public int updateProd(ProdVO prod);	
	
}
