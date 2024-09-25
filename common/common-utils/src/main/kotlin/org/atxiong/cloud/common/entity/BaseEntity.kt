package org.atxiong.cloud.common.entity

import com.alibaba.fastjson2.JSONWriter
import com.alibaba.fastjson2.annotation.JSONField
import com.baomidou.mybatisplus.annotation.TableField
import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable
import java.time.LocalDateTime

open class BaseEntity (
    @Schema(title = "其他字段")
    @TableField(exist = false)
    @JSONField(serializeFeatures = [JSONWriter.Feature.IgnoreEmpty])
    var otherParams: Map<String, String>? = null,

    @Schema(title = "创建时间")
    var createTime: LocalDateTime? = null,

    @Schema(title = "更新时间")
    var updateTime: LocalDateTime? = null

): Serializable