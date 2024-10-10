package org.atxiong.cloud.service;

import org.atxiong.cloud.account.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import java.math.BigDecimal

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkq
 * @since 2024-10-09
 */
interface IAccountService : IService<Account> {
    fun decrease(userId: Long, money: BigDecimal)
}
