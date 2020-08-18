package com.crm.active.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: extra
 * @description: 跨域
 * @author: wq
 * @create: 2020-08-15 09:52
 */
@WebFilter(urlPatterns = "/*", filterName = "corsFilter")
public class CorsFilter implements Filter {

  public static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    logger.info("初始化跨域过滤链");
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {

    HttpServletResponse response = (HttpServletResponse) res;
    HttpServletRequest request = (HttpServletRequest) req;
    // String path = request.getServletPath();
    response.setHeader("Access-Control-Allow-Origin", "**************");
    // 前端项目的域名
    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
    response.setHeader("Access-Control-Max-Age", "3600");
    // 设置允许访问cookie
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader(
        "Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

    if (request.getMethod().equals("OPTIONS")) {
      response.setStatus(HttpServletResponse.SC_OK);
    } else {
      chain.doFilter(request, res);
    }
  }

  @Override
  public void destroy() {
    logger.info("销毁跨域过滤链");
  }
}
