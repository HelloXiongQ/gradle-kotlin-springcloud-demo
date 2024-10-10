package org.atxiong.cloud.storage.entity

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
@TableName("t_storage")
@Schema(title = "Storage对象", description = "")
class Storage : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    @Schema(title = "产品ID")
    var productId: Long? = null

    @Schema(title = "总库存")
    var total: BigDecimal? = null

    @Schema(title = "已用库存")
    var used: BigDecimal? = null

    @Schema(title = "剩余库存")
    var residue: BigDecimal? = null

    override fun toString(): String {
        return "Storage{" +
        "id=" + id +
        ", productId=" + productId +
        ", total=" + total +
        ", used=" + used +
        ", residue=" + residue +
        "}"
    }
}
