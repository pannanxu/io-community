package io.mvvm.community.infra.model.result;

import io.mvvm.community.infra.exception.AppErrorCode;
import io.mvvm.community.infra.trace.TraceIdUtil;
import io.mvvm.community.infra.utils.Json;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Pan
 **/
@Schema(title = "统一响应")
@Data
@Accessors(chain = true)
public class AppResult {
    private static final long serialVersionUID = 1L;

    @Schema(title = "成功")
    private boolean success;
    @Schema(title = "失败code")
    private String  errCode;
    @Schema(title = "失败原因")
    private String  errMessage;
    @Schema(title = "请求ID")
    private String  requestId = TraceIdUtil.getTraceId();

    public static AppResult buildFailure(String errCode, String errMessage) {
        AppResult appResult = new AppResult();
        appResult.setSuccess(false);
        appResult.setErrCode(errCode);
        appResult.setErrMessage(errMessage);
        return appResult;
    }

    public static AppResult buildSuccess() {
        AppResult appResult = new AppResult();
        appResult.setSuccess(true);
        return appResult;
    }

    public static AppResult buildFailure(AppErrorCode appErrorCode) {
        AppResult appResult = new AppResult();
        appResult.setSuccess(false);
        appResult.setErrCode(appErrorCode.getErrCode());
        appResult.setErrMessage(appErrorCode.getErrDesc());
        return appResult;
    }

    @Override
    public String toString() {
        return Json.toJsonString(this);
    }
}