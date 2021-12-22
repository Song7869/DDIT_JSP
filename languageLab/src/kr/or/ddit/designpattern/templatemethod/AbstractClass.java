package kr.or.ddit.designpattern.templatemethod;

public abstract class AbstractClass {
	
	public void LifeInDDIT() {
		FingerPrintCheck();
		StudyInClassRoom();
		FingerPrintOut();
	}
	
	private void FingerPrintCheck() {
		System.out.println("출첵!");
	}
	
	protected abstract void StudyInClassRoom();
	
	private void FingerPrintOut() {
		System.out.println("퇴첵!");
	}
	
}
