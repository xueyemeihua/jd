package com.sky.controller;

import com.sky.pojo.Business;
import com.sky.utils.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoUnPublishBusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _upbid = request.getParameter("upbid");
        int upbid = Integer.parseInt(_upbid);
        try {
            //当集合有对象并且存在用户输入的编号则执行上架操作
            Connection connection = JDBCUtil.getConnection();
            String sql = "UPDATE business SET bcod=0 WHERE bid=? AND bcod=1";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, upbid);
            System.out.println(upbid);
            int i = statement.executeUpdate();
            statement.close();
            connection.close();
            if (i > 0) {
                request.setAttribute("upbError", "");
                request.getRequestDispatcher("/adminView.jsp").forward(request, response);
            } else {
                request.setAttribute("upbError", "输入的编号不合理");
                request.getRequestDispatcher("/nUnPublishBus.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("upbError", "连接数据库失败");
            request.getRequestDispatcher("/nUnPublishBus.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
