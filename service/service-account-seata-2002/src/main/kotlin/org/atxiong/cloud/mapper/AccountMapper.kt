package org.atxiong.cloud.mapper;

import org.atxiong.cloud.account.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param
import java.math.BigDecimal

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xkq
 * @since 2024-10-09
 */
interface AccountMapper : BaseMapper<Account> {
    fun decrease(@Param("userId") userId: Long,@Param("money") money: BigDecimal)
}
