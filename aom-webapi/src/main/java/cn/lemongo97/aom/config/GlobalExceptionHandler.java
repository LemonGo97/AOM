package cn.lemongo97.aom.config;

import cn.lemongo97.aom.common.ResultCode;
import cn.lemongo97.aom.exception.AomBaseException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

/**
 * @author LemonGo97
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public Result handleDataAccessException(DataAccessException e){
        log.error("数据库服务异常", e);
        return Result.failure(ResultCode.DATABASE_EXCEPTION, Arrays.toString(e.getStackTrace()));
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