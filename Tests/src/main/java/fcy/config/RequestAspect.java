package fcy.config;

import fcy.events.event.CheckRoleEvent;
import fcy.events.puhlishers.CheckRolePuhlisher;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * RequestAspect
 */
@Component
@Aspect
public class RequestAspect {
    @Autowired
    CheckRolePuhlisher checkRolePuhlisher;
    private static final Logger logger = LoggerFactory.getLogger(RequestAspect.class);

    /**
     * 重定向到登陆页
     */
    private static final String REDIRECT = "www.baidu.com";


    /**
     * 表示在执行被@CheckRole注解修饰的方法之前 会执行doBefore()方法
     */
    @Pointcut("@annotation(fcy.config.CheckRole)")
    public void log() {
        // AOP的切点。不需要方法体
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before(value = "log()")
    public void doBefore(JoinPoint joinPoint) throws IOException {
        //获取到请求的属性
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 避免空指针异常
        if (attributes == null) {
            return;
        }
        //获取到请求对象
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        checkRolePuhlisher.push(new CheckRoleEvent("checkRole", "这是监听模式打印"));
        logger.error("触发切面");
        response.sendRedirect(REDIRECT);
    }


}
