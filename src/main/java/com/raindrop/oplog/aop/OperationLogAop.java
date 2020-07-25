package com.raindrop.oplog.aop;

import com.alibaba.fastjson.JSONObject;
import com.raindrop.oplog.anno.OpLog;
import com.raindrop.oplog.model.OperationLog;
import com.raindrop.oplog.service.OperationLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;


/**
 * @author wangliang
 * @date 2020/07/18
 */
@Aspect
@Configuration
public class OperationLogAop {

    /**
     * The key of user information in the Session
     */
    public static final String USER_ID_SESSION_KEY = "session:user:id";
    /**
     * Statistics request execute time
     */
    private final ThreadLocal<Date> time = new ThreadLocal<>();
    /**
     * Format date to standard pattern
     */
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private OperationLogService operationLogService;

    @Pointcut("@annotation(com.raindrop.oplog.anno.OpLog)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint jp) {
        Object result = null;
        try {
            time.set(new Date());
            result = jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        Method method = methodSignature.getMethod();
        // if don't need record operation log
        if (method.getAnnotation(OpLog.class) == null) {
            time.remove();
            return result;
        }

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // record operation log and release memory
        recordOperationLog(request, method, result);
        time.remove();
        return result;
    }

    /**
     * Record user operation log to db
     *
     * @param request
     * @param method
     * @param result
     */
    private void recordOperationLog(HttpServletRequest request, Method method, Object result) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(USER_ID_SESSION_KEY);
        OpLog opLog = method.getAnnotation(OpLog.class);
        OperationLog operationLog = new OperationLog();
        operationLog.setUserId(userId);
        operationLog.setOpIp(request.getRemoteAddr());
        operationLog.setOpDesc(opLog.value());
        operationLog.setOpType(opLog.type().getType());
        operationLog.setOpRequest(getRequestParameter(request));
        operationLog.setOpResult(String.valueOf(result));
        operationLog.setOpStartTime(df.format(time.get()));
        operationLog.setOpEndTime(df.format(new Date()));
        operationLogService.saveOperationLog(operationLog);
    }

    /**
     * Combination request parameters to json format
     *
     * @param request
     * @return
     */
    private String getRequestParameter(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = request.getParameter(key);
            jsonObject.put(key, value);
        }
        return jsonObject.toJSONString();
    }

}
