package cn.lemongo97.aom.exception;




import cn.lemongo97.aom.common.ResultCode;

import java.io.Serializable;


/**
 * @author LemonGo97
 */
public class AomBaseException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String msg;
    private final ResultCode code;

    public AomBaseException() {
        super(ResultCode.UNKNOWN_EXCEPTION.message());
        this.code = ResultCode.UNKNOWN_EXCEPTION;
        this.msg = ResultCode.UNKNOWN_EXCEPTION.message();
    }

    public AomBaseException(String msg) {
        super(msg);
        this.code = ResultCode.UNKNOWN_EXCEPTION;
        this.msg = msg;
    }

    public AomBaseException(ResultCode resultCode) {
        super(resultCode.message());
        this.code = resultCode;
        this.msg = resultCode.message();
    }

    public AomBaseException(ResultCode resultCode, String msg) {
        super(resultCode.message());
        this.code = resultCode;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public ResultCode getCode() {
        return code;
    }
}
