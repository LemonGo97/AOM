package cn.lemongo97.aom.update.application;

import cn.hutool.core.util.ReUtil;
import cn.lemongo97.aom.common.Application;
import cn.lemongo97.aom.common.Platform;
import cn.lemongo97.aom.common.SystemType;
import cn.lemongo97.aom.mapper.ApplicationVersionMapper;
import cn.lemongo97.aom.model.application.ApplicationVersionPO;
import cn.lemongo97.aom.update.IApplicationUpdate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RedisUpdateParser implements IApplicationUpdate {

    @Autowired
    private ApplicationVersionMapper applicationVersionMapper;

    private static final String DOWNLOAD_URL = "https://download.redis.io/releases/";
    private static final Pattern PACKAGE_PARSER_REGEX = Pattern.compile("<a\\s*href=\"(\\w+-\\d+(?:\\.\\d+)*.tar.gz)\">(\\w+-\\d+(?:\\.\\d+)*.tar.gz)</a>\\s*(\\d{2}-\\w+-\\d{4}\\s\\d+:\\d+)\\s*(\\d+)");
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.ENGLISH);

    @Override
    public void update(){
        try {
            Document doc = Jsoup.connect(DOWNLOAD_URL).get();
            String html = doc.select("pre").outerHtml();
            Matcher matcher = PACKAGE_PARSER_REGEX.matcher(html);
            while (matcher.find()){
                String downloadUrl = DOWNLOAD_URL + matcher.group(1);
                String fileName = matcher.group(2);
                String uploadTime = matcher.group(3);
                String fileSize = matcher.group(4);
                ApplicationVersionPO application = new ApplicationVersionPO();
                application.setApplication(Application.REDIS);
                try {
                    application.setUpdateTime(DATE_FORMAT.parse(uploadTime));
                } catch (ParseException ignore) {
                }
                application.setVersion(ReUtil.get("redis-(.*)\\.tar\\.gz", fileName, 1));
                application.setPackageName(fileName);
                application.setPlatform(Platform.SOURCE);
                application.setSystemType(SystemType.LINUX);
                application.setDownloadUrl(downloadUrl);
                application.setFileSize(Integer.valueOf(fileSize));
                applicationVersionMapper.insert(application);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
