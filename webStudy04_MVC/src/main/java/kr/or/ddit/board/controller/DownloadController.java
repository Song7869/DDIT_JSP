package kr.or.ddit.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.servlet01.DescriptionServlet;
import kr.or.ddit.vo.AttatchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DownloadController {

	private BoardService service = new BoardServiceImpl();
	
	private String saveFolderPath = "d:/saveFiles";	// 다운로드 파일이 있는 위치
	private File saveFolder = new File(saveFolderPath); // 해당위치 파일객체 생성
	
	@RequestMapping("/board/download.do")
	public String download(
		@RequestParam("what") int attNo,
		HttpServletResponse resp
		) throws IOException, FileNotFoundException {
		log.info("다운로드번호 : {}", attNo);
		
		
		AttatchVO atch = service.download(attNo);
		int res = service.upDowncount(attNo);
		
		File saveFile = new File(saveFolder, atch.getAttSavename()); // 다운로드 파일이 있는 위치, 해당 파일의 객체 생성
		
		if(!saveFile.exists()) throw new FileNotFoundException("해당위치에 파일이 없습니다."); // 해당위치에 해당이름 파일이 없으면?
		
		String filename = URLEncoder.encode(atch.getAttFilename(), "UTF-8").replaceAll("\\+", " "); // 파일이름 인코딩 설정
		
		resp.setHeader("Content-Length", atch.getAttFilesize().toString()); // 전송데이터, 헤더 설정
		resp.setContentType("application/octet-stream"); // 전송데이터 마임 설정
		resp.setHeader("Content-Disposition", "attatchment;filename=\""+atch.getAttFilename()+"\""); // 나머지 헤더 설정
		//inline : 브라우져에서 바로 실행 할 때
		//attatchment : 첨부된 데이터 일 때
		
		try(
		OutputStream os = resp.getOutputStream();
		){
			FileUtils.copyFile(saveFile, os); // 지정 위치로 파일 복사 (다운로드)
		}

		return null;
	}
}
