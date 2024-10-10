package org.atxiong.cloud.service.impl;

import org.atxiong.cloud.account.entity.Account;
import org.atxiong.cloud.mapper.AccountMapper;
import org.atxiong.cloud.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.math.BigDecimal

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xkq
 * @since 2024-10-09
 */
@Service
open class AccountServiceImpl : ServiceImpl<AccountMapper, Account>(), IAccountService {
    override fun decrease(userId: Long, money: BigDecimal) {
        println("------------->AccountService 开始扣减余额")
        baseMapper.decrease(userId, money)
        println("------------->AccountService 开始扣减余额")
    }

}
