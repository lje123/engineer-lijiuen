package com.engineering.assessment.engineer.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : lijiuen
 * create at:  2020/12/7  7:43 下午
 * @description: 定义异常
 */
@Data
class ApiErr {

    private Integer code;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timestamp;
    private String message;

    private ApiErr() {
        timestamp = LocalDateTime.now();
    }

    public ApiErr(Integer code, String message) {
        this();
        this.code = code;
        this.message = message;
    }
}


