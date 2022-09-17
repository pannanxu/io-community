package io.mvvm.community.infra.model.result;

import io.mvvm.community.infra.exception.AppErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: Pan
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AppErrorResult extends AppResult {

    public static AppErrorResult buildFailure(AppErrorCode code) {
        AppErrorResult result = new AppErrorResult();
        result.setErrCode(code.getErrCode());
        result.setErrMessage(code.getErrDesc());
        return result;
    }

    public static AppErrorResult buildFailure(String code, String desc) {
        AppErrorResult result = new AppErrorResult();
        result.setErrCode(code);
        result.setErrMessage(desc);
        return result;
    }

    public static AppErrorResult buildFailure(AppErrorCode code, String desc) {
        return buildFailure(code.getErrCode(), desc);
    }

}