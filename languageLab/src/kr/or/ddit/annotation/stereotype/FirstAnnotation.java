package kr.or.ddit.annotation.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE,ElementType.FIELD})//클래스에서만 사용 할 어노테이션 + field에서도 사용가능해짐
@Retention(RetentionPolicy.SOURCE)//이 어노테이션이 실행되는 시점까지 살려둘 것
public @interface FirstAnnotation {
	//마커 어노테이션 -> 어노테이션이 붙어있는 그룹만 구분 -> 어노테이션 트레이싱
	

}
