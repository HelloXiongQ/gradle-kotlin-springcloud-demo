package org.atxiong.cloud.sentinelHandler

import org.atxiong.cloud.common.entity.HttpResult
import org.atxiong.cloud.http.PayAlibabaApi
import org.atxiong.cloud.payment.dto.PayDTO
import org.springframework.stereotype.Component

/**
 * sentinel 集成 feign 的 统一 fallback
 */
@Component
class PayAlibabaApiFallback: PayAlibabaApi {
    override fun getOrder(id: Int): HttpResult<PayDTO> {
        return HttpResult.error("getOrder 服务不可用！")
    }

    override fun addOrder(pay: PayDTO): HttpResult<Unit> {
        return HttpResult.error("addOrder 服务不可用！")
    }

    override fun getTimeout(): HttpResult<Unit> {
        return HttpResult.error("getTimeout 服务不可用！")
    }

    override fun myCircuit(id: Long): String {
        return "myCircuit 服务不可用！"
    }
}