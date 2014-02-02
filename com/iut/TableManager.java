package com.iut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class TableManager<T> implements Crudable <T> {
	
	protected ResultSetMapper<T> rsmp ;
	protected PreparedStatementMapper<T> psmp ; 
	
	protected List<T> rsetMapperWrapper(ResultSet rset){
		List<T> result = null;
		try {
			result = rsmp.mapRersultSetToObject(rset);
		} catch (SQLException | NoSuchColumnLabel e) {
			e.printStackTrace();
		}
		return result;
	}
	
	protected void pstmMapperWrapper(PreparedStatement pstm, T bean, String... labels){
		try{
			psmp.mapObjectToPSTM(pstm, bean, labels);
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
	
	protected void closeWrapper(AutoCloseable... acs){
		for (AutoCloseable ac : acs ){
			try { ac.close(); }
	        catch (Exception e) {
	        	e.printStackTrace();
	        }
		}
	}
	
	protected PreparedStatement preparedStatementWrapper(Connection conn, String sql){
		PreparedStatement pstm = null ;
		try {
			pstm = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstm;
	}
	
	protected ResultSet queryWrapper(PreparedStatement pstm){
		ResultSet rset = null ;
		try {
			rset = pstm.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rset;
	}
	
	protected int updateWrapper(PreparedStatement pstm){
		int result = 0 ;
		try {
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
