package org.atxiong.cloud.common.controller

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.IService
import io.swagger.v3.oas.annotations.Operation
import org.atxiong.cloud.common.entity.BaseEntity
import org.atxiong.cloud.common.entity.HttpResult
import org.atxiong.cloud.common.utils.PageUtils
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

interface SearchableInterface<MODEL : BaseEntity, SERVICE : IService<MODEL>, MODEL_DTO : Any> {

    var searchService: SERVICE?

    var modelDTOClass: KClass<MODEL_DTO>

    @ResponseBody
    @GetMapping("list")
    @Operation(summary = "基础功能-查询列表信息")
    fun list(model: MODEL): HttpResult<List<MODEL_DTO>> {
        searchService ?: throw Exception("SearchService为空")

        // 是否需要分页
        val pageObject = PageUtils.getPageObject() ?: return HttpResult.success(
            searchService!!.list(KtQueryWrapper(model))
                .map { searchModel ->
                    // 转化为对应DTO
                    val modelDto = modelDTOClass.createInstance()
                    BeanUtils.copyProperties(searchModel, modelDto)
                    return@map modelDto
                }
        )

        return HttpResult.success(
            searchService!!.list(
                Page(pageObject.first, pageObject.second),
                KtQueryWrapper(model)
            ).map { searchModel ->
                val modelDto = modelDTOClass.createInstance()

                BeanUtils.copyProperties(searchModel, modelDto)

                return@map modelDto
            }
        )
    }

    @ResponseBody
    @GetMapping("{id}")
    @Operation(summary = "基础功能-通过ID查询记录")
    fun getOne(@PathVariable id: Long): HttpResult<MODEL_DTO> {
        searchService ?: throw Exception("operableService为空")

        return HttpResult.success(
            searchService?.getById(id).let {
                val modelDto = modelDTOClass.createInstance()

                BeanUtils.copyProperties(it!!, modelDto)

                return@let modelDto
            }
        )
    }

    @ResponseBody
    @GetMapping("count")
    @Operation(summary = "基础功能-获取总记录数")
    fun count(): HttpResult<Long> {
        searchService ?: throw Exception("operableService为空")

        return HttpResult.success(searchService?.count())
    }
}


interface OperableInterface<MODEL : BaseEntity, SERVICE : IService<MODEL>> {

    var operableService: SERVICE?

    @ResponseBody
    @PostMapping
    @Operation(summary = "基础功能-新增记录")
    fun save(@RequestBody model: MODEL): HttpResult<Unit> {
        operableService ?: throw Exception("operableService为空")

        operableService!!.save(model)
        return HttpResult.success()
    }

    @ResponseBody
    @PutMapping
    @Operation(summary = "基础功能-更新记录")
    fun update(@RequestBody model: MODEL): HttpResult<Unit> {
        operableService ?: throw Exception("operableService为空")

        operableService!!.saveOrUpdate(model)
        return HttpResult.success()
    }

    @ResponseBody
    @DeleteMapping("{ids}")
    @Operation(summary = "基础功能-通过ids删除记录")
    fun delete(@PathVariable ids: List<Long>): HttpResult<Unit> {
        operableService ?: throw Exception("operableService为空")

        operableService!!.removeByIds(ids)
        return HttpResult.success()
    }

}
