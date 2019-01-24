package com.itcast.test;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.junit.Test;

import java.sql.*;

/**
 * @class: OracleTest
 * @description:
 * @author: 程序员小贾
 * @create: 2019-01-23 19:36
 **/

public class OracleTest {

    @Test
    public void testConnectToOracle() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.80.10:1521:orcl", "tony", "tony");
        PreparedStatement ps = connection.prepareStatement("select * from emp where empno = ?");
        ps.setObject(1,7788);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getObject("ename"));
        }
        rs.close();
        ps.close();
        connection.close();
    }


    @Test
    public void testProcedure() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.80.10:1521:orcl", "tony", "tony");
        CallableStatement cs = connection.prepareCall("{call p_yearsal(?,?)}");
        cs.setObject(1,7788);
        cs.registerOutParameter(2, OracleTypes.NUMBER);
        cs.execute();
        System.out.println(cs.getObject(2));
        connection.close();
    }

    @Test
    public void testFunction() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.80.10:1521:orcl", "tony", "tony");
        CallableStatement cs = connection.prepareCall("{?=call getTotal(?)}");
        cs.setObject(2,7788);
        cs.registerOutParameter(1, OracleTypes.NUMBER);
        cs.execute();
        System.out.println(cs.getObject(1));
        connection.close();
    }


    public void test(){
        int i = 0;
        System.out.println(i);
    }


}
