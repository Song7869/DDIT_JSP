package kr.or.ddit.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;

/**
 * #stream : 데이터의 흐름, "단방향" 이동 통로
 * 
 * #스트림을 이용한 I/O의 단계 1. 데이터의 출처가 되는 media를 객체화 (ex.File, Socket, byte[]...) 2.
 * media 에 맞는 단방향 스트림을 생성 (FileInput[Output]Stream, SocketInput[Output]Stream,
 * ByteArrayInput[Output]Stream) 3. EOF(EOS)까지 반복적인 Read/Write 작업 4. Close를 통해
 * media 매체를 release. 한다. (다음에 다시 열 수 있도록)
 * 
 * #스트림의 종류 1. 전송데이터의 크기에 따른 분류 1) byte stream (--Stream) :
 * FileInput[Output]Stream 2) character stream (--Reader/--Writer) :
 * FileReader/FileWriter
 * 
 * 2. 스트림 생성 방법에 따른 분류 1) 1차 스트림 : 생성자의 파라미터로 media 객체를 받아 생성한다. (파일에는 파일) new
 * FileInputStream (new File("path")) 2) 2차 스트림(연결형 스트림) : 또 다른 스트림 객체를 기반으로
 * 생성되는 스트림 (생성자의 파라미터로 스트림(연결)을 받음) (이미 1차 스트림이 미디어에 연결되어있어야함) new
 * BufferdInputStream( new FileInputStream (new File("path")) )
 * 
 * 3. 필터링구조 (전처리구조)에 따른 분류 (2차 스트림) DataInputStream( new FileInputStream (new
 * File("path")) ) : 데이터의 타입 그대로 움직인다. ObjectInputStream( new FileInputStream
 * (new File("path")) ) : 객체를 통으로 옮긴다.
 * 
 * 
 * @author pc12
 *
 */
public class StreamDesc2 {
	public static void main(String[] args) throws IOException {

		//simpleIOExample();
		
		
//		String cpRelativePath = "/kr/or/ddit/io/another day.txt";
		String cpRelativePath = "/kr/or/ddit/io/오래된 노래_utf8.txt";
//		String cpRelativePath = "/kr/or/ddit/io/오래된 노래.txt"; //ms949
		
//		String charset = "MS949";
//		String charset = "UTF-8";
		
//		simpleIOWithCharsetExample(cpRelativePath, charset);
		
		String webPath = "http://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
		URL url = new URL(webPath);
		
		
	}
	
	
	
	
		public static void simpleIOWithCharsetExample(String cpRelativePath, String charset) throws IOException {

			// <1>
			// 미디어 생성

			URL fileURL = StreamDesc.class.getResource(cpRelativePath); // 저 위치와 같은 위치에 있는 resource를 가져온다.

			String filePath = fileURL.getFile();
			System.out.println(filePath); // 경로중에 공백이 코딩되어 있음

			filePath = URLDecoder.decode(filePath, "UTF-8");
			System.out.println(filePath); // 경로중에 공백을 UTF-8로 디코딩함

			File file = new File(filePath);
			System.out.println(file.getCanonicalPath());

			// <2> 스트림 생성
			String tmp = null;
			// try with resource 구문 형태 (since 1.7)
			try (
					// closable 객체 생성 (try문이 수행 된 후 자동적으로 닫아진다.// close를 가지고 있는 것만 들어올 수 있음)

					// 1차 스트림 생성
					//FileReader fr = new FileReader(file);
					FileInputStream fis = new FileInputStream(file);
					
					InputStreamReader isr = new InputStreamReader(fis, charset); // 빨대 굵기 변환 어답터(옵션으로 인코딩 변환기능)
					
					
					// 2차 스트림 생성
					//BufferedReader reader = new BufferedReader(fr);
					//BufferedReader reader = new BufferedReader(fis); // 빨대 굵기가 달라서 컴파일에러
					BufferedReader reader = new BufferedReader(isr);

			// <4> media release
			) {

				// <3> EOS까지 반복
				// 출력하기
				while ((tmp = reader.readLine()) != null) {
					System.out.println(tmp);
				}
			}
	}

		
		
		
	public static void simpleIOExample() throws IOException {

		// <1>
		// 미디어 생성
//		String cpRelativePath = "/kr/or/ddit/io/another day.txt";
//		String cpRelativePath = "/kr/or/ddit/io/오래된 노래_utf8.txt";
		String cpRelativePath = "/kr/or/ddit/io/오래된 노래.txt";
		URL fileURL = StreamDesc.class.getResource(cpRelativePath); // 저 위치와 같은 위치에 있는 resource를 가져온다.

		String filePath = fileURL.getFile();
		System.out.println(filePath); // 경로중에 공백이 코딩되어 있음

		filePath = URLDecoder.decode(filePath, "UTF-8");
		System.out.println(filePath); // 경로중에 공백을 UTF-8로 디코딩함

		File file = new File(filePath);
		System.out.println(file.getCanonicalPath());

		// <2> 스트림 생성
		String tmp = null;
		// try with resource 구문 형태 (since 1.7)
		try (
				// closable 객체 생성 (try문이 수행 된 후 자동적으로 닫아진다.// close를 가지고 있는 것만 들어올 수 있음)

				// 1차 스트림 생성
				FileReader fr = new FileReader(file);
				
				// 2차 스트림 생성
				BufferedReader reader = new BufferedReader(fr);

		// <4> media release
		) {

			// <3> EOS까지 반복
			// 출력하기
			while ((tmp = reader.readLine()) != null) {
				System.out.println(tmp);
			}
		}

	}
}
