package org.atxiong.cloud.controller

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import org.atxiong.cloud.common.entity.HttpResult
import org.atxiong.cloud.common.entity.HttpStatusEnum
import org.atxiong.cloud.http.PayApi
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit


@RestController
@RequestMapping("order/circuit")
class OrderCircuitController (
    private val client: PayApi
) {

    /**
     * 断路器机制 （根据调用报错比例，来拒绝访问）
     */
    @GetMapping("{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "fallbackMethod")
    fun myCircuitBreaker(@PathVariable id: Long): HttpResult<String> {
        return HttpResult.success(client.myCircuit(id))
    }

    /**
     * bulkhead 机制 (限制数量访问)
     */
    @GetMapping("bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "bulkheadBackMethod", type = Bulkhead.Type.SEMAPHORE)
    fun myBulkhead(@PathVariable id: Long): HttpResult<String> {
        return HttpResult.success(client.myCircuit(id))
    }

    /**
     * bulkhead 机制(限制数量访问)
     * ThreadPool 实现
     * 这个模式下只能处理 返回值为 CompletableFuture<T> 的方法
     */
    @GetMapping("bulkhead/thread/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadThreadPoolBackMethod", type = Bulkhead.Type.THREADPOOL)
    fun myBulkheadThreadPool(@PathVariable id: Long): CompletableFuture<String> {
        println("${Thread.currentThread().name} ----- 开始进入")
        TimeUnit.SECONDS.sleep(5)
        println("${Thread.currentThread().name} ----- 准备离开")

        return CompletableFuture.supplyAsync {
            println("${Thread.currentThread().name} ----- 开始进入")
            client.myCircuit(id) + " ---Bulkhead.Type.THREADPOOL"
        }
    }

    /**
     * 限流
     */
    @GetMapping("limit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "rateLimitBackMethod")
    fun myLimit(@PathVariable id: Long): HttpResult<String> {
        return HttpResult.success(client.myCircuit(id))
    }

    // myCircuitBreaker服务降级后置方案
    fun fallbackMethod(id: Long, t: Throwable): HttpResult<Unit> = HttpStatusEnum.LIMIT_REQUEST.getResult()

    // myBulkhead服务降级后置方案
    fun bulkheadBackMethod(id: Long, t: Throwable): HttpResult<Unit> = HttpStatusEnum.SLOW_REQUEST.getResult()

    // myBulkheadThreadPool服务降级后置方案
    fun myBulkheadThreadPoolBackMethod(id: Long, t: Throwable): CompletableFuture<HttpResult<Unit>> =
        CompletableFuture.supplyAsync { HttpStatusEnum.SLOW_REQUEST.getResult() }

    // myLimit服务降级后置方案
    fun rateLimitBackMethod(id: Long, t: Throwable): HttpResult<Unit> = HttpStatusEnum.RATE_LIMIT_REQUEST.getResult()
}