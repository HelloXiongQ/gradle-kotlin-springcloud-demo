package org.atxiong.cloud.payment.controller

import cn.hutool.core.util.IdUtil
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("pay/circuit")
class PayCircuitController {

    @GetMapping("{id}")
    fun myCircuit(@PathVariable id: Long, request: HttpServletRequest): String {
        with(request) {
            headerNames.asIterator().forEach {
                println("$it -> ${getHeader(it)}")
            }
        }

        if (id < 0L) throw RuntimeException("id 不能为 负数")

        if (id == 9999L) TimeUnit.SECONDS.sleep(5)

        return "received id: $id -> ${IdUtil.simpleUUID()}"
    }

    @PostMapping
    fun test(@RequestBody map: Map<String, Any>) = map.apply {
        println(this)
    }
}