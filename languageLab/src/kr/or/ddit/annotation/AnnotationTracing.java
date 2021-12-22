package kr.or.ddit.annotation;

import kr.or.ddit.annotation.stereotype.FirstAnnotation;
import kr.or.ddit.annotation.stereotype.MultiValueAnnotation;
import kr.or.ddit.annotation.stereotype.SingleValueAnnotation;

/**
 * Annotation
 *	: 사람과 시스템에게 일정 정보를 전달 할 목적의 주석.
 *
 *	1. marker annotation
 *	2. single value annotation
 *	3. multi value annotation
 */
@FirstAnnotation
@SingleValueAnnotation("text")
//@MultiValueAnnotation(value="text", floatNumber=2.0f)
//@MultiValueAnnotation("text")
@MultiValueAnnotation
public class AnnotationTracing{
	@FirstAnnotation // 클래스에만 사용할 어노테이션이라 사용이 불가함
	String prop;
	
	public static void main(String[] args) {
		FirstAnnotation annotation =  AnnotationTracing.class.getAnnotation(FirstAnnotation.class);
		System.out.println(annotation);
		
		SingleValueAnnotation annotation2 = AnnotationTracing.class.getAnnotation(SingleValueAnnotation.class);
		System.out.println(annotation2.value());
	}
	
}
