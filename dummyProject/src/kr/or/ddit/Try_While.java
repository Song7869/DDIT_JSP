package kr.or.ddit;

import java.util.Scanner;

public class Try_While {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String hit1;
		String hit2 = "타격";
		int life = 3;

		while(life > 0){
		System.out.println("타격을 입력해주세요: ");
		hit1 = scanner.nextLine();


			if (hit1.equals(hit2)) {
				life--;
				if (life == 0) {
					System.out.println("남은 라이프 : "+life);
					System.out.println("상대가 쓰러졌습니다 승리!");
					break;
				}
				System.out.println("남은 라이프 : "+life);
				System.out.println("계속해서 타격을 입력해주세요:");
			} 
			else {
				System.out.println("상대가 주먹을 회피했습니다 정확하게 '타격'을 입력해주세요!: ");
			}
			
		}
	}
}