package org.atxiong.cloud.service;

import org.atxiong.cloud.storage.entity.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkq
 * @since 2024-10-09
 */
interface IStorageService : IService<Storage> {
    fun decrease(productId: Long, count: Int)
}
