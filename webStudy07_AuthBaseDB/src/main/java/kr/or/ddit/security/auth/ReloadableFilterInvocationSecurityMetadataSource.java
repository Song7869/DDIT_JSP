package kr.or.ddit.security.auth;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.context.WebApplicationContext;

/**
 * 동적으로 변경될 수 있는 역할별 접근 제어 설정을 리로드 할 수 있는 metadataSource 
 * 
 */
public class ReloadableFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
	@Inject
	private WebApplicationContext container;
	
	private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

	public ReloadableFilterInvocationSecurityMetadataSource(
			LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
		this.requestMap = requestMap;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
				.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}

		return allAttributes;
	}

	public Collection<ConfigAttribute> getAttributes(Object object) {
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
				.entrySet()) {
			if (entry.getKey().matches(request)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
	
	public void reload() {
		LinkedHashMap<RequestMatcher, List<ConfigAttribute>> reloadedRequestMap 
									= container.getBean("requestMap", LinkedHashMap.class);
		synchronized (requestMap) {
			requestMap.clear();
			requestMap.putAll(reloadedRequestMap);
		}
	}
}
