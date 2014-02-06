package com.iut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableArticleManager extends TableManager<TableArticle> {
    
    TableArticleManager(){
        this.rsmp = new ResultSetMapper<TableArticle>(TableArticle.class);
        this.psmp = new PreparedStatementMapper<TableArticle>(TableArticle.class);
    }
    
    public List<TableArticle> readAll(Connection conn, TableArticle bean){        
        String sql = "select * from article order by name_article ;";
        
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
    
    public TableArticle read(Connection conn, int id_article) {
        TableArticle tc = new TableArticle();
        tc.setId_article(id_article);
        return read(conn, tc);
    }

    @Override
    public int update(Connection conn, TableArticle bean) {
        String sql = "update article set stock_article = ? where id_article = ? ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "stock_article", "id_article");
        
        return updateWrapper(pstm);
    }

    @Override
    public int delete(Connection conn, TableArticle bean) {
        String sql = "delete from article where id_article = ? ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        pstmMapperWrapper(pstm, bean, "id_article");
        
        return updateWrapper(pstm);
    }
    
    public List<Map<TableArticle, Integer>> getBestSellers(Connection conn) {
    	
    	String sql = "select id_article, sum(qty_contains) as qty_sold "
    			+ "from article natural join cart_contains_article "
    			+ "where ( "
    				+ "select date_checkout "
    				+ "from cart where cart.id_cart = cart_contains_article.id_cart "
    			+ ") >= DATE_SUB( NOW(), INTERVAL 7 DAY ) "
    			+ "group by id_article "
    			+ "order by qty_sold desc ;";
        
        PreparedStatement pstm = preparedStatementWrapper(conn, sql);
        ResultSet rset = queryWrapper(pstm);
        
        List<Map<TableArticle, Integer>> result = new ArrayList<Map<TableArticle, Integer>>() ;
        Map<TableArticle, Integer> row;
        
        try {
	        while(rset.next()){
	        	row = new HashMap<TableArticle, Integer>();
	        	row.put(
	        		read(conn, rset.getInt(1) ), 
	        		rset.getInt(2)
		        );
	        	result.add(row);
	        }
        } catch (SQLException e) {
			e.printStackTrace();
		}
        
        return result;
    }

	@Override
	public int generate(Connection conn) {
	    String sql = "create table if not exists article ("
	            + "id_article int not null auto_increment,"
	            + "id_cat int not null,"
	            + "price_article float not null,"
	            + "name_article varchar(128) not null,"
	            + "stock_article int default 0 not null,"
	            + "description_article text,"
	            + "primary key (id_article),"
	            + "foreign key (id_cat) references category(id_cat)"
	        + ");";
	    PreparedStatement pstm = preparedStatementWrapper(conn, sql);
	    return updateWrapper(pstm);
	}
    
	public int drop(Connection conn) {
	    String sql = "drop table if exists article ;";
	    PreparedStatement pstm = preparedStatementWrapper(conn, sql);
	    return updateWrapper(pstm);
	}
}
