package org.atxiong.cloud.payment.service.impl;

import org.atxiong.cloud.payment.mapper.PayMapper;
import org.atxiong.cloud.payment.service.IPayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.atxiong.cloud.payment.entity.Pay
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付交易表 服务实现类
 * </p>
 *
 * @author xkq
 * @since 2024-07-27
 */
@Service
class PayServiceImpl : ServiceImpl<PayMapper, Pay>(), IPayService {

}
