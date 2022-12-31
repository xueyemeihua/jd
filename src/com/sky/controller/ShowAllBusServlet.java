package com.sky.controller;

import com.sky.pojo.Business;
import com.sky.utils.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowAllBusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM business";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            //将查询的数据存入对象集合中,再存入域对象
            List<Business> buss = new ArrayList<>();
            while (rs.next()) {
                Integer bid = rs.getInt("bid");
                String btitle = rs.getString("btitle");
                String binfo = rs.getString("binfo");
                Integer bprice = rs.getInt("bprice");
                Integer bcod = rs.getInt("bcod");
                buss.add(new Business(bid, btitle, binfo, bprice, bcod));
            }
            statement.close();
            connection.close();
            if (buss.size() > 0) {
                request.setAttribute("buss", buss);
                request.getRequestDispatcher("showAllBus.jsp").forward(request, response);
            } else {
                request.setAttribute("buss", "没有数据");
                request.getRequestDispatcher("doShowAllBus.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("buss", "连接数据库失败");
            request.getRequestDispatcher("doShowAllBus.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
