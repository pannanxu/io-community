package io.mvvm.community.oss.aliyun;

import io.mvvm.community.oss.core.IOss;
import io.mvvm.community.oss.core.OssResult;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class AliOssTest {

    private IOss oss;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        OssConfig config = new OssConfig();
        config.setEndpoint("https://oss-cn-beijing.aliyuncs.com");
        config.setAccessKeyId("TODO");
        config.setAccessKeySecret("TODO");
        config.setBucketName("TODO");
        config.setPublicDomain("TODO");

        oss = new AliOss(config);
    }

    @org.junit.jupiter.api.Test
    void upload() throws IOException {
        OssResult result = oss.upload("pom.xml", Files.newInputStream(Paths.get("/Users/pan/WORK/io-community/oss-components/oss-aliyun-component/pom.xml")));
        Assertions.assertTrue(result.isSuccess());
    }
}