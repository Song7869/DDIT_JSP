package kr.or.ddit.servlet08;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@WebServlet("/fileUploadSpec2")
public class FileUploadServletSpec2 extends HttpServlet{
	String saveFolderURL = "/resources/images";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// 서블릿 스펙 2.5 방식으로 commons-fileupload 를 활용한 업로드
		HttpSession session = req.getSession();
		// 1. 서버측에 저장(웹리소스의 형태로)
		DiskFileItemFactory itemFactory = new DiskFileItemFactory();
		ServletFileUpload handler = new ServletFileUpload(itemFactory);
		 try {
			List<FileItem> fileItemList = handler.parseRequest(req);
			for(FileItem item : fileItemList) {
				String partName = item.getFieldName();
				Object value = null;
				if(item.isFormField()) {
					value = item.getString(req.getCharacterEncoding());
				}else {
					File saveFolder = new File(req.getServletContext().getRealPath(saveFolderURL));
					File saveFile = new File(saveFolder, item.getName());
					try(
						InputStream is = item.getInputStream();
					){
						FileUtils.copyInputStreamToFile(is, saveFile);
					}
					value = saveFolderURL + "/" + item.getName();
				}
				session.setAttribute(partName, value);
			}
		} catch (FileUploadException e) {
			throw new IOException(e);
		}
		// 2. 저장한 이미지의 URL 을 가지고, fileUploadForm.jsp 로 이동(redirect).
		resp.sendRedirect(req.getContextPath() + "/11/fileUploadForm.jsp");
		// 3. 업로더의 이름과 저장된 이미지를 랜더링.
	}
}
