package io.mvvm.community.sms.core;

import lombok.Data;

/**
 * @description: 推送
 * @author: pan
 **/
@Data
public class GenericPushInfo implements PushInfo {

    /**
     * 推送手机号
     */
    private String phone;

    /**
     * 推送参数
     */
    private String params;

}