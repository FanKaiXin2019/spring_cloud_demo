package fcy.config;

import java.lang.annotation.*;

/**
 * @author wbbaijq
 * 注解@Target 此注解的作用目标，括号里METHOD的意思说明此注解只能加在方法上面
 * 注解@Retention 注解的保留位置，括号里RUNTIME的意思说明注解可以存在于运行时，可以用于反射
 * 注解@Documented 说明该注解将包含在javadoc中
 * <p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckRole {

}