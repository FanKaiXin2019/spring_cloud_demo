package Check;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = { CheckToFInfo.class })
public @interface ChekcToF {
    String message()

    default "不能带范";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
