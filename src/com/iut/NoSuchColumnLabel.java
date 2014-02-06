package com.iut;

public class NoSuchColumnLabel extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoSuchColumnLabel(){
		super();
	}
	
	public NoSuchColumnLabel(String message){
		super(message);
	}
}
