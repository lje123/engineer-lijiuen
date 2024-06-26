package com.engineering.assessment.engineer.abstracts;

import com.engineering.assessment.engineer.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * +------------------------------------------------------------------------------------------+
 * | @description: 处理订单抽象类
 * +------------------------------------------------------------------------------------------+
 * | @version: 1.0.0
 * +------------------------------------------------------------------------------------------+
 * | @project: engineer
 * +------------------------------------------------------------------------------------------+
 * | @Author: lijiuen
 * +------------------------------------------------------------------------------------------+
 * | @Date:2024/4/18 21:02:25
 * +------------------------------------------------------------------------------------------+
 */
@Component
@Slf4j
public abstract class AbsatractDoOrder {

    /**
     * +------------------------------------------------------------------------------------------+
     * | @description: 统一处理订单保存逻辑
     * +------------------------------------------------------------------------------------------+
     * | @version: 1.0.0
     * +------------------------------------------------------------------------------------------+
     * | @Author:lijiuen
     * +------------------------------------------------------------------------------------------+
     * | @Date: 2024-04-18 21:09:04
     * +------------------------------------------------------------------------------------------+
     */
    @Transactional(rollbackFor = Exception.class)
    protected void doSaveOrUpdateOrder(Order order) {
        System.out.println("保存订单成功");
    }
}
