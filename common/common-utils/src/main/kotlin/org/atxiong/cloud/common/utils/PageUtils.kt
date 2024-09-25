package org.atxiong.cloud.common.utils

import jakarta.servlet.http.HttpServletRequest
import org.atxiong.cloud.common.entity.PageConstant
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

/**
 * first: PageNum,
 * second: PageSize
 */
typealias PageEntity = Pair<Long, Long>

/**
 * 分页工具类
 */
class PageUtils {
    companion object {
        fun getPageObject(): PageEntity? {
            try {
                val num =  ServletUtils.getParameter(PageConstant.PAGE_NUM).toLong()
                val size = ServletUtils.getParameter(PageConstant.PAGE_SIZE).toLong()

                return PageEntity(
                    num, size
                )
            } catch (e: Exception) {
                return null
            }
        }
    }
}

class ServletUtils {

    companion object {
        private fun getRequest(): HttpServletRequest {
            return (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        }

        fun getParameter(name: String): String = getRequest().getParameter(name)
    }
}