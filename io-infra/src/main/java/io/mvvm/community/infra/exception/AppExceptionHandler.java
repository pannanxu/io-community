package io.mvvm.community.infra.exception;

import io.mvvm.community.infra.model.result.AppErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Pan
 **/
@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public AppErrorResult assertException(Exception e) {
        log.error("AppExceptionHandler|assertException|{}", e.getMessage(), e);
        return AppErrorResult.buildFailure(AppErrorCode.BIZ_ERROR, e.getMessage());
    }

    @ExceptionHandler(AppException.class)
    public AppErrorResult appException(AppException e) {
        log.info("AppExceptionHandler|appException|{}", e.getMessage());
        AppErrorResult result = AppErrorResult.buildFailure(e.getErrCode(), e.getErrDesc());
        result.setErrMessage(e.getMessage());
        return result;
    }

    // 没有处理器异常404
    @ExceptionHandler(NoHandlerFoundException.class)
    public AppErrorResult noHandlerFoundException(NoHandlerFoundException e) {
        log.error("AppExceptionHandler|noHandlerFoundException|{}", e.getMessage());
        return AppErrorResult.buildFailure(AppErrorCode.TRY_LATER);
    }

    // 方法RequestParam/PathVariable形式参数校验异常
    @ExceptionHandler(ConstraintViolationException.class)
    public AppErrorResult constraintViolationException(ConstraintViolationException ex) {
        log.info("AppExceptionHandler|constraintViolationException|{}", ex.getMessage());
        String messages = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return AppErrorResult.buildFailure(AppErrorCode.INVALID_PARAMS, messages);
    }

    @ExceptionHandler(UndeclaredThrowableException.class)
    public AppErrorResult undeclaredThrowableException(UndeclaredThrowableException e) {
        log.info("AppExceptionHandler|undeclaredThrowableException|{}", e.getMessage());
        return AppErrorResult.buildFailure(AppErrorCode.INVALID_PARAMS);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public AppErrorResult missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.info("AppExceptionHandler|missingServletRequestParameterException|{}", e.getMessage());
        return AppErrorResult.buildFailure(AppErrorCode.INVALID_PARAMS);
    }

    // 方法参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AppErrorResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String fieldError = null == e.getBindingResult().getFieldError() ? e.getMessage() : e.getBindingResult().getFieldError().getDefaultMessage();
        log.info("AppExceptionHandler|methodArgumentNotValidException|{}", fieldError);
        return AppErrorResult.buildFailure(AppErrorCode.INVALID_PARAMS, fieldError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public AppErrorResult methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.info("AppExceptionHandler|methodArgumentTypeMismatchException|{}", e.getMessage());
        return AppErrorResult.buildFailure(AppErrorCode.INVALID_PARAMS);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public AppErrorResult httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.info("AppExceptionHandler|httpMediaTypeNotSupportedException|{}", e.getMessage());
        return AppErrorResult.buildFailure(AppErrorCode.INVALID_PARAMS);
    }

    @ExceptionHandler(BindException.class)
    public AppErrorResult bindException(BindException e) {
        log.info("AppExceptionHandler|bindException|{}", e.getMessage());
        return AppErrorResult.buildFailure(AppErrorCode.INVALID_PARAMS);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public AppErrorResult accessDeniedException(AccessDeniedException e) {
        log.error("AppExceptionHandler|accessDeniedException|{}", e.getMessage());
        return AppErrorResult.buildFailure(AppErrorCode.AUTH_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public AppErrorResult exception(Exception e) {
        log.error("AppExceptionHandler|exception|{}", e.getMessage(), e);
        return AppErrorResult.buildFailure(AppErrorCode.UNKNOWN_ERROR, e.getMessage());
    }

}