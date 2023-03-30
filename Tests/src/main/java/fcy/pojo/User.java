package fcy.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * com.oak.springbootredis -> User
 *
 * @Author: guoxy
 * @Description:
 * @Date: 2022/4/26
 */
@Data
@Accessors(chain = true)
public class User {

    private String userId;
    private String idCard;
    private String name;
    private String nickname;

}