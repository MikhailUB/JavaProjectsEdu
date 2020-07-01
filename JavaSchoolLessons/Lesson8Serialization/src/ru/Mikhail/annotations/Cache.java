package ru.Mikhail.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    CacheTypes cacheType() default CacheTypes.IN_MEMORY;
    String fileName() default "";
    boolean zip() default false;
    Class[] identityBy() default {};
}
