package kr.or.ddit.designpattern;

import kr.or.ddit.designpattern.templatemethod.AbstractClass;
import kr.or.ddit.designpattern.templatemethod.DilligentStudent;
import kr.or.ddit.designpattern.templatemethod.LazyStudent;
import kr.or.ddit.designpattern.templatemethod.TemplateClass;

public class testView {
	public static void main(String[] args) {
		
		AbstractClass[] array = new AbstractClass[] {
				new DilligentStudent(), new LazyStudent()
		};
		
		
		for(AbstractClass obj : array) {
			obj.LifeInDDIT();
		}
		
		
	}
}
