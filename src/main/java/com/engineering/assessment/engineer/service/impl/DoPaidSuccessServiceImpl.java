package com.engineering.assessment.engineer.service.impl;

import com.engineering.assessment.engineer.abstracts.AbsatractDoOrder;
import com.engineering.assessment.engineer.dto.OrderDto;
import com.engineering.assessment.engineer.entity.Order;
import com.engineering.assessment.engineer.enums.OrderStatusEnum;
import com.engineering.assessment.engineer.service.DoOrderByStatusService;
import com.engineering.assessment.engineer.vo.OrderVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * +------------------------------------------------------------------------------------------+
 * | @description: 处理支付成功相应逻辑
 * +------------------------------------------------------------------------------------------+
 * | @version: 1.0.0
 * +------------------------------------------------------------------------------------------+
 * | @project: engineer
 * +------------------------------------------------------------------------------------------+
 * | @Author: lijiuen
 * +------------------------------------------------------------------------------------------+
 * | @Date:2024/4/18 21:44:46
 * +------------------------------------------------------------------------------------------+
 */
@Service
public class DoPaidSuccessServiceImpl extends AbsatractDoOrder implements DoOrderByStatusService {
    @Override
    public Boolean isCurrentStatus(Short status) {
        return OrderStatusEnum.PAID.getStatus().equals(status);
    }

    @Override
    @Transactional
    public OrderVo doHandleOrderByStatus(OrderDto orderDto) {
        System.out.println("处理订单成功支付逻辑");
        //------------------------------
        Order order = new Order();
        order.setOrderStatus(OrderStatusEnum.PAID.getStatus());
        this.doSaveOrUpdateOrder(order);
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderStatus(OrderStatusEnum.REFUND_SUCCESSFUL.getStatus());
        orderVo.setOrderNo("ip99888999");
        return orderVo;
    }

    @Override
    public Boolean isDoNextStatus(Short status, Short orderType) {
        System.out.println("根据订单类型和订单当前状态判断是否要走下一节点");
        return false;
    }
}
