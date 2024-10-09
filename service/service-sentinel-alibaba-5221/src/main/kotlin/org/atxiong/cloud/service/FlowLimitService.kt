package org.atxiong.cloud.service

import com.alibaba.csp.sentinel.annotation.SentinelResource
import org.springframework.stereotype.Service

@Service
class FlowLimitService {

    @SentinelResource("common")
    fun common(): String {
        println("------------FlowLimitService come in")

        return "common"
    }
}