package com.engineering.assessment.engineer.vo;

import lombok.Data;

/**
 * +------------------------------------------------------------------------------------------+
 * | @description: 订单处理返参
 * +------------------------------------------------------------------------------------------+
 * | @version: 1.0.0
 * +------------------------------------------------------------------------------------------+
 * | @project: engineer
 * +------------------------------------------------------------------------------------------+
 * | @Author: lijiuen
 * +------------------------------------------------------------------------------------------+
 * | @Date:2024/4/19 09:15:20
 * +------------------------------------------------------------------------------------------+
 */
@Data
public class OrderVo {
    private String orderNo;
    private Short orderStatus;
    private Integer orderAmount;
    private Short orderType;
}
