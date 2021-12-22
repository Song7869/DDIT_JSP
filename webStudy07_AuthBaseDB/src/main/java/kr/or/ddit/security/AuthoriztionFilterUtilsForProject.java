package kr.or.ddit.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.User;

public class AuthoriztionFilterUtilsForProject {
	public static boolean pass(User user, String project_id) {
		return true;
	}
}
