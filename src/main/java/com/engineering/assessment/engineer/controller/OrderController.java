package com.engineering.assessment.engineer.controller;

import com.engineering.assessment.engineer.common.Result;
import com.engineering.assessment.engineer.dto.OrderDto;
import com.engineering.assessment.engineer.service.OrderService;
import com.engineering.assessment.engineer.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * +------------------------------------------------------------------------------------------+
 * | @description: 订单处理控制器
 * +------------------------------------------------------------------------------------------+
 * | @version: 1.0.0
 * +------------------------------------------------------------------------------------------+
 * | @project: engineer
 * +------------------------------------------------------------------------------------------+
 * | @Author: lijiuen
 * +------------------------------------------------------------------------------------------+
 * | @Date:2024/4/19 09:52:04
 * +------------------------------------------------------------------------------------------+
 */
@RestController
@RequestMapping("/order/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/handle-order")
    public Result<OrderVo> handleOrder(@RequestBody @Validated OrderDto orderDto) {
        OrderVo orderVo = orderService.doHandleOrder(orderDto);
        return Result.success(orderVo);
    }
}
