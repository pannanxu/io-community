package io.mvvm.community.sms.aliyun;

import io.mvvm.community.sms.core.GenericPushInfo;
import io.mvvm.community.sms.core.ISmsPusher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class AliSmsPusherTest {

    private ISmsPusher pusher;

    @BeforeEach
    void setUp() {
        AliSmsConfig config = new AliSmsConfig();
        config.setAccessKeyId("TODO");
        config.setAccessKeySecret("TODO");

        config.setTemplates(new ArrayList<AliSmsConfig.Template>() {{
            add(new AliSmsConfig.Template("TODO", "TODO", TestAliSmsTemplate.def.getType()));
        }});
        pusher = new AliSmsPusher(config);
    }

    @Test
    void push() {
        GenericPushInfo info = new GenericPushInfo();
        info.setPhone("TODO");
        info.setParams("{\n" +
                "  \"code\": \"123456\"\n" +
                "}");
        boolean result = pusher.push(TestAliSmsTemplate.def, info);
        Assertions.assertTrue(result);
    }
}