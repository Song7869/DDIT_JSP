package kr.or.ddit.task.simple;

import java.util.Timer;

public class SimpleThreadTest {
	public static void main(String[] args) {
		PrintNumberTask job = new PrintNumberTask();
		
//		job.run(); // 멀티스레드 아님!, 메인스레드 안에서 돌고 있음
//		
//		Thread thread = new Thread(job);
//		thread.start(); //운영스레드 2개 (main, 지금 이거)
//		
//		
//		
		
		Timer timer = new Timer();
		timer.schedule(job, 0, 1000); // 작업, 실행시작시간, 간격
		
		//잡과 스케쥴의 완전한 분리
		//스레드 터치 없음 -> threadpool
		//pool : 미리 만들어놓고 있다가, 필요하면 꺼내 쓰는것
	}
}
