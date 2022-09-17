package io.mvvm.community.infra.exception;

/**
 * @author: Pan
 **/
public enum AppErrorCode implements IErrorCode {

    INVALID_PARAMS("INVALID_PARAMS", "参数错误"),
    TRY_LATER("TRY_LATER", "稍后再试"),
    FREQUENTLY_ERROR("FREQUENTLY_ERROR", "操作频繁，稍后再试"),
    UNKNOWN_ERROR("UNKNOWN_ERROR", "未知错误"),

    BIZ_ERROR("BIZ_ERROR", "业务异常"),
    UNAUTHORIZED("UNAUTHORIZED", "未登录"),
    AUTH_ERROR("AUTH_ERROR", "无权限"),
    AUTH_EXPIRED("AUTH_EXPIRED", "认证过期"),

    OTHER_DEVICE_LOGIN("OTHER_DEVICE_LOGIN", "账号在其他设备登录"),
    ;

    private final String errCode;
    private final String errDesc;

    AppErrorCode(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    @Override
    public String getErrCode() {
        return errCode;
    }

    @Override
    public String getErrDesc() {
        return errDesc;
    }
}