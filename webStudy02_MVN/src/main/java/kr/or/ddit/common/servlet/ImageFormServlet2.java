package kr.or.ddit.common.servlet;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myimage.do")
public class ImageFormServlet2 extends HttpServlet{
	ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		String folderPath = application.getInitParameter("contentFolder");
		File folder = new File(folderPath);
		application.setAttribute("contentFolder", folder);
		
		String folderPath2 = application.getInitParameter("contentFolder2");
		File folder2 = new File(folderPath2);
		application.setAttribute("contentFolder2", folder2);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		2. 요청 분석
		
//		3. 모델 생성
		File folder = (File) application.getAttribute("contentFolder");
		
		File[] images = folder.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name){
				
				String mime = application.getMimeType(name);
				return mime != null && mime.startsWith("image/");
				
			}
		});
//		4. 모델 공유(전달)
		System.out.println(folder.exists() + ":" +   images);
		req.setAttribute("images", images);
		
		
		
		
		File folder2 = (File) application.getAttribute("contentFolder2");
		File[] images2 = folder2.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				
				String mime = application.getMimeType(name);
				return mime != null && mime.startsWith("image/");
			}
		});
		req.setAttribute("images2", images2);
		
		
		
//		5. 뷰를 선택
		String view = "/WEB-INF/views/fileManager.jsp";
//		6. 뷰로 이동
		req.getRequestDispatcher(view).forward(req, resp);
		
	}
}
