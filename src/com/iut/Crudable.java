package com.iut;

import java.sql.Connection;
import java.util.List;

public interface Crudable<T> {
	int drop(Connection conn);
	int generate(Connection conn);
	int create(Connection conn, T bean );
	T read(Connection conn, T bean );
	List<T> readAll(Connection conn, T bean );
	int update(Connection conn, T bean);
	int delete(Connection conn, T bean);
}
