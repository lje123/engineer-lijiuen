package com.engineering.assessment.engineer.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.slf4j.MDC;

import java.time.LocalDateTime;

/**
 * @author : lijiuen
 * create at:  2020/12/7  7:43 下午
 * @description:
 */
@Data
public class ApiError {

    private Integer code = 400;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timestamp;
    private String message;
    private String traceId;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public static ApiError error(String message) {
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        apiError.setTraceId(MDC.get("traceId"));
        return apiError;
    }

    public static ApiError error(Integer code, String message) {
        ApiError apiError = new ApiError();
        apiError.setCode(code);
        apiError.setMessage(message);
        apiError.setTraceId(MDC.get("traceId"));
        return apiError;
    }
}


