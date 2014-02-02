package com.iut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class TableUserManager extends TableManager<TableUser> {
	
//	protected ResultSetMapper<TableUser> rsmp ; 
	
	TableUserManager(){
		this.rsmp = new ResultSetMapper<TableUser>(TableUser.class);
		this.psmp = new PreparedStatementMapper<TableUser>(TableUser.class);
	}
	
	public List<TableUser> readAll(Connection conn, TableUser bean){		
		String sql = "select id_user, name_user, password from user ;";
		
		PreparedStatement pstm = preparedStatementWrapper(conn, sql);
		ResultSet rset = queryWrapper(pstm);
		
		return rsetMapperWrapper(rset);
	}

	@Override
	public int create(Connection conn, TableUser bean) {
		String sql = "insert into user values( null, ?, ? ) ;";
		
		bean.setPassword( Hasher.hashPseudoPassword( bean.getName_user() , bean.getPassword() ) );
		
		PreparedStatement pstm = preparedStatementWrapper(conn, sql);
		pstmMapperWrapper(pstm, bean, "name_user", "password");
		
		return updateWrapper(pstm);
	}

	@Override
	public TableUser read(Connection conn, TableUser bean) {
		String sql = "select id_user, name_user, password from user where id_user = ? ;";
		
		PreparedStatement pstm = preparedStatementWrapper(conn, sql);
		pstmMapperWrapper(pstm, bean, "id_user");
		ResultSet rset = queryWrapper(pstm);
		
		return rsetMapperWrapper(rset).get(0);
	}

	@Override
	public int update(Connection conn, TableUser bean) {
		String sql = "update user set name_user = ?, password = ? where id_user = ?";
		
		bean.setPassword( Hasher.hashPseudoPassword( bean.getName_user() , bean.getPassword() ) );
		
		PreparedStatement pstm = preparedStatementWrapper(conn, sql);
		pstmMapperWrapper(pstm, bean, "name_user", "password", "id_user");
		
		return updateWrapper(pstm);
	}

	@Override
	public int delete(Connection conn, TableUser bean) {
		String sql = "delete from user where id_user = ?";
		
		PreparedStatement pstm = preparedStatementWrapper(conn, sql);
		pstmMapperWrapper(pstm, bean, "id_user");
		
		return updateWrapper(pstm);
	}
	
}
