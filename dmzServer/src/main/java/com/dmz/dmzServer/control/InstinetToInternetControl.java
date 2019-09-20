package com.dmz.dmzServer.control;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/innerToOut")
public class InstinetToInternetControl {
    private static Logger logger = Logger.getLogger(InstinetToInternetControl.class);

    @RequestMapping(value = "/out", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String InnerToOutServer(@RequestBody Map<String, Object> reqestBody) throws IOException {
        logger.info("请求信息：" + reqestBody);
        Map dmzBody = (Map) reqestBody.get("dmzBody");
        Map dmzHeader = (Map) reqestBody.get("dmzHeader");
        String apiUrl = (String) reqestBody.get("apiUrl");
        // 请求体
        String body = JSON.toJSONString(dmzBody);

////        验证数据
//        String requestSn = UUID.randomUUID().toString();
//        // 请求时间戳
//        long timestamp = new Date().getTime();
//        String appKey = "19828e94807c4b27a8fcfcdefec83f3b";
//        // 生成签名
//        String sign = DigestUtils.md5Hex(requestSn + timestamp + body + appKey);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(apiUrl);
//        设置请求头循环遍历
        Iterator<Entry<String, String>> iterator = dmzHeader.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = iterator.next();
            httpPost.setHeader(elem.getKey(), elem.getValue());
        }

//        httpPost.setHeader("timestamp",timestamp + "");
//        httpPost.setHeader("request-sn",requestSn);
//        httpPost.setHeader("signature",sign);


        httpPost.setEntity(new StringEntity(body, "UTF-8"));
        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        logger.info(entity);
        String entityStr = EntityUtils.toString(entity, "UTF-8");
        logger.info("响应内容：" + entityStr);
        return entityStr;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String testHttp(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request);

        return "";
    }
}
