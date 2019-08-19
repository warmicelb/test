package com.ice.database;

import java.sql.*;

/**
 * @ClassName JDBCTool
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/3 4:24 PM
 **/
public class JDBCTool {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://47.75.240.173:3306/practice?useUnicode=true&characterEncoding=utf8&useSSL=false&useOldAliasMetadataBehavior=true&autoReconnect=true&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static String username = "root";
    private static String password = "abcd1234";
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void close(Connection connection, PreparedStatement ps, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
