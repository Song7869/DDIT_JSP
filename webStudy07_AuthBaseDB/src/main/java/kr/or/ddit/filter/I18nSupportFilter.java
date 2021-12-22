package kr.or.ddit.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.LocaleResolver;

public class I18nSupportFilter extends OncePerRequestFilter{
	@Inject
	LocaleResolver localeResolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		LocaleContextHolder.setLocale(localeResolver.resolveLocale(request));
		filterChain.doFilter(request, response);
		
	}

}

















