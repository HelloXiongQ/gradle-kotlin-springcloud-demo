package org.atxiong.cloud.account.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.math.BigDecimal
import io.swagger.v3.oas.annotations.media.Schema

/**
 * <p>
 * 
 * </p>
 *
 * @author xkq
 * @since 2024-10-09
 */
@TableName("t_account")
@Schema(title = "Account对象", description = "")
class Account : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    @Schema(title = "用户ID")
    var userId: Long? = null

    @Schema(title = "总额度")
    var total: BigDecimal? = null

    @Schema(title = "已用账户余额")
    var used: BigDecimal? = null

    @Schema(title = "余额")
    var residue: BigDecimal? = null

    override fun toString(): String {
        return "Account{" +
        "id=" + id +
        ", userId=" + userId +
        ", total=" + total +
        ", used=" + used +
        ", residue=" + residue +
        "}"
    }
}
