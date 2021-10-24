package cn.lemongo97.aom.utils;

import java.io.IOException;
import java.io.InputStream;

public interface CompressFileHandler {

    void handle(final String filePath, final String fileName, final InputStream inputStream) throws IOException;

}
