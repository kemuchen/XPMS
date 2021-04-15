package cn.xpms.paas;

import cn.xpms.paas.api.bean.dto.project.PaasProjectPo;
import cn.xpms.paas.api.dao.generator.entity.PaasInfraredBrand;
import cn.xpms.paas.api.service.business.inter.construction.CategoryServiceInterface;
import cn.xpms.paas.api.service.business.inter.device.DeviceServiceInterface;
import cn.xpms.paas.api.service.business.inter.project.ProjectServiceInterface;
import cn.xpms.system.framework.beans.error.AppException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
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
@ContextConfiguration(classes = PaasApplication.class)
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

    @Autowired
    CategoryServiceInterface categoryServiceInterface;

    @Autowired
    DeviceServiceInterface deviceServiceInterface;

    @Autowired
    ProjectServiceInterface projectServiceInterface;

    @Test
    public void test() throws AppException {
        PaasInfraredBrand paasInfraredBrand = new PaasInfraredBrand();
        paasInfraredBrand.setCategoryId("5");
        paasInfraredBrand.setBrandId("37");
        categoryServiceInterface.synchroInfraredCategoryBrandRemote(paasInfraredBrand);
    }


    @Test
    public void getDevice() throws AppException {
//        deviceServiceInterface.synchroRoomDevice("1335875739018457088");
        PaasProjectPo paasProjectPo = new PaasProjectPo();
        paasProjectPo.setLocationId("42");
        paasProjectPo.setAddress("十堰市");
        paasProjectPo.setHotel_id(6);
        paasProjectPo.setProjectName("名巢美宿五堰店");
        projectServiceInterface.createProject(paasProjectPo);
    }
}
