package fcy.events.listeners;

import fcy.events.event.CheckRoleEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/16 15:51
 */
@Component
public class CheckRoleListener implements ApplicationListener<CheckRoleEvent> {
    @Override
    public void onApplicationEvent(CheckRoleEvent stringPayloadApplicationEvent) {
        System.out.println(stringPayloadApplicationEvent.getMsg());
    }
}
