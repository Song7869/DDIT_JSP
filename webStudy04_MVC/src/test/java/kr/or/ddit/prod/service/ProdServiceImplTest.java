package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImplTest {
	private ProdService service = new ProdServiceImpl();
	private ProdVO dummy;
	private String prodId;

	@Before
	public void setUp() throws Exception {
		prodId = "P10100000a";
		dummy = new ProdVO();
	}

	@Test
	public void testCreateProd() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveProdList() {
		fail("Not yet implemented");
	}

	@Test(expected=PKNotFoundException.class)
	public void testRetrieveProd() {
		ProdVO saved = service.retrieveProd(prodId);
//		assertNotNull(saved);
	}

	@Test
	public void testModifyProd() {
		fail("Not yet implemented");
	}

}
