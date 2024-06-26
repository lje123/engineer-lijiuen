package com.engineering.assessment.engineer.entity;

import lombok.Data;

/**
 * +------------------------------------------------------------------------------------------+
 * | @description: 订单实体类
 * +------------------------------------------------------------------------------------------+
 * | @version: 1.0.0
 * +------------------------------------------------------------------------------------------+
 * | @project: engineer
 * +------------------------------------------------------------------------------------------+
 * | @Author: lijiuen
 * +------------------------------------------------------------------------------------------+
 * | @Date:2024/4/18 21:16:06
 * +------------------------------------------------------------------------------------------+
 */
@Data
public class Order {
    private Integer totalAmount;

    private String orderNo;
    /*
     *  0--待支付  1--支付成功  2--取消  3--退款中 4--退款成功  5--退款失败
     * */
    private Short orderStatus;

    private String remark;

    private Short orderType;

    private Short orderSource;
}
