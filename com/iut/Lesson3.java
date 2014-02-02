package com.iut;

import java.sql.Connection;
import java.util.List;

public class Lesson3 {

	public static void main(String[] args) throws Exception {
		TableUserManager tum = new TableUserManager();
		
		Connection conn = Singleton.DS.getConnection();
		display_all(tum.readAll(conn));
		display_one(tum.read(conn));
		
		System.out.println(Hasher.hashPseudoPassword("ben", "coucou"));
//		System.out.println(tum.create(conn, "ben", "coucou"));
		
		TableUser tu = new TableUser();
		tu.setPassword("coucou");
		tu.setName_user("ben");
		System.out.println(tum.create(conn, tu));
		conn.commit();
	}
	
	public static void display_all(List<Table> list) {
        for (Table t : list ){
        	TableUser tu = null;
        	tu = (TableUser) t;
            System.out.println(tu.getId_user() + ", " + tu.getName_user() + ", " + tu.getPassword());
        }
    }
	
	public static void display_one(Table t) {
    	TableUser tu = null;
    	tu = (TableUser) t;
        System.out.println(tu.getId_user() + ", " + tu.getName_user() + ", " + tu.getPassword());
    }
}
