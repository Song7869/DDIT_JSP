package kr.or.ddit.prod.preparer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@kr.or.ddit.annotations.ViewPreparer
public class ProdViewPreparer implements ViewPreparer{

	@Inject
	private ProdDAO dao;
	
	@PostConstruct
	public void init() {
		log.info("주입된 dao 객체 : {}", dao.getClass().getName());
	}
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		MenuVO menu1 = new MenuVO();
		menu1.setMenuURL("prod/prodInsert.do");
		menu1.setMenutext("신규상품등록");
		
		MenuVO menu2 = new MenuVO();
		menu2.setMenuURL("prod/prodList.do");
		menu2.setMenutext("상품목록");
		
		List<MenuVO> menuList = Arrays.asList(menu1, menu2);
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menulist", menuList);
	}

}
