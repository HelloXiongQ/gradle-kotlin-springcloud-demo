package org.atxiong.cloud.handle

import com.alibaba.csp.sentinel.slots.block.BlockException

class SentinelTestHandler {

    companion object {

        // 需要编译成 java 纯 static 函数
        @JvmStatic
        fun handleException(blockException: BlockException): String {
            return "程序异常-------block"

        }

        @JvmStatic
        fun fallback(t: Throwable): String {
            return "程序异常-------fallback"
        }

        @JvmStatic
        fun handleParams(p1: String?, p2: String?, t: BlockException): String {
            return "程序异常-------Params BlockException"
        }
    }
}