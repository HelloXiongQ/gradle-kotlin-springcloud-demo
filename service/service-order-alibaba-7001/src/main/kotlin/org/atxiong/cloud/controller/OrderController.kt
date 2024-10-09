package org.atxiong.cloud.controller

import jakarta.annotation.Resource
import org.atxiong.cloud.common.entity.HttpResult
import org.atxiong.cloud.common.entity.HttpStatusEnum
import org.atxiong.cloud.http.PayAlibabaApi
import org.atxiong.cloud.payment.dto.PayDTO
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("consumer/order")
class OrderController(
    @Qualifier("org.atxiong.cloud.http.PayAlibabaApi") private val payApi: PayAlibabaApi,
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

    @GetMapping("test")
    fun getValue(@Value("\${order.info}") a: String): String {
        return a
    }

    // 定义超时接口
    @GetMapping("/timeout")
    fun getTimeout(): HttpResult<Unit> {
        return kotlin.runCatching {
            payApi.getTimeout()
        }.onFailure {
            throw it
        }.getOrNull()!!
    }
}