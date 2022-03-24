package ru.obvilion.api.db.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Default {
    String value() default "";

    String NEXT_VALUE() default "";

    boolean CURRENT_DATE() default false;
}
