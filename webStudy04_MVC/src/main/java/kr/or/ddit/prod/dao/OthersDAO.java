package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.BuyerVO;

/**
 * UI 구성을 위한 상품분류와 거래처 목록을 조회하기 위한 Persistence 
 *
 */
public interface OthersDAO {
 	public List<Map<String, Object>> selectLprodList();
 	public List<BuyerVO> selectBuyerList(@Param("lprodGu") String lprodGu);
}
