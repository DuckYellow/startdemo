package com.example.startdemo.learndemo.transaction;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;
import java.sql.*;

public class JTA {
    private Connection connA = null;
    private Connection connB = null;
    private PreparedStatement psA = null;
    private PreparedStatement psB = null;

    public void transferAccount() {
        UserTransaction userTx = null;
        try {
            Context context = new InitialContext();
            userTx = (UserTransaction) context.lookup("java:comp/UserTransaction");
// 从数据库 A 中取得数据库连接
            connA = DriverManager.getConnection(null, null, null);
// 从数据库 B 中取得数据库连接
            connB = DriverManager.getConnection(null, null, null);
// 启动事务
            userTx.begin();
// 将 A 账户中的金额减少 500
            psA.executeUpdate("修改SQL"); //执行修改操作
            // 将 B 账户中的金额增加 500
            psB.executeUpdate("修改SQL"); //执行修改操作
            // 提交事务
            userTx.commit();
        } catch (SQLException sqle) {
            try {
                userTx.rollback();
                psA.close();
                psB.close();
            } catch (Exception ignore) {
                System.out.println(ignore);
            }
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
