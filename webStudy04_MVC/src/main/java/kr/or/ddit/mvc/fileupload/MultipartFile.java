package kr.or.ddit.mvc.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 파일이 업로드 되고 있는 Part 에 대한 인터페이스
 */
public interface MultipartFile {
	/**
	 * 파일을 이진데이터의 배열형태로 읽기.
	 * @return
	 * @throws IOException 
	 */
	public byte[] getBytes() throws IOException;
	/**
	 * part name 반환
	 * @return
	 */
	public String getName();
	/**
	 * 원본 파일명 반환
	 * @return
	 */
	public String getOriginalFilename();
	/**
	 * 파일의 mime 반환
	 * @return
	 */
	public String getContentType();
	
	/**
	 * 실제 파일이 업로드 되는지 여부
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * 파일 크기 반환
	 * @return
	 */
	public long getSize();
	
	/**
	 * 파일 읽기를 위한 입력 스트림 
	 * @return
	 * @throws IOException 
	 */
	public InputStream getInputStream() throws IOException;
	
	/**
	 * 파일을 실제 저장 위치로 이동.
	 * @param dest
	 * @throws IOException 
	 */
	public void transferTo(File dest) throws IOException;
}












