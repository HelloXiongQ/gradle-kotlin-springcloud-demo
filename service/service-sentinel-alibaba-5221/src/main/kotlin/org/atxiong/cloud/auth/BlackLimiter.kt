package org.atxiong.cloud.auth

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component

// 可以提供流控规则的比对条件
@Component
class BlackLimiter: RequestOriginParser {
    override fun parseOrigin(p0: HttpServletRequest?): String {
        return p0!!.remoteAddr
    }
}