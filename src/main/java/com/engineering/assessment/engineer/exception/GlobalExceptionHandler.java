package com.engineering.assessment.engineer.exception;


import cn.hutool.core.util.ReflectUtil;
import com.engineering.assessment.engineer.common.ApiError;
import com.engineering.assessment.engineer.common.Result;
import io.netty.util.internal.ThrowableUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLTransientConnectionException;
import java.sql.SQLTransientException;
import java.util.List;
import java.util.Objects;

/**
 * +------------------------------------------------------------------------------------------+
 * | @description:处理全局异常
 * +------------------------------------------------------------------------------------------+
 * | @version: 1.0.0
 * +------------------------------------------------------------------------------------------+
 * | @Author:lijiuen
 * +------------------------------------------------------------------------------------------+
 * | @Date: 2024-04-18 21:56:33
 * +------------------------------------------------------------------------------------------+
 */
@Slf4j
@RestControllerAdvice
@Order(-99)
public class GlobalExceptionHandler {

    /**
     * +------------------------------------------------------------------------------------------+
     * | @description:处理所有不可知的异常
     * +------------------------------------------------------------------------------------------+
     * | @version: 1.0.0
     * +------------------------------------------------------------------------------------------+
     * | @Author:lijiuen
     * +------------------------------------------------------------------------------------------+
     * | @Date: 2024-04-19 08:55:53
     * +------------------------------------------------------------------------------------------+
     */
    @ExceptionHandler(Throwable.class)
    public Result<ApiError> handleException(HttpServletRequest request, Throwable e) {
        String requestUrl = request.getRequestURI();
        // 打印堆栈信息
        log.error("Throwable--方法：【{}】--异常---【{}】", requestUrl, ThrowableUtil.stackTraceToString(e));
        Object bindingResult = ReflectUtil.getFieldValue(e, "bindingResult");
        if (bindingResult != null) {
            StringBuilder builder = new StringBuilder();
            List errors = (List) ReflectUtil.getFieldValue(bindingResult, "errors");
            errors.forEach(er -> {
                Object defaultMessage = ReflectUtil.getFieldValue(er, "defaultMessage");
                builder.append(defaultMessage).append(",");
            });
            return Result.fail(ApiError.error(builder.toString()));
        }
        return Result.fail(ApiError.error(e.getMessage()));
    }


    /**
     * +------------------------------------------------------------------------------------------+
     * | @description:处理SQL异常
     * +------------------------------------------------------------------------------------------+
     * | @version: 1.0.0
     * +------------------------------------------------------------------------------------------+
     * | @Author:lijiuen
     * +------------------------------------------------------------------------------------------+
     * | @Date: 2024-04-19 08:56:21
     * +------------------------------------------------------------------------------------------+
     */
    @ExceptionHandler(SQLTransientException.class)
    public Result<ApiError> sqlTransientException(HttpServletRequest request, Throwable e) {
        String requestUrl = request.getRequestURI();
        // 打印堆栈信息
        log.error("SQLTransientException--方法：【{}】--异常---【{}】", requestUrl, ThrowableUtil.stackTraceToString(e));
        return buildResponseEntity(ApiError.error("保存异常！"));
    }

    /**
     * +------------------------------------------------------------------------------------------+
     * | @description:处理数据库连接的异常
     * +------------------------------------------------------------------------------------------+
     * | @version: 1.0.0
     * +------------------------------------------------------------------------------------------+
     * | @Author:lijiuen
     * +------------------------------------------------------------------------------------------+
     * | @Date: 2024-04-19 08:57:30
     * +------------------------------------------------------------------------------------------+
     */
    @ExceptionHandler(SQLTransientConnectionException.class)
    public Result<ApiError> sqlTransientConnectionException(HttpServletRequest request, Throwable e) {
        String requestUrl = request.getRequestURI();
        // 打印堆栈信息
        log.error("SQLTransientConnectionException--方法：【{}】--异常---【{}】", requestUrl, ThrowableUtil.stackTraceToString(e));
        return buildResponseEntity(ApiError.error("数据库连接异常！"));
    }


    /**
     * +------------------------------------------------------------------------------------------+
     * | @description: 处理数据库无效数据的异常
     * +------------------------------------------------------------------------------------------+
     * | @version: 1.0.0
     * +------------------------------------------------------------------------------------------+
     * | @Author:lijiuen
     * +------------------------------------------------------------------------------------------+
     * | @Date: 2024-04-19 08:56:26
     * +------------------------------------------------------------------------------------------+
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<ApiError> dataIntegrityViolationException(HttpServletRequest request, Throwable e) {
        String requestUrl = request.getRequestURI();
        Throwable cause = e.getCause();
        Object detailMessage = ReflectUtil.getFieldValue(cause, "detailMessage");
        // 打印堆栈信息
        log.error("DataIntegrityViolationException--方法：【{}】--异常---【{}】", requestUrl, ThrowableUtil.stackTraceToString(e));
        return buildResponseEntity(ApiError.error("sql执行异常！" + detailMessage));
    }


    /**
     * +------------------------------------------------------------------------------------------+
     * | @description:处理所有接口数据验证异常
     * +------------------------------------------------------------------------------------------+
     * | @version: 1.0.0
     * +------------------------------------------------------------------------------------------+
     * | @Author:lijiuen
     * +------------------------------------------------------------------------------------------+
     * | @Date: 2024-04-19 08:56:40
     * +------------------------------------------------------------------------------------------+
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<ApiError> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        String requestUrl = request.getRequestURI();
        // 打印堆栈信息
        log.error("MethodArgumentNotValidException--方法：【{}】--异常---【{}】", requestUrl, ThrowableUtil.stackTraceToString(e));
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String msg = "不能为空";
        if (msg.equals(message)) {
            message = str[1] + ":" + message;
        }
        return buildResponseEntity(ApiError.error(message));
    }

    /**
     * 统一返回
     */
    private Result<ApiError> buildResponseEntity(ApiError apiError) {
        return Result.fail(apiError);
    }


}
