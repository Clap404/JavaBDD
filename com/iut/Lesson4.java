package com.iut;

import java.sql.Connection;
import java.util.List;

public class Lesson4 {

	public static void main(String[] args) throws Exception {
		Connection conn = Singleton.DS.getConnection();
				
		TableArticleManager tum = new TableArticleManager();
		
		TableArticle tu = new TableArticle();
		tu.setId_article(1);
//		tu.setName_article("Nourriture");
		
//		tum.create(conn, tu);
//		tum.read(conn, tu);
//		tum.update(conn, tu);
//		tum.delete(conn, tu);
		
		display_all(tum.readAll(conn, null));
		display_one(tum.read(conn, tu));
		
		conn.commit();
	}

	public static void display_all(List<?> list) {
		for (Object t : list ){
			System.out.println(t);
		}
	}
	public static void display_one(Object o) {
		System.out.println(o);
	}
}
