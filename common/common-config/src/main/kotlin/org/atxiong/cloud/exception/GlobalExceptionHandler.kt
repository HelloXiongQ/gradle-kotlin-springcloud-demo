package org.atxiong.cloud.exception

import org.atxiong.cloud.common.entity.HttpResult
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception): HttpResult<Unit> {
        e.printStackTrace()
        return HttpResult.error(e.message!!)
    }
}