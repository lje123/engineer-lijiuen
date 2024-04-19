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
 * | @description: 处理退款中订单
 * +------------------------------------------------------------------------------------------+
 * | @version: 1.0.0
 * +------------------------------------------------------------------------------------------+
 * | @project: engineer
 * +------------------------------------------------------------------------------------------+
 * | @Author: lijiuen
 * +------------------------------------------------------------------------------------------+
 * | @Date:2024/4/18 21:51:57
 * +------------------------------------------------------------------------------------------+
 */
@Service
@Slf4j
public class DoRerundingSericeImpl extends AbsatractDoOrder implements DoOrderByStatusService {
    @Override
    public Boolean isCurrentStatus(Short status) {
        return OrderStatusEnum.REFUNDING.getStatus().equals(status);
    }

    @Override
    @Transactional
    public OrderVo doHandleOrderByStatus(OrderDto orderDto) {
        System.out.println("处理订单发起退款后逻辑");
        //------------------------------

        Order order = new Order();
        order.setOrderStatus(OrderStatusEnum.REFUND_SUCCESSFUL.getStatus());
        this.doSaveOrUpdateOrder(order);
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderStatus(OrderStatusEnum.REFUNDING.getStatus());
        orderVo.setOrderNo("ip99888999");
        return orderVo;
    }

    @Override
    public Boolean isDoNextStatus(Short status, Short orderType) {
        return false;
    }
}
