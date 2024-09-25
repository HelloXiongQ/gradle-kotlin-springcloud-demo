package org.atxiong.cloud.common.entity

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "统一数据返回对象")
data class HttpResult<T>(
    @Schema(title = "状态码") var code: Int = HttpStatusEnum.SUCCESS.code,
    @Schema(title = "状态信息") var msg: String = HttpStatusEnum.SUCCESS.message,
    @Schema(title = "返回数据") var data: T? = null
) {
    companion object {
        fun <T> success(data: T? = null): HttpResult<T> {
            return HttpResult(data = data)
        }

        fun <T> error(msg: String): HttpResult<T> {
            return HttpResult(code = HttpStatusEnum.FAIL.code, msg = msg)
        }

        fun <T> create(status: HttpStatusEnum, data: T? = null): HttpResult<T>{
            return HttpResult(
                code = status.code,
                msg = status.message,
                data = data
            )
        }
    }
}