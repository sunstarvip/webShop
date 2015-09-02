package com.darknight.webShop.system.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DarKnight on 2015/8/30.
 */
public class LonginFilter implements Filter {
    private String excludedUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();

        if(session != null && session.getAttribute("loginUserInfo") != null) {
            JSONObject loginUserJson = JSON.parseObject(session.getAttribute("loginUserInfo").toString());
            if(loginUserJson.get("merchantAccount") != null) {
                String loginId = loginUserJson.get("merchantAccount").toString();
                if(StringUtils.isNotBlank(loginId)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }

        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String ctx = request.getContextPath();
        String targetUri = request.getRequestURI();
        String queryString = request.getQueryString();
        if(StringUtils.isNotBlank(queryString)) {
            queryString = Base64.encodeBase64URLSafeString(queryString.getBytes());
        }
        response.sendRedirect(ctx + "/loginPage?targetUri=" + targetUri + "&queryString=" + queryString);
    }

    @Override
    public void destroy() {

    }
}
