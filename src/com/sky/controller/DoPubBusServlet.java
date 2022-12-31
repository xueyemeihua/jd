package com.sky.controller;

import com.sky.utils.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoPubBusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _pbid = request.getParameter("pbid");
        int pbid = Integer.parseInt(_pbid);
        try {
            //当集合有对象并且存在用户输入的编号则执行上架操作
            Connection connection = JDBCUtil.getConnection();
            String sql = "UPDATE business SET bcod=1 WHERE bid=? AND bcod=0";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pbid);
            System.out.println(pbid);
            int i = statement.executeUpdate();
            statement.close();
            connection.close();
            if (i > 0) {
                request.setAttribute("pbError", "");
                request.getRequestDispatcher("/adminView.jsp").forward(request, response);
            } else {
                request.setAttribute("pbError", "输入的编号不合理");
                request.getRequestDispatcher("/nPublishBus.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("pbError", "连接数据库失败");
            request.getRequestDispatcher("/nPublishBus.jsp").forward(request, response);
        }

    }
}
