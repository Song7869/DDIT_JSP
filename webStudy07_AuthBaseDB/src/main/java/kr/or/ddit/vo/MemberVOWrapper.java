package kr.or.ddit.vo;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class MemberVOWrapper extends User{
	private MemberVO authMember;

	public MemberVOWrapper(MemberVO authMember) {
		super(authMember.getMemId(), authMember.getMemPass(), 
					AuthorityUtils.createAuthorityList(authMember.getMemRoles().toArray(new String[authMember.getMemRoles().size()])));
		this.authMember = authMember;
	}

	

}
