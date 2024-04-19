package com.engineering.assessment.engineer;

import com.engineering.assessment.engineer.dto.OrderDto;
import com.engineering.assessment.engineer.enums.OrderStatusEnum;
import com.engineering.assessment.engineer.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * +------------------------------------------------------------------------------------------+
 * | @description: 测试类
 * +------------------------------------------------------------------------------------------+
 * | @version: 1.0.0
 * +------------------------------------------------------------------------------------------+
 * | @project: engineer
 * +------------------------------------------------------------------------------------------+
 * | @Author: lijiuen
 * +------------------------------------------------------------------------------------------+
 * | @Date:2024/4/19 10:10:14
 * +------------------------------------------------------------------------------------------+
 */
@SpringBootTest
public class OrderTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testOrder() {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderStatus(OrderStatusEnum.TO_BE_PAID.getStatus());
        orderDto.setTotalAmount(100000);
        orderService.doHandleOrder(orderDto);
    }
}
