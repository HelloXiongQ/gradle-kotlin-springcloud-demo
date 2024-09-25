package org.atxiong.cloud.payment.dto

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable
import java.math.BigDecimal

/**
 * <p>
 * 支付交易表
 * </p>
 *
 * @author xkq
 * @since 2024-07-27
 */
@TableName("t_pay")
@Schema(title = "Pay对象", description = "支付交易表")
class PayDTO : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    @Schema(title = "支付流水号")
    var payNo: String? = null

    @Schema(title = "订单流水号")
    var orderNo: String? = null

    @Schema(title = "用户账号ID")
    var userId: Long? = null

    @Schema(title = "交易金额")
    var amount: BigDecimal? = null

    override fun toString(): String {
        return "Pay{" +
        "id=" + id +
        ", payNo=" + payNo +
        ", orderNo=" + orderNo +
        ", userId=" + userId +
        ", amount=" + amount +
        "}"
    }
}