package kr.or.ddit.enumpkg;

/*public class BrowserType {
	public static final BrowserType CHROME = new BrowserType();
	public static final BrowserType EDGE = new BrowserType();
	
	private BrowserType() {
		
	}
}*/


public enum BrowserType {
	EDG("엣지"), CHROME("크롬"),  SAFARI("사파리"), OTHER("기타");
	private BrowserType(String name) {
		this.browserName = name;
	}
	private String browserName;
	
	public String getBrowserName() {
		return browserName;
	}
	
	
	public static BrowserType findBrowser(String agent) {
		agent = agent==null ? "" : agent.toUpperCase();
		
		BrowserType retValue = OTHER;
		
		for(BrowserType tmp :values()) {
			if(agent.contains(tmp.name())) {
				retValue = tmp;
				break;
			}
		}
		
		return retValue;
	}
	
	public static String findBrowserName(String agent) {
		return findBrowser(agent).getBrowserName();
				
	}
	
	
	
	
}
