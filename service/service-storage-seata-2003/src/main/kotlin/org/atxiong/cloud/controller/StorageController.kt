package org.atxiong.cloud.controller;

import org.atxiong.cloud.common.entity.HttpResult
import org.atxiong.cloud.service.impl.StorageServiceImpl
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping
import java.util.concurrent.TimeUnit

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xkq
 * @since 2024-10-09
 */
@Controller
@RequestMapping("storage")
class StorageController (
    private val storageService: StorageServiceImpl
) {

    @PostMapping("decrease")
    fun decrease(productId: Long, count: Int): HttpResult<String> {
        // 超时测试seata
        TimeUnit.SECONDS.sleep(60)

        storageService.decrease(productId, count)
        return HttpResult.success("扣减库存成功")
    }
}
