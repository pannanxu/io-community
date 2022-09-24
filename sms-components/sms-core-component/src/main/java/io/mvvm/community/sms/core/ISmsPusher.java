package io.mvvm.community.sms.core;

/**
 * @description:
 * @author: pan
 **/
public interface ISmsPusher {

    /**
     * 短信推送
     *
     * @param template 推送模板
     * @param info     推送目标信息, 可以看通用的: {@link GenericPushInfo}
     * @return 推送成功返回true，否则返回false
     */
    boolean push(SmsTemplate<?> template, PushInfo info);

}