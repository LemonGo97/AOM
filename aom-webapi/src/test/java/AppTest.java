import cn.lemongo97.aom.Application;
import cn.lemongo97.aom.api.RedisIOAPI;
import cn.lemongo97.aom.model.Role;
import cn.lemongo97.aom.model.User;
import cn.lemongo97.aom.model.application.ApplicationPO;
import cn.lemongo97.aom.model.application.ApplicationVersionPO;
import cn.lemongo97.aom.model.mapper.ApplicationPOMapper;
import cn.lemongo97.aom.model.wrapper.ApplicationPOQuery;
import cn.lemongo97.aom.repository.ApplicationJpaRepository;
import cn.lemongo97.aom.repository.UserJpaRepository;
import cn.lemongo97.aom.update.IApplicationUpdate;
import cn.lemongo97.aom.utils.CompressFileUtil;
import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;
import okhttp3.ResponseBody;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipParameters;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Call;
import retrofit2.Response;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest(classes = Application.class)
public class AppTest {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private IApplicationUpdate applicationUpdate;

    @Autowired
    private ApplicationJpaRepository applicationJpaRepository;

    @Test
    void contextLoads() {
        User u1 = new User();
        u1.setUsername("admin");
        u1.setPassword("123456");
        u1.setAccountNonExpired(true);
        u1.setAccountNonLocked(true);
        u1.setCredentialsNonExpired(true);
        u1.setEnabled(true);
        List<Role> rs1 = new ArrayList<>();
        Role r1 = new Role();
        r1.setName("ROLE_admin");
        r1.setNameZh("管理员");
        rs1.add(r1);
        u1.setRoles(rs1);
        userJpaRepository.save(u1);
        User u2 = new User();
        u2.setUsername("lipu");
        u2.setPassword("123456");
        u2.setAccountNonExpired(true);
        u2.setAccountNonLocked(true);
        u2.setCredentialsNonExpired(true);
        u2.setEnabled(true);
        List<Role> rs2 = new ArrayList<>();
        Role r2 = new Role();
        r2.setName("ROLE_user");
        r2.setNameZh("普通用户");
        rs2.add(r2);
        u2.setRoles(rs2);
        userJpaRepository.save(u2);
    }

    @Autowired
    private ApplicationPOMapper applicationPOMapper;

    @Test
    void testFluentMybatis() throws IOException {
        List<ApplicationPO> applications = applicationPOMapper.listObjs(applicationPOMapper.query());
        System.out.println(applications);
    }

    @Test
    void getRedisReleaseNotes() throws IOException {
        List<ApplicationVersionPO> redis = applicationJpaRepository.queryByNameOrderByPackageNameDesc("Redis");
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
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    redi.setChangeLog(new String(bytes));
                }
            });
            applicationJpaRepository.save(redi);
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
