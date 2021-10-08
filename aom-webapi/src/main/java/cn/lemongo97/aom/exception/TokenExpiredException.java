package cn.lemongo97.aom.exception;

import cn.lemongo97.aom.common.ResultCode;

/**
 * @author lemongo97
 */
public class TokenExpiredException extends AomBaseException{

    public TokenExpiredException() {
        super(ResultCode.USER_TOKEN_EXPIRED);
    }
}
