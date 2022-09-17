package io.mvvm.community.infra.model;

import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 公共分页实体
 *
 * @author: Pan
 **/
@Schema(title = "公共分页实体")
@Data
public class BaseSearch implements Serializable {
    @Schema(title = "当前页", example = "1")
    private int     page   = 1;
    @Schema(title = "页大小", example = "10")
    private int     size   = 10;
    @Schema(title = "偏移量", example = "10", hidden = true)
    private int     offset = 10;
    @Schema(title = "查询关键字", example = "百度")
    private String  keyword;
    private boolean hasPage = true;

    public int getOffset() {
        this.offset = (this.page - 1) * this.size;
        return this.offset;
    }

    public void startPage() {
        if (!hasPage) {
            return;
        }
        PageHelper.startPage(this.getPage(), this.getSize());
    }
}