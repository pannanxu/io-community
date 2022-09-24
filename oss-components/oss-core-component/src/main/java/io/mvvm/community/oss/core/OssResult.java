package io.mvvm.community.oss.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: OSS 文件上传返回
 * @author: pan
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OssResult {
    /**
     * 文件大小
     */
    private int size;
    /**
     * 文件地址: http[s]://example.com/${path}
     */
    private String url;
    /**
     * 文件存储路径: /dir/file.png
     */
    private String path;

    /**
     * 上传是否成功
     */
    private boolean success;

}