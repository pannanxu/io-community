package io.mvvm.community.oss.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import io.mvvm.community.oss.core.IOss;
import io.mvvm.community.oss.core.OssResult;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @description:
 * @author: pan
 **/
@Slf4j
public class AliOss implements IOss {

    /**
     * 阿里云 OSS 配置项
     */
    private final OssConfig config;

    public AliOss(OssConfig config) {
        if (log.isDebugEnabled()) {
            log.info("AliOSS|init|{}",  config);
        }
        this.config = config;
    }

    @Override
    public OssResult upload(String fileName, InputStream file) {
        String bucketName = config.getBucketName();
        String objectName = getObjectName(fileName);
        OSS ossClient = null;
        try {
            int available = file.available();
            ossClient = buildOssClient();
            PutObjectResult result = ossClient.putObject(bucketName, objectName, file);
            return new OssResult(available, config.getPublicDomain() + objectName, objectName, null != result.getETag());
        } catch (Exception e) {
            log.error("OSS|上传文件异常|{}", e.getMessage(), e);
        } finally {
            if (null != ossClient) {
                ossClient.shutdown();
            }
        }
        return new OssResult();
    }

    private String getObjectName(String fileName) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(new Date());

        String[] split = fileName.split("\\.");
        String last = split[split.length - 1];

        String uuid = UUID.randomUUID().toString();

        return config.getObjectNamePath() + time + "/" + uuid + "." + last;
    }

    private OSS buildOssClient() {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = config.getEndpoint();
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = config.getAccessKeyId();
        String accessKeySecret = config.getAccessKeySecret();
        // 创建OSSClient实例。
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

}