package com.sky.controller;

import com.sky.initialize.StaticVariable;
import com.sky.utils.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddBusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StaticVariable.setEcoding(request, response);
        String btitle = request.getParameter("btitle");
        String binfo = request.getParameter("binfo");
        String _bprice = request.getParameter("bprice");
        String _bcod = request.getParameter("bcod");
        try {
            int bprice = Integer.parseInt(_bprice);
            int bcod = Integer.parseInt(_bcod);
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO `business`(btitle,binfo,bprice,bcod) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, btitle);
            statement.setString(2, binfo);
            statement.setInt(3, bprice);
            statement.setInt(4, bcod);
            statement.executeUpdate();
            JDBCUtil.close(statement, connection);
            //添加成功重定向到管理员页面
            response.sendRedirect("adminView.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            HttpSession session = request.getSession();
            session.setAttribute("addBusError", "业务信息输入不合理");
            response.sendRedirect("addBus.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
