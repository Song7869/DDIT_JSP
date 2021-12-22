package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pc12
 *	서블릿 :
 *		웹상에서 발생하는 요청을 받고, 그에 따른 동적인 응답을 생성 할 수있는 객체에 대한 명세서(specification)
 *
 *	개발단계:
 *		1) HttpServlet을 상속받아 스펙에 따른 객체를 정의한다.
 *		2) 컴파일 진행 -> /WEB_INF/classes 에 클래스(.class)로 배포 된다.
 *		3) 서블릿 컨테이너(서버)에 해당 서블릿을 등록한다.
 *			2.5버전 : web.xml -> servlet ->  servlet-name -> servlet-class
 *			3.x버전 이상 : @WebServlet 이라는 어노테이션으로 등록 가능
 *		##서블릿 컨테이너 : 서블릿의 라이프사이클을 관리한다.
 *		
 *		4) 등록된 서블릿에 url-pattern을 매핑한다.
 *		5) 서버 리스타트(싱글톤을 업데이트하기 위해서)
 *
 *
 *	서블릿 컨테이너 : servlet의 lifecycle을 관리한다.
 *	생성 (init) : 컨테이너가 해당 서블릿에 매핑된 최초의 요청을 받았을 때 생성된다. load-on-startup 옵션으로 생성 시점을 앞당길 수 있다.
 *					-> init
 *		##따라서 단 한번(서버가 시행 될 때) 수행이 되야 하는 경우 init을 상속 받는다.
 *
 *	요청 : 요청의 처리순서 (service가 결정//template), request 의 method 에 따라 구체적인 작업은 (doXXX//hook)에서 시행된다.
 *	소멸 (destroy) : destroy가 callback 으로 호출되지만, 호출 구조는 장담 할 수 없다.
 *
 */
public class DescriptionServlet extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println(getClass().getName() + "이 생성됨.");
	}
	
	//##template method##
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service 메서드 호출됨");
//		super.service(req, resp);
		System.out.println("service 메서드 종료됨");
	}
	//##hook method##
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 메소드 호출됨");
	}
	
	
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println(getClass().getName() + "이 소멸됨.");
	}
}
