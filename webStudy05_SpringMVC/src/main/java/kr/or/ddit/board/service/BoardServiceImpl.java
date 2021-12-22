package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.util.CryptoUtils;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO boardDAO;
	@Inject
	private AttatchDAO attatchDAO;
	
	@Value("#{appInfo.attatchPath}")
	private String saveFolderPath;
	@Value("#{appInfo.attatchPath}")
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException {
//		saveFolder = new File(saveFolderPath);
		log.info("첨부파일 저장 위치 : {}", saveFolder.getCanonicalPath());
	}
	
	private int processAttatches(BoardVO board) {
		int rowcnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList!=null && !attatchList.isEmpty()) {
			rowcnt = attatchDAO.insertAttatches(board);
			
			if(1==1) throw new RuntimeException("강제 발생 예외");
			
			try {
				for(AttatchVO atch : attatchList) {
					atch.saveTo(saveFolder);
				}
			}catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return rowcnt;
	}

	private void encryptPassword(BoardVO board) {
		board.setBoPass(CryptoUtils.sha512EncryptBase64(board.getBoPass()));
	}
	
	@Transactional
	@Override
	public ServiceResult createBoard(BoardVO board) {
		ServiceResult result = null;
		
		encryptPassword(board);
		
		int rowcnt = boardDAO.insertBoard(board);
		if(rowcnt > 0) {
			rowcnt += processAttatches(board);
			result = ServiceResult.OK;		
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		List<BoardVO> boardList = boardDAO.selectBoardList(pagingVO);
		pagingVO.setTotalRecord(boardDAO.selectTotalRecord(pagingVO));
		pagingVO.setDataList(boardList);
		return boardList;
	}

	@Override
	public BoardVO retrieveBoard(int boNo) {
		BoardVO board = boardDAO.selectBoard(boNo);
		if(board==null)
			throw new PKNotFoundException(boNo +"번 글이 없음.");
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boNo", boNo);
		pMap.put("incType", "BO_HIT"); // replace text 활용
		boardDAO.incrementCount(pMap);
		return board;
	}

	@Transactional
	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		Object authenticated = authenticated(board);
		ServiceResult result = null;
		if(authenticated instanceof BoardVO) {
			BoardVO saved = (BoardVO) authenticated;
			int rowcnt = boardDAO.updateBoard(board);
			if(rowcnt > 0) {
				// 올릴 파일 처리
				processAttatches(board);
				// 지울 파일 처리
				int[] delAttNos = board.getDelAttNos();
				if(delAttNos!=null && delAttNos.length > 0) {
					List<AttatchVO> attatchList = saved.getAttatchList();
					attatchDAO.deleteAttatches(board);
					Arrays.sort(delAttNos);
					for(AttatchVO tmp : attatchList) {
						if(Arrays.binarySearch(delAttNos, tmp.getAttNo()) >= 0)
							FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavename()));
					}
				}
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	private Object authenticated(BoardVO board){
		BoardVO saved = retrieveBoard(board.getBoNo());
		String savedPass = saved.getBoPass();
		String inputPass = board.getBoPass();
		inputPass = CryptoUtils.sha512EncryptBase64(inputPass);
		if(savedPass.equals(inputPass)) {
			return saved;
		}else {
			return ServiceResult.INVALIDPASSWORD;
		}
	}
	
	@Transactional
	@Override
	public ServiceResult removeBoard(BoardVO board) {
		Object authenticated = authenticated(board);
		ServiceResult result = null;
		if(authenticated instanceof BoardVO) {
			BoardVO saved = (BoardVO) authenticated;
			List<AttatchVO> attatchList = saved.getAttatchList();
			attatchDAO.deleteAttatches(board);
			boardDAO.deleteBoard(board.getBoNo());
			for(AttatchVO tmp : attatchList) {
				FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavename()));
			}
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public AttatchVO download(int attNo) {
		AttatchVO atch = attatchDAO.selectAttatch(attNo);
		if(atch==null)
			throw new PKNotFoundException(attNo+"파일의 메타데이터가 없음.");
		return atch;
	}

}










