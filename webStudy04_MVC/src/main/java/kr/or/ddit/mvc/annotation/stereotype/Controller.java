package kr.or.ddit.mvc.annotation.stereotype;

import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;

@Target(TYPE)
@Retention(RUNTIME)
public @interface Controller {

}
