package com.dmz.dmzServer.Utils;


import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.channels.Channel;
import java.util.Date;

/**
 * 转发拦截器 请求参数签名校验
 * Created by 陈新会on 14:47 2019/09/17
 */
@Slf4j
public class ApiSignInterceptor implements HandlerInterceptor {


    private final static String SEPERATOR = "_";
    private final static String SECRET = "jzyydmz";
    private final static String NO_PERMISSION_ERROR_MESSAGE = "Api Token Error, You have no permission to access this api";

    // md5计算
    private String md5Hex(String data) {
        return DigestUtils.md5Hex(data).toLowerCase();
    }

    private String getSign(String t) {
        return md5Hex(t + SEPERATOR + SECRET);
    }

    // sign计算，t为时间戳,sign为md5(t+"_"+"jzyydmz")

    @Override

    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String t = request.getParameter("timestamp");
            String sign = request.getParameter("signature");

            if (t.isEmpty() || sign.isEmpty()) {
                response.sendError(403, NO_PERMISSION_ERROR_MESSAGE);
                return false;
            }


            String expectedSign = getSign(t);

            if (!expectedSign.equals(sign)) {
                response.sendError(403, NO_PERMISSION_ERROR_MESSAGE);
                return false;
            }

        } catch (Throwable t) {
            response.sendError(403, NO_PERMISSION_ERROR_MESSAGE);
            return false;
        }

        return true;

    }

}