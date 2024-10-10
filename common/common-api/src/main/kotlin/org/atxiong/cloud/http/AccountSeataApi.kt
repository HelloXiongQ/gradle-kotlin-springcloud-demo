package org.atxiong.cloud.http

import org.atxiong.cloud.common.entity.HttpResult
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.math.BigDecimal

@FeignClient("cloud-account-seata-service", path = "account")
interface AccountSeataApi {

    /**
     * 余额扣减
     * @param userId 用户id
     * @param money 扣了多少钱
     * @return 结果集
     */
    @PostMapping("decrease")
    fun decrease(@RequestParam("userId") userId: Long, @RequestParam("money") money: BigDecimal): HttpResult<String>
}