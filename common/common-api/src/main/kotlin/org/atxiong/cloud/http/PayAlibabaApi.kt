package org.atxiong.cloud.http

import org.atxiong.cloud.common.entity.HttpResult
import org.atxiong.cloud.payment.dto.PayDTO
import org.atxiong.cloud.sentinelHandler.PayAlibabaApiFallback
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@FeignClient("cloud-payment-alibaba-service", path = "pay", fallback = PayAlibabaApiFallback::class)
interface PayAlibabaApi {
    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: Int): HttpResult<PayDTO>

    @PostMapping
    fun addOrder(@RequestBody pay: PayDTO): HttpResult<Unit>

    // 包超时的
    @GetMapping("/timeout")
    fun getTimeout(): HttpResult<Unit>

    @GetMapping("/circuit/{id}")
    fun myCircuit(@PathVariable id: Long): String
}