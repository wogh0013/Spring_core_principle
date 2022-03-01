package hello.core.filter;

import java.lang.annotation.*;

//@Component 내의 속성을 끌어옴
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
