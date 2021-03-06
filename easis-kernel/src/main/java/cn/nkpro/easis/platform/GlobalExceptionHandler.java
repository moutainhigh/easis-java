package cn.nkpro.easis.platform;


import cn.nkpro.easis.co.NkComponentException;
import cn.nkpro.easis.exception.NkAccessDeniedException;
import cn.nkpro.easis.exception.NkDefineException;
import cn.nkpro.easis.exception.abstracts.NkCaution;
import cn.nkpro.easis.exception.abstracts.NkRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static Map<Class<? extends Throwable>,Integer> codes = new LinkedHashMap<>();

    static {
        // 用户操作错误或警告提示
        codes.put(NkCaution.class,400);
        codes.put(IllegalArgumentException.class,400);

        // 用户未登陆 或 token失效
        codes.put(AccessDeniedException.class,401);
        codes.put(AuthenticationException.class,401);

        // 没有权限，拒绝访问
        codes.put(NkAccessDeniedException.class,403);

        // 系统错误
        codes.put(NkComponentException.class,501);
        codes.put(NkDefineException.class,501);
        codes.put(NkRuntimeException.class,501);
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView errorHandler(HttpServletRequest request,
                                     HttpServletResponse response, Exception ex) {

        String message = ex.getMessage();

        if(StringUtils.isBlank(message)){
            if(ex instanceof NullPointerException){
                message = "空指针";
            }else{
                message = "未知错误";
            }
        }

        log.error(message,ex);

        response.setStatus(500);
        for(Class<?> type : codes.keySet()){
            if(ClassUtils.isAssignable(ex.getClass(),type)){
                response.setStatus(codes.get(type));
                break;
            }
        }

        MappingJackson2JsonView view = new MappingJackson2JsonView();
        Map<String, Object> attributes = new HashMap<>();

        attributes.put("code", response.getStatus());
        attributes.put("msg", message);
        attributes.put("url", request.getRequestURI().substring(request.getContextPath().length()));
        attributes.put("causeStackTrace", ExceptionUtils.getRootCauseStackTrace(ex));

        view.setAttributesMap(attributes);
        ModelAndView mav = new ModelAndView("error");
        mav.setView(view);
        return mav;
    }
}