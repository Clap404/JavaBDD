/*     */ package com.iut;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Table1Manager
/*     */ {
/*     */   public int create(Connection conn, Table1 table1)
/*     */   {
/*  12 */     int n = -1;
/*     */ 
/*  14 */     PreparedStatement pstm = null;
/*     */     try {
/*  16 */       String sql = "insert into table1 (c1, c2) values(?,?)";
/*  17 */       pstm = conn.prepareStatement(sql);
/*     */ 
/*  19 */       pstm.setInt(1, table1.getC1());
/*  20 */       pstm.setBigDecimal(2, table1.getC2());
/*     */ 
/*  22 */       n = pstm.executeUpdate();
/*  23 */       if (1 != n)
/*  24 */         System.out.println("erreur insert");
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  28 */       e.printStackTrace();
/*     */       try {
/*  30 */         pstm.close(); } catch (Exception localException1) {  } } finally { try { pstm.close(); } catch (Exception localException2) {
/*     */       } }
/*  32 */     return n;
/*     */   }
/*     */ 
/*     */   public Table1 read(Connection conn, int c1) {
/*  36 */     Table1 table1 = null;
/*     */ 
/*  38 */     PreparedStatement pstm = null;
/*  39 */     ResultSet rset = null;
/*     */     try {
/*  41 */       String sql = "select * from table1 where c1=?";
/*  42 */       pstm = conn.prepareStatement(sql);
/*     */ 
/*  44 */       pstm.setInt(1, c1);
/*     */ 
/*  46 */       rset = pstm.executeQuery();
/*  47 */       if (rset.next()) {
/*  48 */         table1 = new Table1();
/*  49 */         table1.setC1(rset.getInt("c1"));
/*  50 */         table1.setC2(rset.getBigDecimal("c2"));
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  54 */       e.printStackTrace();
/*     */       try {
/*  56 */         rset.close(); } catch (Exception localException1) {
/*     */       }try { pstm.close(); }
/*     */       catch (Exception localException2)
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  56 */         rset.close(); } catch (Exception localException3) {
/*     */       }try { pstm.close(); } catch (Exception localException4) {  }
/*     */     }
/*  59 */     return table1;
/*     */   }
/*     */ 
/*     */   public List<Table1> read_all(Connection conn) {
/*  63 */     List all = new ArrayList();
/*     */ 
/*  65 */     Statement stmt = null;
/*  66 */     ResultSet rset = null;
/*     */     try {
/*  68 */       stmt = conn.createStatement();
/*     */ 
/*  70 */       String sql = "select c1, c2 from table1 order by 1";
/*  71 */       rset = stmt.executeQuery(sql);
/*  72 */       while (rset.next()) {
/*  73 */         Table1 table1 = new Table1();
/*  74 */         table1.setC1(rset.getInt(1));
/*  75 */         table1.setC2(rset.getBigDecimal(2));
/*  76 */         all.add(table1);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  80 */       e.printStackTrace();
/*     */       try {
/*  82 */         rset.close(); } catch (Exception localException1) {
/*     */       }try { stmt.close(); }
/*     */       catch (Exception localException2)
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  82 */         rset.close(); } catch (Exception localException3) {
/*     */       }try { stmt.close(); } catch (Exception localException4) {  }
/*     */     }
/*  85 */     return all;
/*     */   }
/*     */ 
/*     */   public int update(Connection conn, int c1, Table1 table1)
/*     */   {
/*  90 */     int n = -1;
/*     */ 
/*  92 */     PreparedStatement pstm = null;
/*     */     try {
/*  94 */       String sql = "update table1 set c1=?, c2=? where c1=?";
/*  95 */       pstm = conn.prepareStatement(sql);
/*  96 */       int i = 1;
/*     */ 
/*  98 */       pstm.setInt(i++, table1.getC1());
/*  99 */       pstm.setBigDecimal(i++, table1.getC2());
/*     */ 
/* 101 */       pstm.setInt(i++, c1);
/*     */ 
/* 103 */       if (1 != (n = pstm.executeUpdate()))
/* 104 */         System.out.println("erreur update");
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 108 */       e.printStackTrace();
/*     */       try {
/* 110 */         pstm.close(); } catch (Exception localException1) {  } } finally { try { pstm.close(); } catch (Exception localException2) {
/*     */       } }
/* 112 */     return n;
/*     */   }
/*     */ 
/*     */   public int delete(Connection conn, int c1) {
/* 116 */     int n = -1;
/*     */ 
/* 118 */     PreparedStatement pstm = null;
/*     */     try {
/* 120 */       String sql = "delete from table1 where c1=?";
/* 121 */       pstm = conn.prepareStatement(sql);
/*     */ 
/* 123 */       pstm.setInt(1, c1);
/*     */ 
/* 125 */       if (1 != (n = pstm.executeUpdate()))
/* 126 */         System.out.println("erreur delete");
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 130 */       e.printStackTrace();
/*     */       try {
/* 132 */         pstm.close(); } catch (Exception localException1) {  } } finally { try { pstm.close(); } catch (Exception localException2) {
/*     */       } }
/* 134 */     return n;
/*     */   }
/*     */ }

/* Location:           /home/anthony/Téléchargements/lesson2.jar
 * Qualified Name:     com.iut.Table1Manager
 * JD-Core Version:    0.6.2
 */