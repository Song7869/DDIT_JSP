package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Service
public class ProdServiceImpl implements ProdService {
	@Resource(name="prodDAO")
	private ProdDAO dao;
	
	@Inject
	private WebApplicationContext context;
	
	@Value("#{appInfo['prodImages']}")
	private String saveFolderURL;
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		saveFolder = new File(context.getServletContext().getRealPath(saveFolderURL));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}

	private void processImage(ProdVO prod) {
		try {
			MultipartFile prodImage = prod.getProdImage();
			if(prodImage!=null) {
				String saveName = prod.getProdImg();
				File dest = new File(saveFolder, saveName);
				prodImage.transferTo(dest);
			}
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ServiceResult createProd(ProdVO prod) {
		int cnt = dao.insertProd(prod);
		ServiceResult result = null;
		if(cnt > 0) {
			processImage(prod);
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
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
		int cnt = dao.updateProd(prod);
		ServiceResult result = null;
		if(cnt > 0) {
			processImage(prod);
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}











