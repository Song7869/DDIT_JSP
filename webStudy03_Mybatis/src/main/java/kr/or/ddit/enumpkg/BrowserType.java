package kr.or.ddit.enumpkg;

public enum BrowserType {
	EDG("엣지"), CHROME("크롬"), SAFARI("사파리"), OTHER("기타");
	private BrowserType(String name) {
		this.browerName = name;
	}
	private String browerName;
	public String getBrowerName() {
		return browerName;
	}
	
	public static BrowserType findBrowser(String agent) {
		agent = agent==null?"" : agent.toUpperCase();
		BrowserType retValue = OTHER;
		for(BrowserType tmp : values()) {
			if(agent.contains(tmp.name())) {
				retValue = tmp;
				break;
			}
		}		
		return retValue;
	}
	
	public static String findBrowserName(String agent) {
		return findBrowser(agent).getBrowerName();
	}
}











