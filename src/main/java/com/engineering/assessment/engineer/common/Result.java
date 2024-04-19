package com.engineering.assessment.engineer.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @author : lijiuen
 * create at:  2020/12/7  8:27 下午
 * @description:
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Result<T> {
    private final static int MAP_INIT_VALUE = 16;

    private int code;

    private T data;

    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    public Result(ApiError error) {
    }

    public Result() {
    }


    public static <T> Result<T> result(ApiError apiCode) {
        return result(apiCode, null);
    }

    public static <T> Result<T> result(ApiError apiCode, Object data) {
        return result(apiCode, null, data);
    }

    public static <T> Result<T> result(int code, Object data) {
        return result(code, data);
    }

    public static <T> Result<T> result(ApiError apiCode, String msg, Object data) {
        String message = apiCode.getMessage();
        if (StringUtils.isNotBlank(msg)) {
            message = msg;
        }
        final Result<T> tApiResult = new Result<>();
        tApiResult.setCode(apiCode.getCode());
        tApiResult.setMessage(message);
        tApiResult.setData((T) data);
        tApiResult.setTime(new Date());
        return tApiResult;
    }


    public static <T> Result<T> fail(ApiError apiCode) {
        return result(apiCode, null);
    }


    public static <T> Result<T> dataFail(String msg) {
        return result(ApiError.error(msg), msg, null);

    }

    public static <T> Result<T> fail(String msg) {
        return result(ApiError.error(msg), msg, null);

    }

    public static <T> Result success(Object o) {
        return result(HttpStatus.OK.value(), o);
    }


    public static <T> Result<T> orderFail(ApiError code, String msg) {
        return result(code, msg, null);

    }

    public static <T> Result<T> fail(ApiError apiCode, String msg, Object data) {
        return result(apiCode, msg, data);

    }


    public static Result<ApiError> fail(ApiError apiError, HttpStatus valueOf) {
        return result(apiError, valueOf.value());
    }
}
