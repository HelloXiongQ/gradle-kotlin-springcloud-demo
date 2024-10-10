package org.atxiong.cloud.service.impl;

import org.atxiong.cloud.storage.entity.Storage;
import org.atxiong.cloud.mapper.StorageMapper;
import org.atxiong.cloud.service.IStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xkq
 * @since 2024-10-09
 */
@Service
open class StorageServiceImpl : ServiceImpl<StorageMapper, Storage>(), IStorageService {
    override fun decrease(productId: Long, count: Int) {
        println("------------->StorageService 开始扣减库存");
        baseMapper.decrease(productId, count)
        println("------------->StorageService 扣减库存结束");
    }

}
