package kr.or.ddit.mvc.fileupload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

public class StandardMultipartHttpServletRequest extends HttpServletRequestWrapper {

	private Map<String, List<MultipartFile>> fileMap;
	
	public StandardMultipartHttpServletRequest(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		fileMap = new LinkedHashMap<>();
		parseRequest(request);
	}
	
	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		Collection<Part> parts = request.getParts();
		for(Part single : parts) {
			String mime = single.getContentType();
			if(mime==null || mime.isEmpty()) continue;
			StandardMultipartFile file = new StandardMultipartFile(single);
			String partName = file.getName();
			List<MultipartFile> alreadyFiles = fileMap.get(partName);
			if(alreadyFiles==null) {
				alreadyFiles = new ArrayList<>();
				fileMap.put(partName, alreadyFiles);
			}
			alreadyFiles.add(file);
		}
	}

	public MultipartFile getFile(String name){
		List<MultipartFile> files = fileMap.get(name);
		MultipartFile file = null;
		if(files!=null && files.size()>0) {
			file = files.get(0);
		}
		return file;
	}
	
	public List<MultipartFile> getFiles(String name){
		return fileMap.get(name);
	}

	public Map<String, List<MultipartFile>> getFileMap() {
		return fileMap;
	}
	
	public Iterator<String> getFileNames(){
		return fileMap.keySet().iterator();
	}
}









