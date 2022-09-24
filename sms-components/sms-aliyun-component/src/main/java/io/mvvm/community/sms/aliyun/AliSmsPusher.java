package io.mvvm.community.sms.aliyun;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import darabonba.core.client.ClientOverrideConfiguration;
import io.mvvm.community.sms.core.PushInfo;
import io.mvvm.community.sms.core.ISmsPusher;
import io.mvvm.community.sms.core.SmsTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @description:
 * @author: pan
 **/
@Slf4j
public class AliSmsPusher implements ISmsPusher {

    private final AsyncClient client;
    private final AliSmsConfig config;

    public AliSmsPusher(AliSmsConfig config) {
        if (log.isDebugEnabled()) {
            log.info("阿里云短信|init|{}", config);
        }
        this.config = config;
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(config.getAccessKeyId())
                .accessKeySecret(config.getAccessKeySecret())
                .securityToken(config.getSecurityToken()) // use STS token
                .build());

        this.client = AsyncClient.builder()
                .region(config.getRegion()) // Region ID
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                        //.setReadTimeout(Duration.ofSeconds(30))
                )
                .build();
    }

    @Override
    public boolean push(SmsTemplate<?> template, PushInfo info) {
        AliSmsConfig.Template configTemplate = config.getTemplate(template);
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName(configTemplate.getSignName())
                .templateCode(configTemplate.getTemplateCode())
                .templateParam(info.getParams())
                .phoneNumbers(info.getPhone())
                // Request-level configuration rewrite, can set Http request parameters, etc.
                // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                .build();

        // Asynchronously get the return value of the API request
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        // Synchronously get the return value of the API request
        SendSmsResponse resp = null;
        try {
            resp = response.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("阿里云短信|推送异常|{}", e.getMessage(), e);
        }
        return null != resp && "OK".equals(resp.getBody().getCode());
    }
}