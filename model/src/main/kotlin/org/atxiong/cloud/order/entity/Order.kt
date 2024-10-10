package org.atxiong.cloud.order.entity

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
@TableName("t_order")
@Schema(title = "Order对象", description = "")
class Order : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    @Schema(title = "用户ID")
    var userId: Long? = null

    @Schema(title = "产品ID")
    var productId: Long? = null

    @Schema(title = "数量")
    var count: Int? = null

    @Schema(title = "金额")
    var money: BigDecimal? = null

    @Schema(title = "订单状态: 0:创建中, 1:已完结")
    var status: Int? = null

    override fun toString(): String {
        return "Order{" +
        "id=" + id +
        ", userId=" + userId +
        ", productId=" + productId +
        ", count=" + count +
        ", money=" + money +
        ", status=" + status +
        "}"
    }
}
