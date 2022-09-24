package io.mvvm.community.sms.aliyun;

import io.mvvm.community.sms.core.SmsTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author: pan
 **/
@Data
public class AliSmsConfig {

    private String accessKeyId;
    private String accessKeySecret;
    private String securityToken;
    private String region = "cn-qingdao";
    private List<Template> templates;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Template {
        private String templateCode;
        private String signName;
        private String type;
    }

    public Template getTemplate(SmsTemplate<?> type) {
        return this.templates.stream().filter(e -> e.getType().equals(type.getType())).findFirst().orElseThrow(() -> new RuntimeException("暂无此短信推送模板类型: " + type.getName()));
    }
}