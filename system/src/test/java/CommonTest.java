import cn.xpms.system.framework.util.http.HttpUtil;
import cn.xpms.system.framework.util.sys.SysUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CommonTest
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/4 16:46
 * @Version 1.0
 */
public class CommonTest {

    @Test
    public void testApi() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("deviceSn", "84E0F4267CC61501");
        params.put("faceImg", SysUtil.imageToBase64("D:\\123.jpg"));
        params.put("clientId", "b51e56f369d14a42b02a8121000388dc918e100a62a344f9b625c4f63c917819");
        params.put("clientKey", "2dd6596f73bc402c8bce68cc90b41b8d9a555b4627bc4d7dbeed105a42c8d726");
        String response = HttpUtil.post("http://apitest.charmplus.cn/faceInfo/uploadFace", JSONObject.toJSONString(params), null);
        System.err.println("1111" + response);
    }
}
