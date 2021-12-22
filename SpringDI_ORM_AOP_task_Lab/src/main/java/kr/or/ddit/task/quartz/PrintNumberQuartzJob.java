package kr.or.ddit.task.quartz;

import org.springframework.scheduling.annotation.Scheduled;

public class PrintNumberQuartzJob {
	
		private int number;

		public void execute() {
			System.out.printf("%d - %s\n",++number, Thread.currentThread().getName());
		}
		
		
}
