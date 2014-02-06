package com.iut;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Lesson4 {

    public static void main(String[] args) throws Exception {
        Connection conn = Singleton.DS.getConnection();
        
        //génération de la base
        TableUserManager tUse = new TableUserManager(); 
        TableCartManager tCar = new TableCartManager(); 
        TableCategoryManager tCat = new TableCategoryManager(); 
        TableArticleManager tArt = new TableArticleManager(); 
        TableContainsManager tCon = new TableContainsManager(); 

        tCon.drop(conn);
        tArt.drop(conn);
        tCat.drop(conn);
        tCar.drop(conn);
        tUse.drop(conn);

        tUse.generate(conn);
        tCar.generate(conn);
        tCat.generate(conn);
        tArt.generate(conn);
        tCon.generate(conn);
        
        conn.commit();
        
        ///////////////////////////////////////////////////////////////////////
        System.out.println("\nCréation de catégories d'articles");
        TableCategoryManager tcm = new TableCategoryManager();
        
        TableCategory tc = new TableCategory();
        
        tc.setName_cat("Appareils Photos");
        tcm.create(conn, tc);
        
        tc.setName_cat("Zooms & Objectifs");
        tcm.create(conn, tc);
        
        display_all(tcm.readAll(conn, null));
        
        ///////////////////////////////////////////////////////////////////////
        System.out.println("\nCréation d'articles");
        TableArticleManager tam = new TableArticleManager();
        
        TableArticle ta = new TableArticle();
        ta.setId_cat(1);
        ta.setPrice_article(new BigDecimal(1600));
        ta.setName_article("Reflex Numérique");
        ta.setDescription_article("Il est cher, mais il est bien !");
        ta.setStock_article(2);

        tam.create(conn, ta);
        
        ta.setPrice_article(new BigDecimal(530));
        ta.setName_article("Kit Reflex Numérique + 18-55mm");
        ta.setDescription_article("Indispensable pour tout bon photographe.");
        ta.setStock_article(1);
        
        tam.create(conn, ta);
        
        ta.setId_cat(2);
        ta.setPrice_article(new BigDecimal(748));
        ta.setName_article("Zoom 24-105");
        ta.setDescription_article("");
        ta.setStock_article(5);
        
        tam.create(conn, ta);
        
        ta.setId_cat(2);
        ta.setPrice_article(new BigDecimal(354.90));
        ta.setName_article("Objectif 85");
        ta.setDescription_article("");
        ta.setStock_article(2);
        
        tam.create(conn, ta);
        
        ta.setId_cat(2);
        ta.setPrice_article(new BigDecimal(17.80));
        ta.setName_article("Filtre");
        ta.setDescription_article("");
        ta.setStock_article(17);
        
        tam.create(conn, ta);
        
        display_all(tam.readAll(conn, null));
        ///////////////////////////////////////////////////////////////////////
        System.out.println("\nCréation d'utilisateurs");
        TableUserManager tum = new TableUserManager();
        
        TableUser tu = new TableUser();
        tu.setName_user("User1");
        tu.setPassword("password");
        tum.create(conn, tu);
        
        tu.setName_user("User2");
        tum.create(conn, tu);
        
        display_all(tum.readAll(conn, null));
        ///////////////////////////////////////////////////////////////////////
        System.out.println("\nCréation de paniers");
        TableCartManager tcam = new TableCartManager();
        
        TableCart tca = new TableCart();
        tca.setId_user(1);
        tca.setId_cart(1);
        tca.addContent(2, 1);
        tca.addContent(5, 2);
        tca.addContent(4, 1);
        tcam.create(conn, tca);
        
        tca = new TableCart();
        tca.setId_user(2);
        tca.setId_cart(2);
        tca.addContent(1, 1);
        tca.addContent(3, 1);
        tca.addContent(4, 1);
        tca.addContent(5, 2);
        tcam.create(conn, tca);
        
        display_all(tcam.readAll(conn, null));
        ///////////////////////////////////////////////////////////////////////
        System.out.println("\nModification de paniers");
        TableContainsManager tcnsm = new TableContainsManager();
        
        TableContains tcns = new TableContains();
        tcns.setId_article(5);
        tcns.setId_cart(2);
        tcns.setQty(0);
        tcnsm.update(conn, tcns);
        
        tcns.setId_article(1);
        tcns.setQty(2);
        tcnsm.update(conn, tcns);
        
        display_all(tcam.readAll(conn, null));
        ///////////////////////////////////////////////////////////////////////
        System.out.println("\nTotaux des paniers");
        System.out.println( "Prix du panier 1: TTC:" + tcam.getPrice(conn, 1) + " HT:"
                + tcam.getPrice(conn, 1).multiply(new BigDecimal("0.8")) );
        System.out.println( "Prix du panier 2: TTC:" + tcam.getPrice(conn, 2) + " HT:"
                + tcam.getPrice(conn, 2).multiply(new BigDecimal("0.8")) );
        
        ///////////////////////////////////////////////////////////////////////
        
        System.out.println("\nValidation des paniers");
        tcam.buy(conn, 1);
        tcam.buy(conn, 2);
        display_all(tcam.readAll(conn, null));
        display_all(tam.readAll(conn, null));
        
        ///////////////////////////////////////////////////////////////////////
        System.out.println("\nMeilleures ventes cette semaine:");
        display_alll(tam.getBestSellers(conn));
        
        conn.commit();
    }

    public static void display_all(List<?> list) {
        for (Object t : list ){
            System.out.println(t);
        }
    }
    
    public static void display_alll(List<Map<TableArticle, Integer>> list) {
        for(Map<?,?> m : list){
            Entry<?,?>  t = m.entrySet().iterator().next();
            System.out.println( t.getKey().toString() + "\nx " + t.getValue().toString() );
        }
    }
    
    public static void display_one(Object o) {
        System.out.println(o);
    }
    
}
