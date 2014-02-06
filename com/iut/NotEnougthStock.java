package com.iut;

public class NotEnougthStock extends Exception {
	
	private static final long serialVersionUID = 1L;

	NotEnougthStock(int id_article, int amount){
		super("Not enougth stock for item " + id_article + " x " + amount);
	}
}
