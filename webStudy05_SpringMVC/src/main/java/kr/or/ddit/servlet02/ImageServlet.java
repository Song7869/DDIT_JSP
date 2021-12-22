package kr.or.ddit.servlet02;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.*;

//client 의 명령어 : http://localhost/dummy/image.do
//callback method

@WebServlet("/image.do")
public class ImageServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException, ServletException
	{
		
		File folder = (File) getServletContext().getAttribute("contentFolder");
		String child = req.getParameter("image");
		if(child==null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		File imageFile = new File(folder, child);
		if(!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		String mime = getServletContext().getMimeType(imageFile.getName());
		if(mime==null || !mime.startsWith("image/")) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		resp.setContentType(mime);
		
		FileInputStream fis = new FileInputStream(imageFile);
		byte[] buffer = new byte[1024];
		int count = -1;
		OutputStream os = resp.getOutputStream();
		while((count = fis.read(buffer)) != -1){
			os.write(buffer, 0, count);			
		}
		fis.close();
		os.flush();
		os.close();
	}
}
