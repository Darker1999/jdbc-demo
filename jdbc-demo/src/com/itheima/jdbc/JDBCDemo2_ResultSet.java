package com.itheima.jdbc;
/*
@author Darker
@create 2022-09-18-16:11

*/

import org.junit.Test;
import com.itheima.pojo.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo2_ResultSet {

   @Test
    public void testResultSet() throws Exception {
//        1.注册驱动
//       Class.forName("com.mysql.jdbc.Driver");
//        2.获取连接
       String url= "jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
       String username= "root";
       String password= "123456";
       Connection conn = DriverManager.getConnection(url, username, password);
//        3.定义sql
       String sql = "SELECT * FROM account";
//        4.获取执行sql的对象 Statement
       Statement stmt = conn.createStatement();
//        5.执行sql
       ResultSet rs = stmt.executeQuery(sql);

//       创建集合：
       List<Account> list = new ArrayList<>();

//        6.处理结果
//          6.1.光标向下移动一行，并且判断当前行是否有数据
       while (rs.next()){
           Account account = new Account();
//          6.2. 获取数据 getXxx()
           int id = rs.getInt("id");
           String name = rs.getString("name");
           double money = rs.getDouble("money");
//          6.3. 赋值
           account.setId(id);
           account.setName(name);
           account.setMoney(money);
//           存入集合
           list.add(account);
       }

       System.out.println(list);

//        7.释放资源
       rs.close();
       stmt.close();
       conn.close();

   }
}
