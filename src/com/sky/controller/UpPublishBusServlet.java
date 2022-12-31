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

public class UpPublishBusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //先查询出所有上架业务并存入集合
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM business WHERE bcod=1";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            //将查询的数据存入对象集合中,再存入域对象
            List<Business> pbuss = new ArrayList<>();
            while (rs.next()) {
                Integer bid = rs.getInt("bid");
                String btitle = rs.getString("btitle");
                String binfo = rs.getString("binfo");
                Integer bprice = rs.getInt("bprice");
                Integer bcod = rs.getInt("bcod");
                pbuss.add(new Business(bid, btitle, binfo, bprice, bcod));
            }
            statement.close();
            connection.close();
            if (pbuss.size() > 0) {
                //将查出的集合存入域对象
                request.setAttribute("pbuss", pbuss);
                request.setAttribute("upbError", "");
                request.getRequestDispatcher("/unPublishBus.jsp").forward(request, response);
            } else {
                request.setAttribute("upbError", "没有要下架的业务");
                request.getRequestDispatcher("/nUnPublishBus.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("upbError", "查询数据库失败");
            request.getRequestDispatcher("/nUnPublishBus.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
