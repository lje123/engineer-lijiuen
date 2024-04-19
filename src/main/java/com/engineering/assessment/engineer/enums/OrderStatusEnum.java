package com.engineering.assessment.engineer.enums;

import lombok.Getter;

import static com.engineering.assessment.engineer.constants.Constants.*;

/**
 * +------------------------------------------------------------------------------------------+
 * | @description: 订单状态枚举类
 * +------------------------------------------------------------------------------------------+
 * | @version: 1.0.0
 * +------------------------------------------------------------------------------------------+
 * | @project: engineer
 * +------------------------------------------------------------------------------------------+
 * | @Author: lijiuen
 * +------------------------------------------------------------------------------------------+
 * | @Date:2024/4/18 21:24:21
 * +------------------------------------------------------------------------------------------+
 */
@Getter
public enum OrderStatusEnum {
    TO_BE_PAID(S_ZERO, "待支付"),
    PAID(S_ONE, "支付成功"),
    CANCEL(S_TWO, "取消"),
    REFUNDING(S_THREE, "退款中"),
    REFUND_SUCCESSFUL(S_FOUR, "退款成功"),
    REFUND_FAILED(S_FIVE, "退款失败");

    private Short status;
    private String desc;

    OrderStatusEnum(Short status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
