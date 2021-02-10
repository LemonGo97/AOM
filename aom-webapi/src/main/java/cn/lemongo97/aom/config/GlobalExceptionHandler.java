package cn.lemongo97.aom.config;

import cn.lemongo97.aom.common.ResultCode;
import cn.lemongo97.aom.exception.AomBaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AomBaseException.class)
    public Result handleTiBaseException(AomBaseException e) {
        log.error(e.getMsg(),e);
        return Result.failure(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Throwable.class)
    public Result handleBasicException(Throwable t) {
        log.error(ResultCode.UNKNOWN_EXCEPTION.message(),t);
        return Result.failure(ResultCode.UNKNOWN_EXCEPTION, t.getMessage());
    }
}