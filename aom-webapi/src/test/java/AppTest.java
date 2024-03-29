import cn.lemongo97.aom.Application;
import cn.lemongo97.aom.api.RedisIOAPI;
import cn.lemongo97.aom.mapper.ApplicationMapper;
import cn.lemongo97.aom.mapper.ApplicationVersionMapper;
import cn.lemongo97.aom.model.application.ApplicationPO;
import cn.lemongo97.aom.model.application.ApplicationVersionPO;
import cn.lemongo97.aom.update.IApplicationUpdate;
import cn.lemongo97.aom.utils.CompressFileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Call;
import retrofit2.Response;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest(classes = Application.class)
public class AppTest {
    @Autowired
    private IApplicationUpdate applicationUpdate;

    @Autowired
    private ApplicationVersionMapper applicationVersionMapper;

    @Test
    void contextLoads() {
        applicationUpdate.update();
    }


    @Autowired
    ApplicationMapper applicationMapper;

    @Test
    void testFluentMybatis() throws IOException {
        ApplicationPO build = ApplicationPO.builder().icon("redis").name(cn.lemongo97.aom.common.Application.REDIS).build();
        applicationMapper.insert(build);
    }

    @Test
    void getRedisReleaseNotes() throws IOException {
        List<ApplicationVersionPO> redis = applicationVersionMapper.selectList(new QueryWrapper<>());
        Pattern compile = Pattern.compile("#define\\s+REDIS_VERSION\\s+\"(.*?)\"");
        for (ApplicationVersionPO redi : redis) {
            String packageName = redi.getPackageName();
            Call<ResponseBody> download = RedisIOAPI.service.download(packageName);
            Response<ResponseBody> execute = download.execute();
            CompressFileUtil.decompressTgz(execute.body().byteStream(), (filePath, fileName, inputStream) -> {
                if (Objects.equals("version.h",fileName)){
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    String fileContent = new String(bytes);
                    Matcher matcher = compile.matcher(fileContent);
                    if (matcher.find()){
                        redi.setVersion(matcher.group(1));
                        System.out.println(redi.getVersion());
                    }
                }else if (Objects.equals("00-RELEASENOTES",fileName)){
//                    byte[] bytes = IOUtils.toByteArray(inputStream);
//                    redi.setChangeLog(new String(bytes));
                }
            });
            applicationVersionMapper.updateById(redi);
        }
    }


    public static void main(String[] args) throws ParseException, IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File("C:\\Users\\LemonGo97\\Downloads\\redis-6.0.10.tar.gz"));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        CompressFileUtil.decompressTgz(byteArrayInputStream, (filePath, fileName, inputStream) -> {
            if (Objects.equals("version.h",fileName)){
                byte[] bytes1 = IOUtils.toByteArray(inputStream);
                System.out.println(new String(bytes1));
            }else if (Objects.equals("00-RELEASENOTES",fileName)){
                byte[] bytes1 = IOUtils.toByteArray(inputStream);
                System.out.println(bytes1);
            }
        });
    }

}
