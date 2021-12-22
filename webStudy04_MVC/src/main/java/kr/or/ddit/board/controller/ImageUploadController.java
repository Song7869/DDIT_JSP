package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.binding.MapperProxyFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ImageUploadController {

	private String saveFolderURL = "/resources/boardImages";
	
	@RequestMapping(value="/board/imageUpload.do", method=RequestMethod.POST)
	public String upload(
			@RequestPart("upload") MultipartFile upload,
			HttpServletRequest req, HttpServletResponse resp
		) throws IOException {
		log.info("넘어옴");
		
		
		Map<String, Object> imgMap = new HashMap<>(); // 이미지파일의 정보"들"을 담아올 그릇
		String fileName = null;
		String url = null;
		int uploaded = 0;
		
		if(!upload.isEmpty()) {			
			String mime = upload.getContentType();
			if(mime!=null && mime.startsWith("image/")) {
				File saveFolder = new File(req.getServletContext().getRealPath(saveFolderURL));
				if(!saveFolder.exists()) saveFolder.mkdir();
				String saveName = UUID.randomUUID().toString();
				upload.transferTo(new File(saveFolder, saveName));
				fileName = upload.getOriginalFilename();
				uploaded = 1;
				url = req.getContextPath() + saveFolderURL + "/" + saveName;
			}
		}
		
		imgMap.put("fileName", fileName);
		imgMap.put("uploaded", uploaded);
		imgMap.put("url", url);

		resp.setContentType("application/json;charset=UTF-8");
		
		try(
			PrintWriter out = resp.getWriter();
		){
			new ObjectMapper().writeValue(out, imgMap);
		}
		return null;
	}
}











