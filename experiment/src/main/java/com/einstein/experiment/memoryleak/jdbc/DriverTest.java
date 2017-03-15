package com.einstein.experiment.memoryleak.jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author liujiaming
 * @since 2017/03/15
 *
 */
public class DriverTest {

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String dbName = "test";
        String userName = "root";
        String password = "android";
        String url = "jdbc:mysql://127.0.0.1:3306/" + dbName;
        String sql = "select * from white_seller";

        try {
            Class.forName(driver);
            java.sql.Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs.getFetchSize());
            while (rs.next()) {
                System.out.println("id : " + rs.getInt(1) + " name : " + rs.getString(2) + " password : " + rs.getString(3));
            }

            // 关闭记录集
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // 关闭声明
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // 关闭链接对象
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
