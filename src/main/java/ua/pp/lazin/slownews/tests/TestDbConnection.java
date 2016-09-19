package ua.pp.lazin.slownews.tests;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class TestDbConnection {

    public static void testDBConnection() throws Exception {

        Context initContext= new InitialContext();
        DataSource dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/slownews");

        Connection con = null;
        try {
            con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from iuser");
            int cnt = 1;
            while (rs.next()) {
                System.out.println((cnt++) + ". name:" + rs.getString("name") +
                        " surname:" + rs.getString("surname") + " balance:" + rs.getDouble("balance"));
            }
            rs.close();
            st.close();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

}