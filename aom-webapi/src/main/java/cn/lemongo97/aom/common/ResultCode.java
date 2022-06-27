package cn.lemongo97.aom.common;

import org.springframework.http.HttpStatus;

public enum ResultCode {
    /* 成功状态码*/
    SUCCESS(200, HttpStatus.OK, "成功"),
    /* 参数错误：1001-1999 */
    PARAM_IS_INVALID(1001, HttpStatus.BAD_REQUEST, "参数无效"),
    PARAM_IS_BLANK(1002, HttpStatus.BAD_REQUEST, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1003, HttpStatus.BAD_REQUEST, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, HttpStatus.BAD_REQUEST, "参数缺失"),
    /* 用户错误：2001-2999 */
    USER_NOT_LOGGED_IN(2001, HttpStatus.UNAUTHORIZED, "用户未登录，访问的目标需要验证，请登录"),
    USER_LOGIN_ERROR(2002, HttpStatus.UNAUTHORIZED, "账户不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003, HttpStatus.UNAUTHORIZED, "账户或已被禁用"),
    USER_PERMISSION_DENIED(2006, HttpStatus.FORBIDDEN, "权限不足"),
    USER_UNAUTHORIZED(2007, HttpStatus.UNAUTHORIZED, "认证失败"),
    /* 其他异常：3001-3999 */
    UNKNOWN_EXCEPTION(3001, HttpStatus.INTERNAL_SERVER_ERROR, "出现了预期之外的异常"),
    DATABASE_EXCEPTION(5001, HttpStatus.INTERNAL_SERVER_ERROR, "数据库服务异常"),
    OBJECT_NOT_EXIST(6001, HttpStatus.INTERNAL_SERVER_ERROR, "对象不存在");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

    ResultCode(Integer code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public HttpStatus httpStatus() {
        return this.httpStatus;
    }

    public String message() {
        return this.message;
    }


}
