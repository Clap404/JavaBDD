package com.iut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultSetMapper<T> {
	
	private Method[] methods;
	private Class<T> clazz;
	private HashMap<String,Method> setters;
	
	public ResultSetMapper(Class<T> clazz){
		this.clazz = clazz;
		methods = clazz.getMethods();
		
		//hashmap to store the setters
		setters = new HashMap<String,Method>();
		for( int i=methods.length -1; i>= 0; i--){
			Column col = methods[i].getAnnotation(Column.class);
			Class<?>[] parameters = methods[i].getParameterTypes();
			//if it returns void, has 1 parameter and has a "Column" annotation, it's a setter
			if (methods[i].getReturnType() == void.class && parameters.length == 1 && col != null) {
				setters.put( col.value(), methods[i] );
			}
		}
	}
	
	public List<T> mapRersultSetToObject(ResultSet rset) throws SQLException, NoSuchColumnLabel {
		
		List<T> output = new ArrayList<T>();
		
		ResultSetMetaData rsmd = rset.getMetaData();
//		List<String> columns = new ArrayList<String>();
		
//		for( int i=rsmd.getColumnCount(); i >= 1; i-- ){
//			columns.add( rsmd.getColumnLabel(i) );
//		}
		
		try {
		
			while(rset.next()){
				T bean;
				bean = clazz.newInstance();
//					
//				Iterator<String> it = columns.iterator();
//				while(it.hasNext()){
//					String colLabel = it.next();
//					Method setter = setters.get(colLabel);
//					Object o = rset.getObject( colLabel );
//					System.out.println(colLabel + o.getClass());
//					
//					setter.invoke(bean, o );
//					
//				}
				
				for( int i=rsmd.getColumnCount(); i >= 1; i-- ){
					String colLabel = rsmd.getColumnLabel(i);
					Method setter = setters.get(colLabel);
					Object o = getObjectAs(rset, rsmd, i);
					System.out.println(colLabel + o.getClass() + o.toString());
					try {
						setter.invoke(bean, o );
					} catch (NullPointerException e) {
						throw new NoSuchColumnLabel(colLabel);
					}
				}
			
				output.add(bean);
			}
		
			
		} catch (IllegalArgumentException | InvocationTargetException
				| InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
			
		return output;
	}
	
	private Object getObjectAs(ResultSet rset, ResultSetMetaData rsmd, int colIndex) throws SQLException {
		int colType = rsmd.getColumnType(colIndex);
		
		if(colType == Types.INTEGER)
			return rset.getInt(colIndex);
		else if(colType == Types.FLOAT || colType == Types.DECIMAL || colType == Types.DOUBLE || colType == Types.REAL)
			return rset.getBigDecimal(colIndex);
		
		return rset.getObject(colIndex);
	}
}