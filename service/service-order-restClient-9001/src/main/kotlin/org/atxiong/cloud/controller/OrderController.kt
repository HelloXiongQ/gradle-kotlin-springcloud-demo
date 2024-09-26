package org.atxiong.cloud.controller

import org.atxiong.cloud.common.entity.HttpResult
import org.atxiong.cloud.common.entity.HttpStatusEnum
import org.atxiong.cloud.http.PayApiExchange
import org.atxiong.cloud.payment.dto.PayDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("consumer/order")
class OrderController(
    private val payApi: PayApiExchange
) {

    @PostMapping
    fun addOrder(@RequestBody payDTO: PayDTO): HttpResult<*>? {
        val res = payApi.addOrder(payDTO)

        if (res.code != HttpStatusEnum.SUCCESS.code) throw RuntimeException("pay新增服务调用失败")

        return res
    }

    @GetMapping("{id}")
    fun getOrder(@PathVariable id: Int): HttpResult<PayDTO> {
        val res = payApi.getOrder(id)

        if (res.code != HttpStatusEnum.SUCCESS.code) throw RuntimeException("pay查询id服务调用失败")

        return res
    }

    // 定义超时接口
    @GetMapping("/timeout")
    fun getTimeout(): HttpResult<Unit> {
        return kotlin.runCatching {
            payApi.getTimeout().apply {
                println("hello")
            }
        }.onFailure {
            throw it
        }.getOrNull()!!
    }
}