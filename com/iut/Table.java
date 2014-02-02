package com.iut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public abstract class Table {
	public String toString(){
			
		Method[] methods = this.getClass().getMethods();
		
		String output = "";
		int nbMethods = methods.length;
		
		for( int i= 0; i< nbMethods; i++){
			Column col = methods[i].getAnnotation(Column.class);
			Class<?>[] parameters = methods[i].getParameterTypes();
			if (parameters.length == 0 && col != null) {
				try {
					output += col.value() + ":" +  methods[i].invoke(this) + ", ";
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return output;
	}
}