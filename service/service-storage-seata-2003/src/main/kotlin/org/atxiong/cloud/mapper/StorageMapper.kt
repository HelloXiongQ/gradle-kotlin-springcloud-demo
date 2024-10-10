package org.atxiong.cloud.mapper;

import org.atxiong.cloud.storage.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xkq
 * @since 2024-10-09
 */
interface StorageMapper : BaseMapper<Storage> {
    fun decrease(productId: Long, count: Int)
}
