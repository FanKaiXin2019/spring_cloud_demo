package fcy.config.redis;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.Ordered;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 10028
 * @@分布式锁注解@@第二步@@定义切面，给方法的执行上锁
 */
@Aspect
@Component
public class DistributeLockAop implements Ordered {
    @Autowired
    private RedissonClient redissonClient;

    @Pointcut(value = "@annotation(fcy.config.redis.DistributeLock)")
    public void pointCut(){

    }
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        DistributeLock annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(DistributeLock.class);
        String prefix = annotation.prefix();
        String lockValue = buildLockName(joinPoint, annotation.value());
        RLock lock = redissonClient.getLock(prefix+lockValue);
        try {
            lock.lock();
            return  joinPoint.proceed();
        } finally {
            lock.unlock();
        }
    }
    //解析参数
    private String buildLockName(JoinPoint pjp, String spel) {
        // 1. 如果 lock.value() 中不包含 #，则说明只是一个普通的字符串，直接返回作为锁的名字,
        //        如果包含#，从方法参数获取同名参数值作为锁的名字
        // 2. 获取被调用的方法以及它的参数名和参数值
        // 3. 把参数名和对应的参数值设置到 context 中
        // 4. 创建执行 SpEL 表达式, 并返回它的结果作为锁的名字

        if (!spel.contains("#")) {
            return spel;
        }

        // [2] 获取被调用的方法以及它的参数名和参数值
        Method method = ((MethodSignature) pjp.getSignature()).getMethod(); // 方法
        String[] params = discoverer.getParameterNames(method);               // 参数名
        Object[] args   = pjp.getArgs();                                      // 参数值
        EvaluationContext context = new StandardEvaluationContext();

        // [3] 把参数名和对应的参数值设置到 context 中
        if (params != null) {
            for (int len = 0; len < params.length; len++) {
                context.setVariable(params[len], args[len]);
            }
        }

        // [4] 创建执行 SpEL 表达式, 并返回它的结果作为锁的名字
        Expression expression = parser.parseExpression(spel);
        return expression.getValue(context, String.class);
    }
    private ExpressionParser parser = new SpelExpressionParser();
    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Override
    public int getOrder() {
        return 6;
    }
}
