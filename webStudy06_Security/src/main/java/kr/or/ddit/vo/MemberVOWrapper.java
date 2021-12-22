package kr.or.ddit.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public class MemberVOWrapper extends User{
	
	private MemberVO authMember;
	
	public MemberVOWrapper(MemberVO authMember) {
		super(authMember.getMemId(), authMember.getMemPass(), AuthorityUtils.createAuthorityList(authMember.getMemRole()));
		this.authMember = authMember;
	}
	
	
	
	//enable : 허가제일때 (true면 접속 가능 : DB에 있어야 함) ex)mem_delete
	//accountNonExpired : 계정만료 여부
	//credentialNonExpired : 비밀번호 변경기간 만료
/*	
	public MemberVOWrapper(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	} */
	
	
	
	
}
