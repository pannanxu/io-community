package io.mvvm.community.sms.aliyun;

import io.mvvm.community.sms.core.SmsTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: pan
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TestAliSmsTemplate implements SmsTemplate<TestAliSmsTemplate> {
    def("测试默认短信模板", "def")
    ;
    private String name;
    private String type;
}