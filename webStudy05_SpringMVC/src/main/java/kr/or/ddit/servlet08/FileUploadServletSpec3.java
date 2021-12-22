package kr.or.ddit.servlet08;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

import lombok.extern.slf4j.Slf4j;

@WebServlet("/fileUploadSpec3")
@MultipartConfig
@Slf4j
public class FileUploadServletSpec3 extends HttpServlet{
	String saveFolderURL = "/resources/images";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		//		Body -> N 개의 파트 (2.5 FileItem, 3.0이후부터 Part)
		String uploader = req.getParameter("uploader");
		session.setAttribute("uploader", uploader);
		Part filePart = req.getPart("uploadFile");
		log.info("upload file : {}", filePart);
		String originalFilename = parseDisposition(filePart);
		log.info("원본 파일명 : {}", originalFilename);
		String contentType = filePart.getContentType();
		if(contentType!=null)
			log.info("허용여부 : {}", contentType.startsWith("image/"));
		log.info("파일 크기 : {}", filePart.getSize());
		if(filePart.getSize()>0) {
			File saveFolder = new File(getServletContext().getRealPath(saveFolderURL));
			String saveName = UUID.randomUUID().toString();
			File saveFile = new File(saveFolder, saveName);
			String fileURL = saveFolderURL + "/" + saveName;
			try(
				InputStream is = filePart.getInputStream();
			){
				FileUtils.copyInputStreamToFile(is, saveFile);
				session.setAttribute("uploadFile", fileURL);
			}
		}
		
		resp.sendRedirect(req.getContextPath() + "/11/fileUploadForm.jsp");
		
	}
	private String parseDisposition(Part filePart) {
//		Content-Disposition: form-data; name="uploadFile"; filename="test.jpg"; asdfasdf
		String headerValue = filePart.getHeader("Content-Disposition");
		int fromIndex = headerValue.indexOf("filename=");
		String filename = null;
		if(fromIndex>=0) {
			int index = headerValue.indexOf("=", fromIndex);
			filename = headerValue.substring(index+1).split(";")[0].replaceAll("\"", "");
		}else {
			throw new IllegalArgumentException("일반 파라미터에 해당하는 파트는 파싱할 수 없음.");
		}
		return filename;
	}
}










