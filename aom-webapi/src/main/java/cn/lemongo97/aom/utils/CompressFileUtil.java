package cn.lemongo97.aom.utils;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipParameters;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class CompressFileUtil {

    private CompressFileUtil() {
    }

    public static void decompressTgz(final InputStream inputStream, CompressFileHandler handler) throws IOException {
        decompressGzip(inputStream, handler);
    }

    public static void decompressGzip(final InputStream inputStream, CompressFileHandler handler) throws IOException {
        GzipCompressorInputStream gzipCompressorInputStream = new GzipCompressorInputStream(inputStream);
        GzipParameters metaData = gzipCompressorInputStream.getMetaData();
        decompressTar(IOUtils.toBufferedInputStream(gzipCompressorInputStream), handler);
    }

    public static void decompressTar(final InputStream inputStream, CompressFileHandler handler) throws IOException {
        TarArchiveInputStream i = new TarArchiveInputStream(inputStream, StandardCharsets.UTF_8.name());
        TarArchiveEntry entry;
        while ((entry = i.getNextTarEntry()) != null) {
            if (!i.canReadEntryData(entry)) {
                System.err.println("cannot read entry data!");
                continue;
            }
            if (entry.isDirectory()) {
                continue;
            }
            String path = entry.getName();
            String fileName = path.contains("/") ? path.substring(path.lastIndexOf('/') + 1) : path;
            handler.handle(path, fileName, i);

        }
    }

}
