package org.atxiong.springcloud.service.impl;

import org.atxiong.springcloud.entity.Pay;
import org.atxiong.springcloud.mapper.PayMapper;
import org.atxiong.springcloud.service.IPayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付交易表 服务实现类
 * </p>
 *
 * @author xkq
 * @since 2024-09-11
 */
@Service
open class PayServiceImpl : ServiceImpl<PayMapper, Pay>(), IPayService {

}
