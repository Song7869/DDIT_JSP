package kr.or.ddit.mvc.fileupload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

public class StandardMultipartFile implements MultipartFile {
	private Part part;
	public StandardMultipartFile(Part part) {
		super();
		this.part = part;
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
	
	@Override
	public byte[] getBytes() throws IOException {
		byte[] bytes = IOUtils.toByteArray(getInputStream());
//		try(
//			InputStream is = getInputStream();
//			ByteArrayOutputStream os = new ByteArrayOutputStream(getSize());	
//		){
//			IOUtils.copy(is, os);
//			bytes = os.toByteArray();
//		}
		return bytes;
	}

	@Override
	public String getName() {
		return part.getName();
	}

	@Override
	public String getOriginalFilename() {
		return parseDisposition(part);
	}

	@Override
	public String getContentType() {
		return part.getContentType();
	}

	@Override
	public boolean isEmpty() {
		return part.getSize()==0;
	}

	@Override
	public long getSize() {
		return part.getSize();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return part.getInputStream();
	}

	@Override
	public void transferTo(File dest) throws IOException {
		part.write(dest.getCanonicalPath());

	}

}
