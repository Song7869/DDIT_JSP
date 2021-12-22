package kr.or.ddit.task.simple;

import java.util.TimerTask;

public class PrintNumberTask extends TimerTask{
// runnable -> job

	private int number;
	
	@Override
	public void run() {
			System.out.println(++number);
	}
	
	// job start 스레드 시작
	// 스레드는 이 작업이 CPU 할당 받을 수 있는 최소한의 구조 , 스레드 == 작업은 아니다.
	
	
}
