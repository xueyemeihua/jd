package com.sky.controller;

import com.sky.initialize.StaticVariable;
import com.sky.utils.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StaticVariable.setEcoding(request, response);
        String username = request.getParameter("rusername");
        String pwd = request.getParameter("rpwd");
        //只要访问登录页面成功就写一个cookie
        Cookie cookie = new Cookie("jdlogin", username);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
        if (username.length() > 18 || username.length() < 3 || pwd.length() > 18 || pwd.length() < 3 || username.equals("admin")) {
            HttpSession session = request.getSession();
            session.setAttribute("rusername", username);
            session.setAttribute("registerError", "用户名或者密码不符合规范");
            response.sendRedirect("register.jsp");
        } else {
            try {
                Connection connection = JDBCUtil.getConnection();
                String sql = "INSERT INTO `user` (username,pwd) VALUES (?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, pwd);
                statement.executeUpdate();
                //注册成功
                JDBCUtil.close(statement, connection);
                response.sendRedirect("login.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
                //注册失败返回注册页面并且提示
                HttpSession session = request.getSession();
                session.setAttribute("rusername", username);
                session.setAttribute("registerError", "数据库连接失败");
                response.sendRedirect("register.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
