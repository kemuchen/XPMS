
import cn.xpms.system.SystemApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GenerateDao
 * @Desc
 * @Author 柯雷
 * @Date 2020/10/29 10:41
 * @Version 1.0
 */
@ContextConfiguration(classes = SystemApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateDao {

    @Test
    public void generateDao() throws Exception {
        List<String> warnings = new ArrayList<String>();
        File configFile = new File("src/main/resources/GenerateMybatis.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}