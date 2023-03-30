package Check;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/1/29 10:21
 */
public class CheckToFInfo implements ConstraintValidator<ChekcToF, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        int index = s.indexOf("范");
        if (index == -1){
            return false;
        }else{
            return true;
        }
    }
}
