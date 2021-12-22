package kr.or.ddit.board.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.util.CryptoUtils;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements BoardService {
	
	private BoardDAO boardDAO = new BoardDAOImpl();
	private AttatchDAO attatchDAO = new AttatchDAOImpl();
	
	private String saveFolderPath = "d:/saveFiles";
	private File saveFolder = new File(saveFolderPath);
	
	private int processAttatches(BoardVO board, SqlSession sqlSession) {
		int rowcnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList!=null && !attatchList.isEmpty()) {
			rowcnt = attatchDAO.insertAttatches(board, sqlSession);
			
//			if(1==1) throw new RuntimeException("강제 발생 예외");
			
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

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public ServiceResult createBoard(BoardVO board) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	 // transaction 시작
		){
			ServiceResult result = null;
			int rowcnt = boardDAO.insertBoard(board, sqlSession);
			if(rowcnt > 0) {
				rowcnt += processAttatches(board, sqlSession);
				result = ServiceResult.OK;		
				sqlSession.commit();
			}else {
				result = ServiceResult.FAILED;
			}
			return result;
		}
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

	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		Object authenticated = authenticated(board);
		ServiceResult result = null;
		if(authenticated instanceof BoardVO) {
			BoardVO saved = (BoardVO) authenticated;
			try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				int rowcnt = boardDAO.updateBoard(board, sqlSession);
				if(rowcnt > 0) {
					// 올릴 파일 처리
					processAttatches(board, sqlSession);
					// 지울 파일 처리
					int[] delAttNos = board.getDelAttNos();
					if(delAttNos!=null && delAttNos.length > 0) {
						List<AttatchVO> attatchList = saved.getAttatchList();
						attatchDAO.deleteAttatches(board, sqlSession);
						Arrays.sort(delAttNos);
						for(AttatchVO tmp : attatchList) {
							if(Arrays.binarySearch(delAttNos, tmp.getAttNo()) >= 0)
								FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavename()));
						}
					}
					result = ServiceResult.OK;
					sqlSession.commit();
				}else {
					result = ServiceResult.FAILED;
				}
			} // try end
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	private Object authenticated(BoardVO board){
		BoardVO saved = retrieveBoard(board.getBoNo());
		String savedPass = saved.getBoPass();
		String inputPass = board.getBoPass();
		inputPass = CryptoUtils.sha512Encryptic(inputPass);
		if( savedPass.equals(inputPass)) {
			//인증 성공의 경우
			return saved;
		}else {
			//인증 실패의 경우
			return ServiceResult.INVALIDPASSWORD;
		}
	}
	
	@Override
	public ServiceResult removeBoard(BoardVO board) {
//		BoardVO saved = retrieveBoard(board.getBoNo());
		Object authenticated = authenticated(board);
		ServiceResult result = null;
		if(authenticated instanceof BoardVO) {
			BoardVO saved = (BoardVO) authenticated;
			try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				List<AttatchVO> attatchList = saved.getAttatchList();
				attatchDAO.deleteAttatches(board, sqlSession);
				boardDAO.deleteBoard(board.getBoNo(), sqlSession);
				for(AttatchVO tmp : attatchList) {
					FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavename()));
				}
				result = ServiceResult.OK;
				sqlSession.commit();
			} // try end
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public AttatchVO download(int attNo) throws FileNotFoundException {
		AttatchVO atch = attatchDAO.selectAttatch(attNo);
		if(atch == null) {
			throw new FileNotFoundException();
		}
		return atch;
		}

	@Override
	public int upDowncount(int attNo) {
		
		return attatchDAO.upDowncount(attNo);
	}


}










