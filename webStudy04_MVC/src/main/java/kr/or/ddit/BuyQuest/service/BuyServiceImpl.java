package kr.or.ddit.BuyQuest.service;

import java.util.List;

import kr.or.ddit.BuyQuest.dao.BuyDao;
import kr.or.ddit.BuyQuest.dao.BuyDaoImpl;
import kr.or.ddit.vo.BuyQuestVO;

public class BuyServiceImpl implements BuyService {

	private BuyDao service = new BuyDaoImpl();
	
	@Override
	public List<BuyQuestVO> retrieveBQList() {
		return service.selectBQList();
	}

}
