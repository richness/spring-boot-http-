package com.dmz.dmzServer;

import com.dmz.dmzServer.bean.ConfigBean;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DmzServerApplicationTests {
	private static Logger logger = Logger.getLogger(DmzServerApplication.class);
	@Autowired
	ConfigBean configBean;
	@Test
	public void contextLoads() {
		System.out.println(configBean.getAppId());
	}
@Test
	public void testLog(){
	JSONObject params = new JSONObject();

	params.put("regNo","");
	params.put("creditCode","");
	params.put("orgCode","");
	params.put("entName","中泰证券股份有限公司");
	// 请求体
	String body = params.toJSONString();
	String requestSn = UUID.randomUUID().toString();
	System.out.println("requestSn:"+requestSn);
	// 请求时间戳
	long timestamp = new Date().getTime();
	System.out.println("timestamp:"+timestamp);
	// 接口地址，请根据实际情况填写，此处以工商简项为例
	String apiUrl = "https://api.elecredit.com/saic/getLaunderInfo";
	// 请联系商务负责人获取
	String appId = "613324319811964928";
	// 请联系商务负责人获取
	String appKey = "19828e94807c4b27a8fcfcdefec83f3b";
	// 生成签名
	String sign = DigestUtils.md5Hex(requestSn + timestamp + body + appKey);
	System.out.println("sign:"+sign);
	}



}
