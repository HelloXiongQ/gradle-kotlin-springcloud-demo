package org.atxiong.springcloud.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 支付交易表
 * </p>
 *
 * @author xkq
 * @since 2024-09-11
 */
@TableName("t_pay")
@ApiModel(value = "Pay对象", description = "支付交易表")
class Pay : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Int? = null

    @ApiModelProperty("支付流水号")
    var payNo: String? = null

    @ApiModelProperty("订单流水号")
    var orderNo: String? = null

    @ApiModelProperty("用户账号ID")
    var userId: Int? = null

    @ApiModelProperty("交易金额")
    var amount: BigDecimal? = null

    @ApiModelProperty("删除标志, 默认0不删除,1删除")
    var deleted: Int? = null

    @ApiModelProperty("创建时间")
    var createTime: LocalDateTime? = null

    @ApiModelProperty("更新时间")
    var updateTime: LocalDateTime? = null

    override fun toString(): String {
        return "Pay{" +
        "id=" + id +
        ", payNo=" + payNo +
        ", orderNo=" + orderNo +
        ", userId=" + userId +
        ", amount=" + amount +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}
