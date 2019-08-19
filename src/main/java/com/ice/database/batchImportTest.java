package com.ice.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 批量导入测试
 * @ClassName batchImportTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/3 4:04 PM
 **/
public class batchImportTest {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        batchImport1();
        long end = System.currentTimeMillis();
        System.out.println("batchImport1:"+(end-begin));
        long begin2 = System.currentTimeMillis();
        batchImport2();
        long end2 = System.currentTimeMillis();
        System.out.println("batchImport2:"+(end2-begin2));
        long begin3 = System.currentTimeMillis();
        batchImport3();
        long end3 = System.currentTimeMillis();
        System.out.println("batchImport3:"+(end3-begin3));
        long begin4 = System.currentTimeMillis();
        batchImport4();
        long end4 = System.currentTimeMillis();
        System.out.println("batchImport4:"+(end4-begin4));
        long begin5 = System.currentTimeMillis();
        batchImport5();
        long end5 = System.currentTimeMillis();
        System.out.println("batchImport5:"+(end5-begin5));
        long begin6 = System.currentTimeMillis();
        batchImport6();
        long end6 = System.currentTimeMillis();
        System.out.println("batchImport6:"+(end6-begin6));
        long begin7 = System.currentTimeMillis();
        batchImport7();
        long end7 = System.currentTimeMillis();
        System.out.println("batchImport7:"+(end7-begin7));
    }

    /**
     * 关闭自动提交,批量插入，使用预编译
     */
    public static void batchImport1(){
        Connection connection = JDBCTool.getConnection();
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("insert into batch_import (name,nick_name) values (?,?)");
            for(int i=0;i<1000;i++){
                ps.setString(1,"ice");
                ps.setString(2,"spp");
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCTool.close(connection,ps,null);
    }

    /**
     * 自动提交，单条插入，预编译sql
     */
    public static void batchImport2(){
        Connection connection = JDBCTool.getConnection();
        PreparedStatement ps = null;
        try {
//            connection.setAutoCommit(false);
            ps = connection.prepareStatement("insert into batch_import (name,nick_name) values (?,?)");
            for(int i=0;i<1000;i++){
                ps.setString(1,"ice");
                ps.setString(2,"spp");
                ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCTool.close(connection,ps,null);
    }

    /**
     * 关闭自动提交，单条插入，预编译sql
     */
    public static void batchImport3(){
        Connection connection = JDBCTool.getConnection();
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("insert into batch_import (name,nick_name) values (?,?)");
            for(int i=0;i<1000;i++){
                ps.setString(1,"ice");
                ps.setString(2,"spp");
                ps.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCTool.close(connection,ps,null);
    }

    public static void batchImport4(){
        Connection connection = JDBCTool.getConnection();
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            for(int i=0;i<1000;i++){
                statement.execute("insert into batch_import (name,nick_name) values ('ice','spp')");
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCTool.close(connection,ps,null);
    }
    public static void batchImport5(){
        Connection connection = JDBCTool.getConnection();
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("insert into batch_import (name,nick_name) values ");
            for(int i=0;i<1000;i++){
                sb.append("('ice','spp')," );
            }
            statement.execute(sb.substring(0,sb.length()-1));
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCTool.close(connection,ps,null);
    }
    public static void batchImport6(){
        Connection connection = JDBCTool.getConnection();
        PreparedStatement ps = null;
        try {
            Statement statement = connection.createStatement();
            for(int i=0;i<1000;i++){
                statement.execute("insert into batch_import (name,nick_name) values ('ice','spp')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCTool.close(connection,ps,null);
    }
    public static void batchImport7(){
        Connection connection = JDBCTool.getConnection();
        PreparedStatement ps = null;
        try {
            Statement statement = connection.createStatement();
            for(int i=0;i<1000;i++){
                statement.execute("insert into batch_import (name,nick_name) values ('ice','spp')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCTool.close(connection,ps,null);
    }
}
