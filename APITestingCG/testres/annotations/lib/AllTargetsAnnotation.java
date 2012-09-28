package lib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AllTargetsAnnotation {
    String text() default "Hello";
    int number();
}