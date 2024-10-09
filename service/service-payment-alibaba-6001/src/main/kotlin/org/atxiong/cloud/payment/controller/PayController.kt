package org.atxiong.cloud.payment.controller

import com.alibaba.csp.sentinel.annotation.SentinelResource
import com.alibaba.csp.sentinel.slots.block.BlockException
import org.atxiong.cloud.common.controller.impl.BaseController
import org.atxiong.cloud.common.entity.HttpResult
import org.atxiong.cloud.payment.dto.PayDTO
import org.atxiong.cloud.payment.entity.Pay
import org.atxiong.cloud.payment.service.IPayService
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * <p>
 * 支付交易表 前端控制器
 * </p>
 *
 * @author xkq
 * @since 2024-07-27
 */
@RestController
@RequestMapping("/pay")
class PayController: BaseController<Pay, IPayService, PayDTO>(
    modelDTOClass = PayDTO::class
) {

    @SentinelResource("getOne", blockHandler = "blockHandler")
    override fun getOne(id: Long): HttpResult<PayDTO> {
        return super.getOne(id)
    }

    /**
     * 自定义服务流控处理方法
     */
    fun blockHandler(id: Long, blockException: BlockException): HttpResult<Unit> {
        return HttpResult.error("getOne 接口被限制操作")
    }

    // 定义超时接口
    @GetMapping("/timeout")
    fun getTimeout(): HttpResult<Unit> {
        Thread.sleep(1000 * 10)
        return HttpResult.success()
    }
}