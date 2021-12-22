package kr.or.ddit.task.spring;

import org.springframework.scheduling.annotation.Scheduled;


public class PrintNumberJob {

//	스프링배치는 아무것도 상속받을 필요가 없고, 받지 말아야 한다. (받으면 POJO 위반)
	
	private int number;
	
	/*
	 * fixedDelay : 작업 시작시점을 기준, 딜레이 간격  -> 시작과 시작사이 고정 // 작업의 실행-종료 여부 상관 안함
	 * fixedLate : 작업이 끝난 시점을 기준, 딜레이 간격
	 * cron : 초 분 시 일 월 요일 /default : 매1초 / 초 : 0 -> 매 분 정각, 분 : 0 -> 매 시 정각
	 * 0 30 5 * * * : 매일 새벽 5시 30분
	 * 0 30 5 * * mon : 매주 월요일 새벽 5시 30분
	*/
	
	@Scheduled(cron="0 0 5 * * mon") //매주 월요일 새벽 5시 정각
	public void execute() {
		System.out.printf("%d - %s\n",++number, Thread.currentThread().getName());
	}
}
