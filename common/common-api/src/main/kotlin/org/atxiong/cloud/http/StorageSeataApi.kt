package org.atxiong.cloud.http

import org.atxiong.cloud.common.entity.HttpResult
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@FeignClient("cloud-storage-seata-service", path = "storage")
interface StorageSeataApi {

    /**
     * 扣减库存
     * @param productId 商品ID
     * @param count 扣减数量
     * @return 结果集
     */
    @PostMapping("decrease")
    fun decrease(@RequestParam("productId") productId: Long, @RequestParam("count") count: Int): HttpResult<String>
}