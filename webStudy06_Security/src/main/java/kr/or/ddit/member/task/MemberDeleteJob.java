package kr.or.ddit.member.task;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.member.dao.MemberDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MemberDeleteJob {

	@Inject
	private MemberDAO memberDAO;
	
	@Transactional
	public void realDelete() {
		log.info("탈퇴 회원들의 파생 레코드 삭제");
		log.info("탈퇴 회원들의 기본 레코드(멤버테이블) 삭제");
		Map<String, Object> pMap = new HashMap<>();
		memberDAO.realDeleteMember(pMap);
		log.info("{} 명이 탈퇴 되었음", pMap.get("rowcnt"));
	}
	
}
