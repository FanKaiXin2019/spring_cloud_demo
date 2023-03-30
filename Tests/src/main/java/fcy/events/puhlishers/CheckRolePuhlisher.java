package fcy.events.puhlishers;

import fcy.config.CheckRole;
import fcy.events.event.CheckRoleEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/16 15:49
 */
@Component
public class CheckRolePuhlisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    public void push(CheckRoleEvent checkRoleEvent){
        this.applicationEventPublisher.publishEvent(checkRoleEvent);
    }
}
