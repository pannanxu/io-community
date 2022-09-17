package io.mvvm.community.infra.security;

import io.mvvm.community.infra.exception.AppErrorCode;
import io.mvvm.community.infra.exception.AppException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @description: Spring Security 上下文工具
 * @author: Pan
 **/
@Component
public class WebSecurityContextHolder {

    /**
     * 获取安全上下文
     *
     * @return {@link SecurityContext}
     */
    public static SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    /**
     * 获取当前已认证的主体或认证请求令牌
     *
     * @return 如果没有可用的身份验证信息，则为null
     */
    public static Authentication getAuthentication() {
        return getSecurityContext().getAuthentication();
    }

    /**
     * 设置身份信息
     *
     * @param authentication 身份信息
     */
    public static void setAuthentication(Authentication authentication) {
        getSecurityContext().setAuthentication(authentication);
    }

    /**
     * 获取当前认证用户信息
     *
     * @return 用户信息
     */
    public static TokenBody getUserInfo() {
        Authentication authentication = getAuthentication();
        if (!(authentication.getPrincipal() instanceof TokenBody)) {
            throw new AppException(AppErrorCode.UNAUTHORIZED, "用户未登录");
        }
        return (TokenBody) authentication.getPrincipal();
    }

    public static Long getUserId() {
        return getUserInfo().getUserId();
    }

}