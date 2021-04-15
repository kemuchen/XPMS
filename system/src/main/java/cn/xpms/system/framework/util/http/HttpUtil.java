package cn.xpms.system.framework.util.http;

import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.SysUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;
import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @ClassName http、https 请求工具类， 微信为https的请求
 * @Desc
 * @Author kelei
 * @Date 2019/7/9 14:59
 * @Version 1.0
 */
public class HttpUtil {

    /**
     * 初始化http请求参数
     *
     * @param url
     * @param method
     * @param headers
     * @return
     * @throws Exception
     */
    private static HttpURLConnection initHttp(String url, String method,
                                              Map<String, Object> headers) throws Exception {
        URL _url = new URL(url);
        HttpURLConnection http = (HttpURLConnection) _url.openConnection();
        // 连接超时
        http.setConnectTimeout(SystemConstants.DEFAULT_TIMEOUT);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(SystemConstants.DEFAULT_TIMEOUT);
        http.setUseCaches(false);
        http.setRequestMethod(method);
        if (headers == null || SysUtil.isEmpty(headers.get("Content-Type"))) {
            http.setRequestProperty("Content-Type",
                    "application/json");
        } else {
            http.setRequestProperty("Content-Type", headers.get("Content-Type").toString());
        }
        http.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
        if (null != headers && !headers.isEmpty()) {
            for (Entry<String, Object> entry : headers.entrySet()) {
                if (!"Content-Type".equals(entry.getKey())) {
                    http.setRequestProperty(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        return http;
    }

    /**
     * 初始化http请求参数
     *
     * @param url
     * @param method
     * @return
     * @throws Exception
     */
    private static HttpsURLConnection initHttps(String url, String method,
                                                Map<String, Object> headers) throws Exception {
        TrustManager[] tm = {new MyX509TrustManager()};
        System.setProperty("https.protocols", "TLSv1");
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        URL _url = new URL(url);
        HttpsURLConnection http = (HttpsURLConnection) _url.openConnection();
        // 设置域名校验
        http.setHostnameVerifier(new HttpUtil().new TrustAnyHostnameVerifier());
        // 连接超时
        http.setConnectTimeout(SystemConstants.DEFAULT_TIMEOUT);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(SystemConstants.DEFAULT_TIMEOUT);
        http.setUseCaches(false);
        http.setRequestMethod(method);
        if (headers == null || SysUtil.isEmpty(headers.get("Content-Type"))) {
            http.setRequestProperty("Content-Type",
                    "application/json");
        } else {
            http.setRequestProperty("Content-Type", headers.get("Content-Type").toString());
        }
        http.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
        if (null != headers && !headers.isEmpty()) {
            for (Entry<String, Object> entry : headers.entrySet()) {
                if (!"Content-Type".equals(entry.getKey())) {
                    http.setRequestProperty(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        http.setSSLSocketFactory(ssf);
        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        return http;
    }

    /**
     * @return 返回类型:
     * @throws Exception
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, Object> params,
                             Map<String, Object> headers) throws Exception {
        HttpURLConnection http;
        if (isHttps(url)) {
            http = initHttps(initParams(url, params), SystemConstants.GET, headers);
        } else {
            http = initHttp(initParams(url, params), SystemConstants.GET, headers);
        }
        InputStream in = http.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in,
                SystemConstants.DEFAULT_CHARSET));
        String valueString;
        StringBuffer bufferRes = new StringBuffer();
        while ((valueString = read.readLine()) != null) {
            bufferRes.append(valueString);
        }
        in.close();
        if (http != null) {
            http.disconnect();// 关闭连接
        }
        return bufferRes.toString();
    }

    /**
     * @return 返回类型:
     * @throws Exception
     * @description 功能描述: delete 请求
     */
    public static String delete(String url, Map<String, Object> params,
                                Map<String, Object> headers) throws Exception {
        HttpURLConnection http;
        if (isHttps(url)) {
            http = initHttps(initParams(url, params), SystemConstants.DELETE, headers);
        } else {
            http = initHttp(initParams(url, params), SystemConstants.DELETE, headers);
        }
        InputStream in = http.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in,
                SystemConstants.DEFAULT_CHARSET));
        String valueString;
        StringBuffer bufferRes = new StringBuffer();
        while ((valueString = read.readLine()) != null) {
            bufferRes.append(valueString);
        }
        in.close();
        if (http != null) {
            http.disconnect();// 关闭连接
        }
        return bufferRes.toString();
    }

    public static String get(String url) throws Exception {
        return get(url, null);
    }

    public static String get(String url, Map<String, Object> params)
            throws Exception {
        return get(url, params, null);
    }

    /**
     * @Description: post请求
     * @Params: [url, params, headers]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-07-01 10:59
     */
    public static String post(String url, String params, Map<String, Object> headers)
            throws Exception {
        HttpURLConnection http;
        if (isHttps(url)) {
            http = initHttps(url, SystemConstants.POST, headers);
        } else {
            http = initHttp(url, SystemConstants.POST, headers);
        }
        OutputStream out = http.getOutputStream();
        out.write(params.getBytes(SystemConstants.DEFAULT_CHARSET));
        out.flush();
        out.close();

        InputStream in = http.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in,
                SystemConstants.DEFAULT_CHARSET));
        String valueString;
        StringBuffer bufferRes = new StringBuffer();
        while ((valueString = read.readLine()) != null) {
            bufferRes.append(valueString);
        }
        in.close();
        if (http != null) {
            http.disconnect();// 关闭连接
        }
        return bufferRes.toString();
    }

    /**
     * @Description: put请求
     * @Params: [url, params, headers]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-07-01 10:59
     */
    public static String put(String url, String params, Map<String, Object> headers)
            throws Exception {
        HttpURLConnection http;
        if (isHttps(url)) {
            http = initHttps(url, SystemConstants.PUT, headers);
        } else {
            http = initHttp(url, SystemConstants.PUT, headers);
        }
        OutputStream out = http.getOutputStream();
        out.write(params.getBytes(SystemConstants.DEFAULT_CHARSET));
        out.flush();
        out.close();

        InputStream in = http.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in,
                SystemConstants.DEFAULT_CHARSET));
        String valueString;
        StringBuffer bufferRes = new StringBuffer();
        while ((valueString = read.readLine()) != null) {
            bufferRes.append(valueString);
        }
        in.close();
        if (http != null) {
            http.disconnect();// 关闭连接
        }
        return bufferRes.toString();
    }

    /**
     * 功能描述: 构造请求参数
     *
     * @return 返回类型:
     * @throws Exception
     */
    public static String initParams(String url, Map<String, Object> params)
            throws Exception {
        if (null == params || params.isEmpty()) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        if (url.indexOf("?") == -1) {
            sb.append("?");
        }
        sb.append(map2Url(params));
        return sb.toString();
    }

    /**
     * map构造url
     *
     * @return 返回类型:
     * @throws Exception
     */
    public static String map2Url(Map<String, Object> paramToMap)
            throws Exception {
        if (null == paramToMap || paramToMap.isEmpty()) {
            return null;
        }
        StringBuffer url = new StringBuffer();
        boolean isfist = true;
        for (Entry<String, Object> entry : paramToMap.entrySet()) {
            if (SysUtil.isEmpty(entry.getValue())) {
                continue;
            }
            if (isfist) {
                isfist = false;
            } else {
                url.append("&");
            }
            url.append(entry.getKey()).append("=");
            String value = entry.getValue().toString();
            if (!StringUtils.isEmpty(value)) {
                url.append(URLEncoder.encode(value, SystemConstants.DEFAULT_CHARSET));
            }
        }
        return url.toString();
    }

    /**
     * 检测是否https
     *
     * @param url
     */
    private static boolean isHttps(String url) {
        return url.startsWith("https");
    }

    /**
     * https 域名校验
     *
     * @return
     */
    public class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;// 直接返回true
        }
    }

    /**
     * @Description:
     * @Params: [url, filePath, params]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-06-27 17:54
     */
    public static String uploadFile(String url, String filePath) throws AppException {
        try {
            HttpClient context = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
            post.setHeader("Accept-Encoding", "gzip, deflate");  //像header这些自己去设置吧

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("name=\"File\"; filename=\"logo.png\"", new File(filePath));//添加文件
            builder.addTextBody("Language", "9");  //添加文本类型参数
            post.setEntity(builder.build());
            HttpResponse response = context.execute(post);
            byte[] res = null;
            //获取参数
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                res = EntityUtils.toByteArray(response.getEntity());
            }
            return new String(res);
        } catch (Exception e) {
            System.err.println("上传文件出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR.getError_code(), "网络异常", "网络异常");
        }
    }

    /**
     * 带证书httpPost请求
     *
     * @param url       接口地址
     * @param param     参数
     * @param PKCS12    证书密码
     * @param fileRoute 证书文件
     * @return
     * @throws Exception
     */
    public static String wechatPayRefund(String url, String param, String PKCS12, String fileRoute) throws Exception {
        //指定读取证书格式为PKCS12
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //读取本机存放的PKCS12证书文件
        FileInputStream instream = new FileInputStream(new File(fileRoute));
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = null;
        try {
            //指定PKCS12的密码
            keyStore.load(instream, PKCS12.toCharArray());
            //指定TLS版本
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, PKCS12.toCharArray())
                    .build();
            //设置httpclient的SSLSocketFactory
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();
            StringBuffer stringBuffer = new StringBuffer();
            HttpPost httpPost = new HttpPost(url);
            InputStream is = new ByteArrayInputStream(param.getBytes("UTF-8"));
            //InputStreamEntity严格是对内容和长度相匹配的。用法和BasicHttpEntity类似
            InputStreamEntity inputStreamEntity = new InputStreamEntity(is, is.available());
            httpPost.setEntity(inputStreamEntity);
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    entity.getContent(), "UTF-8"));
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        } finally {
            httpclient.close();
            instream.close();
            response.close();
        }
    }
}