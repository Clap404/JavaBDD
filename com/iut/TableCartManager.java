package com.iut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class TableCartManager extends TableManager<TableCart> {
        
    TableCartManager(){
        this.rsmp = new ResultSetMapper<TableCart>(TableCart.class);
        this.psmp = new PreparedStatementMapper<TableCart>(TableCart.class);
    }
    
    public List<TableCart> readAll(Connection conn, TableCart bean){        
        String sql = "select * from cart ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        ResultSet rset = queryWrapper(pstm);
        
        return rsetMapperWrapper(rset);
    }

    @Override
    public int create(Connection conn, TableCart bean) {
        String sql = "insert into cart values( null, ?, null ) ;";
                
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_user");
        
        return updateWrapper(pstm);
    }

    @Override
    public TableCart read(Connection conn, TableCart bean) {
        String sql = "select * from cart where id_cart = ? ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_cart");
        ResultSet rset = queryWrapper(pstm);
        
        return rsetMapperWrapper(rset).get(0);
    }

    @Override
    public int update(Connection conn, TableCart bean) {
        String sql = "update cart set id_user = ?, date_checkout = ? where id_cart = ?";
                
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_user", "date_checkout", "id_cart");
        
        return updateWrapper(pstm);
    }

    @Override
    public int delete(Connection conn, TableCart bean) {
        String sql = "delete from cart where id_cart = ?";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_cart");
        
        return updateWrapper(pstm);
    }
    
}
