package io.mvvm.community.infra.security;

import io.mvvm.community.infra.SpringContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 * @author: pan
 **/
public class PasswordUtils {

    private static final PasswordEncoder PASSWORD_ENCODER = SpringContextHolder.getBean(PasswordEncoder.class);

    public static String encode(String raw) {
        return PASSWORD_ENCODER.encode(raw);
    }

    public static boolean matches(String raw, String encode) {
        return PASSWORD_ENCODER.matches(raw, encode);
    }

}