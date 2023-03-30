package Check;

import org.springframework.validation.annotation.Validated;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/1/29 10:40
 */
@Validated
public class User {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
