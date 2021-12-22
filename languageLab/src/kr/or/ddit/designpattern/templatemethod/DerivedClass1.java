package kr.or.ddit.designpattern.templatemethod;

public class DerivedClass1 extends TemplateClass {

	@Override
	protected void stepTwo() {
		System.out.printf("%s 에서 2단계 실행!! \n", getClass().getSimpleName());
	}

}
