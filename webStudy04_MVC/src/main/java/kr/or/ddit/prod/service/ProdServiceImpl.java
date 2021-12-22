package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {
	private ProdDAO dao = new ProdDAOImpl();

	@Override
	public ServiceResult createProd(ProdVO prod) {
		int cnt = dao.insertProd(prod);
		return cnt > 0? ServiceResult.OK : ServiceResult.FAILED;
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO) {
		int totalRecord = dao.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<ProdVO> prodList = dao.selectProdList(pagingVO);
		pagingVO.setDataList(prodList);
		return prodList;
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = dao.selectProd(prodId);
		if(prod==null)
			throw new PKNotFoundException(prodId+"에 해당하는 상품이 없음.");
		return prod;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		retrieveProd(prod.getProdId());
		int rowcnt = dao.updateProd(prod);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}

}











