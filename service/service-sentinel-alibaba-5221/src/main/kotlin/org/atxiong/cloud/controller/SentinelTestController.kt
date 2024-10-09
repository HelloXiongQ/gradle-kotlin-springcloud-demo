package org.atxiong.cloud.controller

import com.alibaba.csp.sentinel.annotation.SentinelResource
import com.alibaba.csp.sentinel.slots.block.BlockException
import org.atxiong.cloud.common.utils.ServletUtils
import org.atxiong.cloud.handle.SentinelTestHandler
import org.atxiong.cloud.service.FlowLimitService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("sentinel")
class SentinelTestController (
    private val flowLimitService: FlowLimitService
) {

    @GetMapping("A")
    @SentinelResource(value = "A", blockHandler = "handleException", fallback = "fallback",
        fallbackClass = [SentinelTestHandler::class], blockHandlerClass = [SentinelTestHandler::class])
    fun testA(): String {
        return "hello Sentinel A"
    }

    @GetMapping("B")
    fun testB(): String {
        return "hello Sentinel B"
    }

    @GetMapping("C")
    fun testC(): String {
        flowLimitService.common()

        return "hello Sentinel C"
    }

    @GetMapping("D")
    fun testD(): String {
        flowLimitService.common()

        return "hello Sentinel D"
    }

    @GetMapping("E")
    fun testE(): String {
        TimeUnit.SECONDS.sleep(1)

        return "hello Sentinel E"
    }

    @GetMapping("F")
    fun testF(): String {
        // 制造异常
        throw RuntimeException("hello")
    }

    @GetMapping("params")
    @SentinelResource(value = "params", blockHandler = "handleParams" ,
        blockHandlerClass = [SentinelTestHandler::class] )
    fun testParams(p1: String?, p2: String? ): String {
        println(ServletUtils.getRequest().remoteAddr)
        return "hello Sentinel Params"
    }
}