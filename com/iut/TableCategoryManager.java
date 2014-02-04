package com.iut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class TableCategoryManager extends TableManager<TableCategory> {
        
    TableCategoryManager(){
        this.rsmp = new ResultSetMapper<TableCategory>(TableCategory.class);
        this.psmp = new PreparedStatementMapper<TableCategory>(TableCategory.class);
    }
    
    public List<TableCategory> readAll(Connection conn, TableCategory bean){        
        String sql = "select id_cat, name_cat from category ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        ResultSet rset = queryWrapper(pstm);
        
        return rsetMapperWrapper(rset);
    }

    @Override
    public int create(Connection conn, TableCategory bean) {
        String sql = "insert into category values( null, ? ) ;";
                
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "name_cat");
        
        return updateWrapper(pstm);
    }

    @Override
    public TableCategory read(Connection conn, TableCategory bean) {
        String sql = "select id_cat, name_cat from category where id_cat = ? ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_cat");
        ResultSet rset = queryWrapper(pstm);
        
        return rsetMapperWrapper(rset).get(0);
    }

    @Override
    public int update(Connection conn, TableCategory bean) {
        String sql = "update category set name_cat = ? where id_cat = ?";
                
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "name_cat", "id_cat");
        
        return updateWrapper(pstm);
    }

    @Override
    public int delete(Connection conn, TableCategory bean) {
        String sql = "delete from category where id_cat = ?";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_cat");
        
        return updateWrapper(pstm);
    }
    
}
