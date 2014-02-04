package com.iut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class TableContainsManager extends TableManager<TableContains> {
        
    TableContainsManager(){
        this.rsmp = new ResultSetMapper<TableContains>(TableContains.class);
        this.psmp = new PreparedStatementMapper<TableContains>(TableContains.class);
    }
    
    public List<TableContains> readAll(Connection conn, TableContains bean){        
        String sql = "select id_cart, id_article, qty_contains from cart_contains_article ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        ResultSet rset = queryWrapper(pstm);
        
        return rsetMapperWrapper(rset);
    }

    @Override
    public int create(Connection conn, TableContains bean) {
        String sql = "insert into cart_contains_article values( ?, ?, ? ) ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_cart", "id_article", "qty_contains");
        
        return updateWrapper(pstm);
    }

    @Override
    public TableContains read(Connection conn, TableContains bean) {
        String sql = "select id_cart, id_article, qty_contains from cart_contains_article where id_cart = ? ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_cart");
        ResultSet rset = queryWrapper(pstm);
        
        return rsetMapperWrapper(rset).get(0);
    }

    @Override
    public int update(Connection conn, TableContains bean) {
        String sql = "update cart_contains_article set qty_contains = ? where id_cart = ? and id_article = ? ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "qty_contains", "id_cart", "id_article");
        
        return updateWrapper(pstm);
    }

    @Override
    public int delete(Connection conn, TableContains bean) {
        String sql = "delete from cart_contains_article where id_cart = ? and id_article = ? ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_cart", "id_article");
        
        return updateWrapper(pstm);
    }
    
}
