package kr.or.ddit.servlet08;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

//@WebServlet("/fileUploadSpec3")
//@MultipartConfig
@Controller
@Slf4j
public class FileUploadControllerSpec3{
	String saveFolderURL = "/resources/images";
	
	@RequestMapping(value="/fileUploadSpec3.do", method=RequestMethod.POST)
	public String upload(
			@RequestParam("uploader") String uploader,
			@RequestPart("uploadFile") MultipartFile[] fileParts,
			StandardMultipartHttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
		//		Body -> N 개의 파트 (2.5 FileItem, 3.0이후부터 Part)
		session.setAttribute("uploader", uploader);
//		MultipartFile filePart = new StandardMultipartFile(req.getPart("uploadFile"));
//		MultipartFile filePart = req.getFile("uploadFile");
		int idx = 0;
		for(MultipartFile filePart : fileParts) {
			if(filePart.isEmpty()) continue;
			log.info("upload file : {}", filePart);
			String originalFilename = filePart.getOriginalFilename();
			log.info("원본 파일명 : {}", originalFilename);
			String contentType = filePart.getContentType();
			if(contentType!=null)
				log.info("허용여부 : {}", contentType.startsWith("image/"));
			log.info("파일 크기 : {}", filePart.getSize());
			File saveFolder = new File(req.getServletContext().getRealPath(saveFolderURL));
			String saveName = UUID.randomUUID().toString();
			File saveFile = new File(saveFolder, saveName);
			String fileURL = saveFolderURL + "/" + saveName;
			filePart.transferTo(saveFile);
			session.setAttribute("uploadFile_"+(idx++), fileURL);
			
		}
		
		return "redirect:/11/fileUploadForm.jsp";
		
	}

}










