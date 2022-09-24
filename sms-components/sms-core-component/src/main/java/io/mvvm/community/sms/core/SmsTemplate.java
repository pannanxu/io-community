package io.mvvm.community.sms.core;

/**
 * @description: sms 短信模板
 * @author: pan
 **/
public interface SmsTemplate<T extends Enum<T> & SmsTemplate<T>> {

    String getType();

    String getName();

}