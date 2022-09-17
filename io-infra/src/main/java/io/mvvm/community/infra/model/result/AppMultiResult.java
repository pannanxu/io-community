package io.mvvm.community.infra.model.result;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static io.mvvm.community.infra.exception.AppErrorCode.TRY_LATER;

/**
 * @author: Pan
 **/
@Schema(title = "分页统一响应")
@EqualsAndHashCode(callSuper = true)
@Data
public class AppMultiResult<T> extends AppResult {

    @Schema(title = "总数量")
    private long    total;
    @Schema(title = "查询数据")
    private List<T> data;

    public static <T> AppMultiResult<T> of(List<T> data) {
        AppMultiResult<T> tAppMultiResult = new AppMultiResult<>();
        tAppMultiResult.setSuccess(true);
        tAppMultiResult.setData(data);
        if (data instanceof Page) {
            PageInfo<T> page = buildPageInfo(data);
            tAppMultiResult.setTotal(page.getTotal());
        }
        return tAppMultiResult;
    }

    public static <T> AppMultiResult<T> of(List<T> data, long total) {
        AppMultiResult<T> tAppMultiResult = new AppMultiResult<>();
        tAppMultiResult.setSuccess(true);
        tAppMultiResult.setData(data);
        tAppMultiResult.setTotal(total);
        return tAppMultiResult;
    }

    private static <T> PageInfo<T> buildPageInfo(List<T> data) {
        return new PageInfo<T>(data);
    }

    /**
     * 数据更新中，稍后再试
     * 客户端对此类错误不做页面刷新等其他操作
     */
    public static <T> AppMultiResult<T> tryLater() {
        AppMultiResult<T> multiResult = new AppMultiResult<>();
        multiResult.setSuccess(false);
        multiResult.setErrCode(TRY_LATER.getErrCode());
        multiResult.setErrMessage(TRY_LATER.getErrDesc());
        return multiResult;
    }

    public static <T> AppMultiResult<T> empty() {
        AppMultiResult<T> multiResult = new AppMultiResult<>();
        multiResult.setSuccess(false);
        return multiResult;
    }
}