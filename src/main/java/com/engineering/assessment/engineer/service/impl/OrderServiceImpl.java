package com.engineering.assessment.engineer.service.impl;

import com.engineering.assessment.engineer.dto.OrderDto;
import com.engineering.assessment.engineer.service.DoOrderByStatusService;
import com.engineering.assessment.engineer.service.OrderService;
import com.engineering.assessment.engineer.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * +------------------------------------------------------------------------------------------+
 * | @description: 处理订单相关逻辑实现类
 * +------------------------------------------------------------------------------------------+
 * | @version: 1.0.0
 * +------------------------------------------------------------------------------------------+
 * | @project: engineer
 * +------------------------------------------------------------------------------------------+
 * | @Author: lijiuen
 * +------------------------------------------------------------------------------------------+
 * | @Date:2024/4/19 09:19:24
 * +------------------------------------------------------------------------------------------+
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private List<DoOrderByStatusService> doOrderByStatusServices;

    @Override
    public OrderVo doHandleOrder(OrderDto orderDto) {
        OrderVo orderVo = null;
        for (DoOrderByStatusService doOrderByStatusService : doOrderByStatusServices) {
            if (doOrderByStatusService.isCurrentStatus(orderDto.getOrderStatus())) {
                orderVo = doOrderByStatusService.doHandleOrderByStatus(orderDto);
                
                if (doOrderByStatusService.isDoNextStatus(orderVo.getOrderStatus(), orderVo.getOrderType())) {
                    orderDto.setOrderStatus(orderVo.getOrderStatus());
                    orderDto.setOrderNo(orderVo.getOrderNo());
                    this.doHandleOrder(orderDto);
                }
            }
        }

        return orderVo;
    }
}
