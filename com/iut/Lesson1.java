package com.iut;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Lesson1 {
    
	public static void main(String[] args) {
    
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstm = null;
		
		try {

            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost/javaBDD", "root", "");

            stmt = conn.createStatement();
            String sql = "create table table1 (c1 integer primary key, c2 numeric(11,2) not null)";
//
//            stmt.executeUpdate(sql);

            int c1 = 2;
            BigDecimal c2 = new BigDecimal("2.22");
            sql = "insert into table1 (c1, c2) values(? , ?)";

            pstm = conn.prepareStatement(sql);

            int i = 1;
            pstm.setInt(i++, c1);
            pstm.setBigDecimal(i++, c2);

            int n = pstm.executeUpdate();

            System.out.println(n);

            String select = "select *from table1";
            ResultSet rset = stmt.executeQuery(select);

            while(rset.next()){
                int r1 = rset.getInt("c1");
                BigDecimal r2 = rset.getBigDecimal("c2");

                System.out.println("r1=" + r1 + ", r2=" + r2.toString());
            }

            ResultSetMetaData rsmd = rset.getMetaData();
            int c = rsmd.getColumnCount();

            while(rset.next()){
                for (int j=1; j<=c; j++) {
                    System.out.println(rsmd.getColumnName(j) + " = " + rset.getString(j));
                }

                int r1 = rset.getInt("c1");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                stmt.close();
            }
            catch (Exception ignore) {}
            try {
                conn.close();
            }
            catch (Exception ignore) {}
            try {
            	pstm.close();
            }
            catch (Exception ignore) {}
        }
    }
}

