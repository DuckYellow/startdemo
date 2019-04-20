package com.example.startdemo.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {

    private Connection conn = null;
    private PreparedStatement ps = null;

    public void con(String[] args) throws SQLException {
        try {
            conn.setAutoCommit(false);  //将自动提交设置为false
            ps.executeUpdate("修改SQL"); //执行修改操作
            ps.executeQuery("查询SQL");  //执行查询操作
            conn.commit();      //当两个操作成功后手动提交
        } catch (Exception e) {
            conn.rollback();    //一旦其中一个操作出错都将回滚，使两个操作都不成功
            e.printStackTrace();
        }
    }
}
