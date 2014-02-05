package com.iut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class TableArticleManager extends TableManager<TableArticle> {
    
    TableArticleManager(){
        this.rsmp = new ResultSetMapper<TableArticle>(TableArticle.class);
        this.psmp = new PreparedStatementMapper<TableArticle>(TableArticle.class);
    }
    
    public List<TableArticle> readAll(Connection conn, TableArticle bean){        
        String sql = "select * from article order by price_article ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        ResultSet rset = queryWrapper(pstm);
        
        return rsetMapperWrapper(rset);
    }

    @Override
    public int create(Connection conn, TableArticle bean) {
        String sql = "insert into article values( null, ?, ?, ?, ?, ? ) ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_cat", "price_article", "name_article", "stock_article", "description_article");
        
        return updateWrapper(pstm);
    }

    @Override
    public TableArticle read(Connection conn, TableArticle bean) {
        String sql = "select * from article where id_article = ? ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_article");
        ResultSet rset = queryWrapper(pstm);
        
        return rsetMapperWrapper(rset).get(0);
    }

    @Override
    public int update(Connection conn, TableArticle bean) {
        String sql = "update article set name_article = ? where id_article = ? ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "name_article", "id_article");
        
        return updateWrapper(pstm);
    }

    @Override
    public int delete(Connection conn, TableArticle bean) {
        String sql = "delete from article where id_article = ? ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_article");
        
        return updateWrapper(pstm);
    }
    
}
