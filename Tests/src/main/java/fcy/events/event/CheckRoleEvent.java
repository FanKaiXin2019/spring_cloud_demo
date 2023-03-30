package fcy.events.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/16 15:56
 */
public class CheckRoleEvent extends ApplicationEvent {
    private String msg;
    public CheckRoleEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
