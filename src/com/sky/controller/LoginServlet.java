package com.sky.controller;

import com.sky.initialize.StaticVariable;
import com.sky.utils.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StaticVariable.setEcoding(request, response);
        String username = request.getParameter("lusername");
        String pwd = request.getParameter("lpwd");
        //只要访问登录页面成功就写一个cookie
        Cookie cookie = new Cookie("jdlogin", username);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM `user` WHERE username=? AND pwd=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, pwd);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                //登录成功
                HttpSession session = request.getSession();
                session.setAttribute("lusername", username);
                if (username.equals("admin")) {
                    JDBCUtil.close(statement, connection);
                    response.sendRedirect("adminView.jsp");
                } else {
                    JDBCUtil.close(statement, connection);
                    response.sendRedirect("userView.jsp");
                }
            } else {
                //登录失败
                HttpSession session = request.getSession();
                session.setAttribute("loginError", "用户名或密码错误");
                session.setAttribute("lusername", username);
                JDBCUtil.close(statement, connection);
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
