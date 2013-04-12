package com.gordondickens.javaconfig.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Gordon Dickens (dickeg01)
 */
@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(public * org.springframework.core.env..*.*(..))")
    public Object monitor(ProceedingJoinPoint joinPoint) throws Throwable {
        final String name = createJoinPointTraceName(joinPoint);
        // logger.debug("********  Entering method '{}'  ********", name);
        try {
            if (joinPoint.getArgs() != null) {
                for (Object value : joinPoint.getArgs()) {
                    logger.debug("{} Setting Value: '{}'", name, value);
//                    logger.debug(joinPoint.getTarget().getClass().getName() + "{}\n\t-Setting: '{}'", name, value);
                }
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
//        } finally {
//            logger.debug("********  Exiting method '{}'  ********", name);
        }
        return joinPoint.proceed();
    }

    private String createJoinPointTraceName(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        StringBuilder sb = new StringBuilder();
        sb.append(signature.getDeclaringType().getSimpleName());
        sb.append('.').append(signature.getName());
        return sb.toString();
    }
}

