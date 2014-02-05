package com.iut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map.Entry;

public class TableCartManager extends TableManager<TableCart> {
        
    TableCartManager(){
        this.rsmp = new ResultSetMapper<TableCart>(TableCart.class);
        this.psmp = new PreparedStatementMapper<TableCart>(TableCart.class);
    }
    
    public List<TableCart> readAll(Connection conn, TableCart bean){        
        String sql = "select * from cart ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        ResultSet rset = queryWrapper(pstm);
        
        List<TableCart> result = rsetMapperWrapper(rset);
        
        TableContainsManager tcm = new TableContainsManager(); 
        
        for(TableCart t : result){
        	
        	List<TableContains> contains = tcm.readAll(conn, t.getId_cart() );
        	
        	for(TableContains tcns : contains){
        		t.addContent(tcns.getId_article(), tcns.getQty());
        	}
        }
        
        return result;
    }

    @Override
    public int create(Connection conn, TableCart bean) {
        String sql = "insert into cart values( null, ?, false , null) ;";
                
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_user");
        
//        HashMap<Integer,Integer> cartContent = bean.getContent();
//        Iterator<Entry<Integer, Integer>> i = cartContent.entrySet().iterator();
        
        int result = updateWrapper(pstm);
        
        TableContainsManager tcm = new TableContainsManager(); 
        TableContains tc = new TableContains(); 
        tc.setId_cart(bean.getId_cart());
        
        for(Entry<Integer,Integer> entry : bean.getContent().entrySet()){
        	tc.setId_article(entry.getKey());
        	tc.setQty(entry.getValue());
        	tcm.create(conn, tc);
        }
        
        return result;
    }

    @Override
    public TableCart read(Connection conn, TableCart bean) {
        String sql = "select * from cart where id_user = ? ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_user");
        ResultSet rset = queryWrapper(pstm);
        
        TableCart t = rsetMapperWrapper(rset).get(0);
        
        TableContainsManager tcm = new TableContainsManager(); 
        
    	List<TableContains> contains = tcm.readAll(conn, t.getId_cart() );
    	
    	for(TableContains tcns : contains){
    		t.addContent(tcns.getId_article(), tcns.getQty());
    	}
        
        return t;
    }
    
    @Override
    public int update(Connection conn, TableCart bean) {
        String sql = "update cart set id_user = ?, date_checkout = ?, checkout = ? where id_cart = ?";
                
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_user", "date_checkout", "checkout", "id_cart");
        
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
