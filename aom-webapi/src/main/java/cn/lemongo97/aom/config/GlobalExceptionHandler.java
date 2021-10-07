package cn.lemongo97.aom.config;

import cn.lemongo97.aom.common.ResultCode;
import cn.lemongo97.aom.exception.AomBaseException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author LemonGo97
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    public Result handleExpiredJwtException(ExpiredJwtException e){
        log.error("token 过期了", e);
        return Result.failure(ResultCode.USER_TOKEN_EXPIRED, e.getStackTrace());
    }

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