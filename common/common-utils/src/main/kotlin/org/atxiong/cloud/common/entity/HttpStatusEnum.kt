package org.atxiong.cloud.common.entity

enum class HttpStatusEnum (
    val code:  Int,
    val message: String,
) {
    SUCCESS(200, "请求成功"),
    FAIL(500, "请求失败"),
    SLOW_REQUEST(5001, "系统繁忙，请稍后再试"),
    LIMIT_REQUEST(5002, "系统错误，请稍后再试"),
    RATE_LIMIT_REQUEST(5003, "系统限流中，请稍后再试");

    fun <T> getResult(message: String? = null, data: T? = null): HttpResult<T> {
        return HttpResult(
            code = this.code,
            msg = message ?: this.message,
            data = data
        )
    }
}