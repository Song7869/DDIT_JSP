package kr.or.ddit.designpattern;

import kr.or.ddit.designpattern.templatemethod.DerivedClass1;
import kr.or.ddit.designpattern.templatemethod.DerivedClass2;
import kr.or.ddit.designpattern.templatemethod.TemplateClass;

public class TemplateMethodPatternView {
	public static void main(String[] args) {
		
		TemplateClass[] array = new TemplateClass[] {
				new DerivedClass1(), new DerivedClass2()
		};
		
		for(TemplateClass obj : array) {
			obj.template();
		}
		
	}
}
