package cn.lemongo97.aom.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * @author lipu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResult<T> {
    private Long total;

    private Long pageSize;

    private Long pageNum;

    private Collection<T> list;
}
