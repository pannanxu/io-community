package io.mvvm.community.infra.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: Pan
 **/
@Schema(title = "地址模型")
@Data
public class Address {

    /**
     * 省
     */
    @Schema(title = "省")
    private Long provinceCode;

    /**
     * 市
     */
    @Schema(title = "市")
    private Long cityCode;

    @Schema(title = "区")
    private Long areaCode;

    /**
     * 省-名称
     */
    @Schema(title = "省-名称")
    private String provinceName;

    /**
     * 市-名称
     */
    @Schema(title = "市-名称")
    private String cityName;

    @Schema(title = "区-名称")
    private String areaName;

}