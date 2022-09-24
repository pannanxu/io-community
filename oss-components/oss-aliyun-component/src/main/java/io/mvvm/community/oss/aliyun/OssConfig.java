package io.mvvm.community.oss.aliyun;

import lombok.Data;

/**
 * @description:
 * @author: pan
 **/
@Data
public class OssConfig {

    /**
     * Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
     */
    private String endpoint;
    /**
     * 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
     */
    private String accessKeyId;
    /**
     * accessKeySecret
     */
    private String accessKeySecret;
    /**
     * 填写Bucket名称，例如 examplebucket。
     */
    private String bucketName;
    /**
     * oss 附件访问域名
     */
    private String publicDomain;
    /**
     * 在 bucket 存储的路径, 例如填写: files/，那么上传的文件则会在：files/xx.jpg
     */
    private String objectNamePath;

}