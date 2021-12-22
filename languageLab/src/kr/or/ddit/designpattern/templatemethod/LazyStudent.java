package kr.or.ddit.designpattern.templatemethod;

public class LazyStudent extends AbstractClass{

	@Override
	protected void StudyInClassRoom() {
		System.out.println("지각 하고 강의실에서 공부중 ....");
	}

}
