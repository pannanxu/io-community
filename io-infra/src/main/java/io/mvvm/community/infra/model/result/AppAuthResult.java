package io.mvvm.community.infra.model.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: pan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppAuthResult<T> extends AppSingleResult<T> {

    @Schema(title = "用户信息")
    private T user;
    @Schema(title = "Token")
    private String accessToken;
    @Schema(title = "授权类型(固定Bearer + 空格 + accessToken)")
    private String authType = "Bearer";

    public static <T> AppAuthResult<T> auth(T user, String accessToken) {
        AppAuthResult<T> result = new AppAuthResult<>();
        result.setUser(user);
        result.setAccessToken(accessToken);
        result.setSuccess(true);
        return result;
    }
}