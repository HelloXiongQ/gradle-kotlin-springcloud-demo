package org.atxiong.cloud.service.impl;

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import io.seata.core.context.RootContext
import io.seata.spring.annotation.GlobalTransactional
import org.atxiong.cloud.http.AccountSeataApi
import org.atxiong.cloud.http.StorageSeataApi
import org.atxiong.cloud.mapper.OrderMapper
import org.atxiong.cloud.order.entity.Order
import org.atxiong.cloud.service.IOrderService
import org.springframework.stereotype.Service


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xkq
 * @since 2024-10-09
 */
@Service
open class OrderServiceImpl(
    private val accountSeataApi: AccountSeataApi,
    private val storageSeataApi: StorageSeataApi
) : ServiceImpl<OrderMapper, Order>(), IOrderService {

    /**
     * 添加 全局事务，name标注事务名，rollbackFor标注回滚异常
     */
    @GlobalTransactional(name = "my-global-tx", rollbackFor = [Exception::class])
    override fun create(order: Order) {
        val xid = RootContext.getXID() // 获取全局的事务ID

        println("--------------开始订单---------------> xid: $xid")

        if (save(order.apply { status = 0 })) {
            val orderFromDB = getOne(KtQueryWrapper(order))

            // 2. 扣减库存
            println("-------------> 开始扣减库存")
            storageSeataApi.decrease(orderFromDB.productId!!, orderFromDB.count!!)
            println("-------------> 扣减库存成功")


            // 3. 扣减账户余额
            println("-------------> 开始扣减余额")
            accountSeataApi.decrease(order.userId!!, order.money!!)
            println("-------------> 扣余额存成功")


            // 4. 修改订单状态
            println("-------------> 开始修改订单状态")
            update(
                order.apply { status = 1 },
                KtQueryWrapper(Order::class.java).eq(Order::userId, order.userId)
            )
            println("-------------> 修改订单状态成功")
        }

        println("--------------结束订单------------> xid: $xid")


    }
}
