import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test.do")
public class TestServlet extends HttpServlet{
	
	ServletContext application;
	
	@Override
	public void init() throws ServletException {
		application = getServletContext();
	}
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String folderPath = "d:/contents";
		File folder = new File(folderPath);
		File[] images = folder.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name){
				String mime = application.getMimeType(name);
				return mime!=null && mime.startsWith("image/");
			}
		});
		
		req.setAttribute("images", images);
		
		req.getRequestDispatcher("/WEB-INF/views/imageForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("여기까지 옴");

	}
	

	
	
}
