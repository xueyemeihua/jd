package com.sky.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class VolidLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //获得链接和登录名
        String requestURI = request.getRequestURI();
        Object lusername = request.getSession().getAttribute("lusername");
        if (lusername!=null || requestURI.indexOf("login")>=0 || requestURI.indexOf("register")>=0 || requestURI.indexOf("index")>=0) {
            //只要有登录过或者在做登录注册主页请求,过滤器就放行
            filterChain.doFilter(request,servletResponse);
        } else {
            //否则拦截
            request.getRequestDispatcher("login.jsp").forward(request, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
