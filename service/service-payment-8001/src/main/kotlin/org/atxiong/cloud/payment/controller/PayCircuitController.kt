package org.atxiong.cloud.payment.controller

import cn.hutool.core.util.IdUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("pay/circuit")
class PayCircuitController {

    @GetMapping("{id}")
    fun myCircuit(@PathVariable id: Long): String {
        if (id < 0L) throw RuntimeException("id 不能为 负数")

        if (id == 9999L) TimeUnit.SECONDS.sleep(5)

        return "received id: $id -> ${IdUtil.simpleUUID()}"
    }
}