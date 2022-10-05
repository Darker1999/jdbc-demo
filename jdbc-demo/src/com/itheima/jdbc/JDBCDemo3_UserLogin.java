package com.itheima.jdbc;
/*
@author Darker
@create 2022-09-18-16:11

*/

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo3_UserLogin {

    @Test
    public void testUserLogin() throws Exception{

//        2.获取连接
        String url= "jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
        String username= "root";
        String password= "123456";
        Connection conn = DriverManager.getConnection(url, username, password);

        //接收用户输入的  用户名和密码
        String name = "zhangsan";
        String pwd = "123";

        String sql = "SELECT * FROM tb_user WHERE username = '"+name+"' AND password = '"+pwd+"'";
        //获取stmt对象
        Statement stmt = conn.createStatement();
        //执行sql
        ResultSet rs = stmt.executeQuery(sql);
        //判断是否登录成功
        if (rs.next()){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }

//        7.释放资源
        rs.close();
        stmt.close();
        conn.close();

    }

// sql注入演示：
    @Test
    public void testUserLogin_Inject() throws Exception{

//        2.获取连接
        String url= "jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
        String username= "root";
        String password= "123456";
        Connection conn = DriverManager.getConnection(url, username, password);

        //接收用户输入的  用户名和密码
        String name = "luanxie";
        String pwd = "' or '1' = '1";

        String sql = "SELECT * FROM tb_user WHERE username = '"+name+"' AND password = '"+pwd+"'";
        //获取stmt对象
        Statement stmt = conn.createStatement();

        //执行sql
        ResultSet rs = stmt.executeQuery(sql);
        //判断是否登录成功
        if (rs.next()){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }

//        7.释放资源
        rs.close();
        stmt.close();
        conn.close();

    }
}
