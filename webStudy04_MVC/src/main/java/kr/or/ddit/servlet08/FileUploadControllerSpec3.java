package kr.or.ddit.servlet08;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.mvc.fileupload.StandardMultipartFile;
import kr.or.ddit.mvc.fileupload.StandardMultipartHttpServletRequest;
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










