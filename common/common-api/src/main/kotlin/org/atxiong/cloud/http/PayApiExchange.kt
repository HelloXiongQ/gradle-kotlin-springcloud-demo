package org.atxiong.cloud.http

import org.atxiong.cloud.common.entity.HttpResult
import org.atxiong.cloud.payment.dto.PayDTO
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange("pay")
interface PayApiExchange {
    @GetExchange("{id}")
    fun getOrder(@PathVariable id: Int): HttpResult<PayDTO>

    @PostExchange
    fun addOrder(@RequestBody pay: PayDTO): HttpResult<Unit>

    // 包超时的
    @GetExchange("/timeout")
    fun getTimeout(): HttpResult<Unit>
}