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

public class PubBusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询未上架的业务并展示在上架页面
        try {
            //先查询出所有上架业务并存入集合
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM business WHERE bcod=0";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            //将查询的数据存入对象集合中,再存入域对象
            List<Business> unpbuss = new ArrayList<>();
            while (rs.next()) {
                Integer bid = rs.getInt("bid");
                String btitle = rs.getString("btitle");
                String binfo = rs.getString("binfo");
                Integer bprice = rs.getInt("bprice");
                Integer bcod = rs.getInt("bcod");
                unpbuss.add(new Business(bid, btitle, binfo, bprice, bcod));
            }
            statement.close();
            connection.close();
            if (unpbuss.size() > 0) {
                //将查出的集合存入域对象
                request.setAttribute("unpbuss", unpbuss);
                request.setAttribute("pbError", "");
                request.getRequestDispatcher("/pubBus.jsp").forward(request, response);
            } else {
                request.setAttribute("pbError", "没有要上架的业务");
                request.getRequestDispatcher("/nPublishBus.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("pbError", "查询数据库失败");
            request.getRequestDispatcher("/nPublishBus.jsp").forward(request, response);
        }
    }
}
