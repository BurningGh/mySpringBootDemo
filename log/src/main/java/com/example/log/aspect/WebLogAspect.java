package com.example.log.aspect;

import com.alibaba.fastjson.JSONObject;
import com.example.log.annotation.SysLog;
import com.example.log.model.Log;
import com.example.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Order(5)
@Component
@EnableAsync
@Slf4j
public class WebLogAspect {
    @Autowired
    private LogService logService;

    // 配置织入点
    @Pointcut("@annotation(com.example.log.annotation.SysLog)")
    public void logPointCut() {
    }

    /**
     * 前置通知 用于拦截操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        handleLog(joinPoint);
    }

    @Async
    protected void handleLog(final JoinPoint joinPoint) {
        try {
            // 获得注解
            SysLog controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            // *========数据库日志=========*//
            Log operLog = new Log();

            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");

            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, operLog);
            System.out.println(operLog);
            logService.add(operLog);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param controllerLog
     * @param operLog
     * @throws Exception
     */
    public void getControllerMethodDescription(SysLog controllerLog, Log operLog) throws Exception {
        // 设置action动作
        operLog.setAction(controllerLog.action());
        // 设置标题
        operLog.setTitle(controllerLog.title());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Map<String, String[]> map = attributes.getRequest().getParameterMap();
        String params = JSONObject.toJSONString(map);
        operLog.setParam(params);
        operLog.setIp("127.0.0.1");
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private SysLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(SysLog.class);
        }
        return null;
    }
}
