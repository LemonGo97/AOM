package cn.lemongo97.aom.utils;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lemongo97
 */
public final class ResponseUtils {

    private ResponseUtils(){}

    public static void writeAndFlush(ServletResponse response, Object message) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(GsonUtil.toJson(message));
        out.flush();
        out.close();
    }
}
