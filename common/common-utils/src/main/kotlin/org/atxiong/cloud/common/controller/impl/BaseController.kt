package org.atxiong.cloud.common.controller.impl

import com.baomidou.mybatisplus.extension.service.IService
import jakarta.annotation.Resource
import org.atxiong.cloud.common.controller.OperableInterface
import org.atxiong.cloud.common.controller.SearchableInterface
import org.atxiong.cloud.common.entity.BaseEntity
import org.springframework.beans.factory.annotation.Autowired
import kotlin.reflect.KClass

/**
 * 集成所有增删改查
 */
open class BaseController<MODEL : BaseEntity, SERVICE : IService<MODEL>, MODEL_DTO : Any>(
    @set:Resource override var operableService: SERVICE? = null,
    @set:Resource override var searchService: SERVICE? = null,
    override var modelDTOClass: KClass<MODEL_DTO>,
) : OperableInterface<MODEL, SERVICE>, SearchableInterface<MODEL, SERVICE, MODEL_DTO>


/**
 * 实现增删改
 */
open class OperableController<MODEL : BaseEntity, SERVICE : IService<MODEL>>(
    @set:Resource override var operableService: SERVICE? = null,
) : OperableInterface<MODEL, SERVICE>


/**
 * 实现查询功能
 */
open class SearchController<MODEL : BaseEntity, SERVICE : IService<MODEL>, MODEL_DTO : Any>(
    @set:Resource override var searchService: SERVICE? = null,
    override var modelDTOClass: KClass<MODEL_DTO>,
) : SearchableInterface<MODEL, SERVICE, MODEL_DTO>