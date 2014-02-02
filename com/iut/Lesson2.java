package com.iut;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class Lesson2
{
    public static void main(String[] args)
    {
        Connection conn = null;
        try
        {
            conn = Singleton.DS.getConnection();

            Table1Manager mng = new Table1Manager();
            mng.delete(conn, 9);

            System.out.println("debut");

            List all = mng.read_all(conn);
            display_all(all);

            System.out.println("insert");

            Table1 new_table1 = new Table1();
            new_table1.setC1(9);
            new_table1.setC2(new BigDecimal("99.99"));
            mng.create(conn, new_table1);

            System.out.println("update");

            Table1 table1 = mng.read(conn, 9);
            table1.setC2(new BigDecimal("88.88"));
            mng.update(conn, 9, table1);

            System.out.println("résultat");

            all = mng.read_all(conn);
            display_all(all);

            System.out.println("delete");

            mng.delete(conn, 9);

            System.out.println("résultat");

            all = mng.read_all(conn);
            display_all(all);

            System.out.println("insert");

            new_table1 = new Table1();
            new_table1.setC1(13);
            new_table1.setC2(new BigDecimal("13.13"));
            mng.create(conn, new_table1);

            System.out.println("résultat");

            all = mng.read_all(conn);
            display_all(all);

            System.out.println("fin");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            }
            catch (Exception localException1) {  }
        } finally {
            try { conn.close(); } catch (Exception localException2) {}
        }
    }

    public static void display_all(List<Table1> all) {
        for (Table1 table1 : all)
            System.out.println(table1.getC1() + ", " + table1.getC2());
    }
}
