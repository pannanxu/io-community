package io.mvvm.community.oss.core;

import java.io.InputStream;

/**
 * @description:
 * @author: pan
 **/
public interface IOss {

    /**
     * OSS 文件上传
     *
     * @param fileName 文件名称
     * @param file     文件
     * @return
     */
    OssResult upload(String fileName, InputStream file);

}