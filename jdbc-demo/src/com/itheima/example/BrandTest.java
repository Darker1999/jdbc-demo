package com.itheima.example;
/*
@author Darker
@create 2022-09-18-21:28

    品牌数据的增删改查

*/

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.itheima.pojo.Brand;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BrandTest {
    @Test
    public void testSelectAll() throws Exception {
//        加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("../jdbc-demo/src/druid.properties"));

//        获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
//        获取数据库连接 Connection
        Connection conn = dataSource.getConnection();
        System.out.println(conn);

        String sql = "select * from tb_brand;";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();


        Brand brand = null;
        List<Brand> brands = new ArrayList<>();
        while(rs.next()){
            //获取数据
            int id = rs.getInt("id");
            String brandName = rs.getString("brand_name");
            String companyName = rs.getString("company_name");
            int ordered = rs.getInt("ordered");
            String description = rs.getString("description");
            int status = rs.getInt("status");
            //封装Brand对象
            brand = new Brand();

            brand.setId(id);
            brand.setBrandName(brandName);
            brand.setCompanyName(companyName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);
            //装载集合
            brands.add(brand);

        }
        System.out.println(brands);

        rs.close();
        pstmt.close();
        conn.close();
    }
}
