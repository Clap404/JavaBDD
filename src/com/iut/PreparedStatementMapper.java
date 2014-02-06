package com.iut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

public class PreparedStatementMapper<T> {
	
	private Method[] methods;
//	private Class<T> clazz;
	private HashMap<String,Method> getters;
	
	public PreparedStatementMapper(Class<T> clazz){
//		this.clazz = clazz;
		
		methods = clazz.getMethods();
		
		//hashmap to store the setters
		getters = new HashMap<String,Method>();
		
		for( int i=methods.length -1; i>= 0; i--){
			Column col = methods[i].getAnnotation(Column.class);
			Class<?>[] parameters = methods[i].getParameterTypes();
			//if it has 0 parameter and a "Column" annotation, it's a getter
			if (parameters.length == 0 && col != null) {
				getters.put( col.value(), methods[i] );
			}
		}
	}
	
	public void mapObjectToPSTM(PreparedStatement pstm, T bean, String... labels) throws SQLException, NoSuchColumnLabel {
		
		for( int i = labels.length; i>0; i-- ){ //fields are identified from 1 for preparedStatement.setXX()
			String s = labels[i-1];
			try {
				mapObject(pstm, i, getters.get(s).invoke(bean));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				throw new NoSuchColumnLabel(s);
			}
		}
	}
	
	private static void mapObject(PreparedStatement pstm, int index ,Object arg) throws SQLException {
		Class<?> argClass = arg.getClass();
		if (argClass == String.class) 
            pstm.setString(index, (String) arg);
        else if (argClass == Integer.class)
            pstm.setInt(index, (Integer) arg);
        else if (argClass == BigDecimal.class)
            pstm.setBigDecimal(index, (BigDecimal) arg);
        else if (argClass == Date.class) 
            pstm.setTimestamp(index, new Timestamp(((Date) arg).getTime()));
        else if (argClass == Long.class)
            pstm.setLong(index, (Long) arg);
        else if (argClass == Double.class)
            pstm.setDouble(index, (Double) arg);
        else if (argClass == Float.class)
            pstm.setFloat(index, (Float) arg);

	}
}