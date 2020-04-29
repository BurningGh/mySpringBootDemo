package com.example.validator.framework.exception.handler;

import com.example.validator.model.Result;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 异常处理<br/>
 * 异常状态码根据前端业务，暂不做修改
 *
 * @author lz
 * @date 2018/7/28
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 数据校验异常
     *
     * @param e 数据校验异常
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleResourceBindException(BindException e) {
        log.error("业务异常，{},{},{}", formatLog(), 400, e.getMessage());
        return getResultView("参数错误", e);
    }

    /**
     * 数据校验异常
     *
     * @param e 数据校验异常
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleResourceConstraintViolationException(ConstraintViolationException e) {
        log.error("业务异常，{},{},{}", formatLog(), 400, e.getMessage());
        return getResultView("参数错误", e);
    }


    /**
     * 参数异常
     *
     * @param msg
     * @param e
     * @return
     */
    private Result getResultView(String msg, BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        Set<BindingResultObject> errorMessage = new HashSet<>();
        allErrors.forEach(item -> errorMessage.add(BindingResultObject.builder().build().setMessage(item.getDefaultMessage()).setField(((DefaultMessageSourceResolvable) Objects.requireNonNull(item.getArguments())[0]).getDefaultMessage())));
        return Result.builder().build().setMsg(msg).setCode(400).setData(errorMessage);
    }

    /**
     * 参数异常
     *
     * @param msg
     * @param e
     * @return
     */
    private Result getResultView(String msg, ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        Set<BindingResultObject> errorMessage = new HashSet<>();
        try {
            constraintViolations.forEach(item -> {
                String message = item.getMessage();
                String s = item.getPropertyPath().toString();
                String[] split = s.split("\\.");
                errorMessage.add(BindingResultObject.builder().build().setMessage(message).setField(split[split.length - 1]));
            });
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return Result.builder().build().setCode(400).setMsg(msg).setData(errorMessage);
    }

    /**
     * 日志记录时间
     *
     * @return
     */
    public static String formatLog() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 设置时区
        return sdf.format(new Date());
    }
}

@Builder
@Data
class BindingResultObject implements Serializable {
    private String field;
    private String message;

    public BindingResultObject setField(String field) {
        this.field = field;
        return this;
    }

    public BindingResultObject setMessage(String message) {
        this.message = message;
        return this;
    }
}