package com.itheima.jdbc;
/*
@author Darker
@create 2022-09-18-16:11

*/

import org.junit.Test;

import java.sql.*;

public class JDBCDemo4_PrepareStatment {
//  通过 PrepareStatment 防止 sql注入
    @Test
    public void testPrepareStatment() throws Exception{

//        2.获取连接
        String url= "jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
        String username= "root";
        String password= "123456";
        Connection conn = DriverManager.getConnection(url, username, password);

        //接收用户输入的  用户名和密码
        String name = "zhangsan";
        String pwd = "' or '1' = '1";

        //定义sql
        String sql = "select * from tb_user where username = ? and password = ?";
        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //设置 ？ 的值
        pstmt.setString(1,name);
        pstmt.setString(2,pwd);
        //执行sql
        ResultSet rs = pstmt.executeQuery();

        //判断是否登录成功
        if (rs.next()){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }

//        7.释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }



}
