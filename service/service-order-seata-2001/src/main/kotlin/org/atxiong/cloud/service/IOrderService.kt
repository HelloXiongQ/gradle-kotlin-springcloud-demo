package org.atxiong.cloud.service;

import org.atxiong.cloud.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkq
 * @since 2024-10-09
 */
interface IOrderService : IService<Order> {
    fun create (order: Order)
}
