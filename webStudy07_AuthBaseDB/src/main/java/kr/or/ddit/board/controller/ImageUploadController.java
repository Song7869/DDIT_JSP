package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ImageUploadController {
	
	@Inject
	private WebApplicationContext context;
	private ServletContext application;
	@Value("#{appInfo.boardImages}")
	private String saveFolderURL;
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		application = context.getServletContext();
		saveFolder = new File( application.getRealPath(saveFolderURL) );
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	@RequestMapping(value="/board/imageUpload.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> upload(
		@RequestPart("upload") MultipartFile upload
	) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		String fileName = null;
		int uploaded = 0;
		String url = null;
		if(!upload.isEmpty()) {
			String mime = upload.getContentType();
			if(mime!=null && mime.startsWith("image/")) {
				
				String saveName = UUID.randomUUID().toString();
				upload.transferTo(new File(saveFolder, saveName));
				fileName = upload.getOriginalFilename();
				uploaded = 1;
				url = application.getContextPath() + saveFolderURL + "/" + saveName;
			}
		}
		
		resultMap.put("fileName", fileName);
		resultMap.put("uploaded", uploaded);
		resultMap.put("url", url);
		
		return resultMap;
	}
	
}








