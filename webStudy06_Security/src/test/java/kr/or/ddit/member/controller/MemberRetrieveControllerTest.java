package kr.or.ddit.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.awt.PageAttributes.MediaType;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.TestWebAppConfiguration;

@RunWith(SpringRunner.class)
@TestWebAppConfiguration
public class MemberRetrieveControllerTest {

	@Inject
	WebApplicationContext context;
	MockMvc mockMvc;
	
	
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		//가짜 목 MVC 환경 설정
		
		
	}

	@Test
	public void testMemberView() throws Exception {
		//의도적 400에러
/*		mockMvc.perform(get("/member/memberView.do"))
				.andExpect(status().isBadRequest()) // 테스트를 하려면 andExpect
				.andDo(log()) // 테스트중에 뭔가를 하려면
				.andReturn(); // 테스트를 끝내려면 andRetrun
*/		
		//정상 테스트
		mockMvc.perform(get("/member/memberView.do").param("who", "b001"))
		.andExpect(status().isOk()) // 테스트를 하려면 andExpect
		.andExpect(model().attributeExists("member"))
		.andExpect(view().name("member/ajax/memberView"))
		.andDo(log()) // 테스트중에 뭔가를 하려면
		.andReturn(); // 테스트를 끝내려면 andRetrun
		
	}
	
	@Test
	public void testListView() throws Exception {
		mockMvc.perform(get("/member/memberList.do").param("page", "2"))
				.andExpect(view().name("member/memberList"))
				.andReturn();
	}

	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/member/memberList.do").accept(org.springframework.http.MediaType.APPLICATION_JSON))
		.andExpect(content().contentType(org.springframework.http.MediaType.APPLICATION_JSON))
		.andDo(log())
		.andReturn();
	}

}
