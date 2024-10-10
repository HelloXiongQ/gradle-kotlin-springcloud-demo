package org.atxiong.cloud.controller;

import org.atxiong.cloud.common.entity.HttpResult
import org.atxiong.cloud.service.IAccountService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xkq
 * @since 2024-10-09
 */
@RestController
@RequestMapping("account")
class AccountController (
    private val iAccountService: IAccountService
) {

    @PostMapping("decrease")
    fun decrease(userId: Long, money: BigDecimal): HttpResult<String> {
        iAccountService.decrease(userId, money)
        return HttpResult.success("扣减余额成功")
    }
}
