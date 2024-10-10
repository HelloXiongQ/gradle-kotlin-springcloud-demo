package org.atxiong.cloud.controller;

import org.atxiong.cloud.common.entity.HttpResult
import org.atxiong.cloud.order.entity.Order
import org.atxiong.cloud.service.impl.OrderServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xkq
 * @since 2024-10-09
 */
@RestController
@RequestMapping("order")
class OrderController (
    private val orderService: OrderServiceImpl
) {
    @GetMapping("create")
    fun create(order: Order): HttpResult<Order> {
        orderService.create(order)
        return HttpResult.success(order)
    }

    @GetMapping("hello")
    fun test(id: Int): HttpResult<Int> {
        return HttpResult.success(id)
    }
}
