package com.engineering.assessment.engineer.service.impl;

import com.engineering.assessment.engineer.abstracts.AbsatractDoOrder;
import com.engineering.assessment.engineer.dto.OrderDto;
import com.engineering.assessment.engineer.entity.Order;
import com.engineering.assessment.engineer.enums.OrderStatusEnum;
import com.engineering.assessment.engineer.service.DoOrderByStatusService;
import com.engineering.assessment.engineer.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * +------------------------------------------------------------------------------------------+
 * | @description: 处理待支付订单逻辑
 * +------------------------------------------------------------------------------------------+
 * | @version: 1.0.0
 * +------------------------------------------------------------------------------------------+
 * | @project: engineer
 * +------------------------------------------------------------------------------------------+
 * | @Author: lijiuen
 * +------------------------------------------------------------------------------------------+
 * | @Date:2024/4/18 21:21:58
 * +------------------------------------------------------------------------------------------+
 */
@Service
@Slf4j
public class DoToBePaidOrderServiceImpl extends AbsatractDoOrder implements DoOrderByStatusService {
    @Override
    public Boolean isCurrentStatus(Short status) {
        return OrderStatusEnum.TO_BE_PAID.getStatus().equals(status);
    }

    @Override
    @Transactional
    public OrderVo doHandleOrderByStatus(OrderDto orderDto) {
        System.out.println("校验订单成功，开始保存订单");
        Order order = new Order();
        order.setOrderStatus(OrderStatusEnum.TO_BE_PAID.getStatus());
        this.doSaveOrUpdateOrder(order);
        System.out.println("发送通知到消息服务，由消息服务根据订单状态进行相应转发和处理");
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderStatus(OrderStatusEnum.TO_BE_PAID.getStatus());
        orderVo.setOrderNo("ip99888999");
        return orderVo;
    }

    @Override
    public Boolean isDoNextStatus(Short status, Short orderType) {
        return false;
    }
}
