package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

	@Inject
	private MemberDAO memberDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member = memberDAO.selectMemberForAuth(username);
		if(member==null) {
			throw new UsernameNotFoundException(username+"유저 없음");
		}
		return new MemberVOWrapper(member);
	}

}
