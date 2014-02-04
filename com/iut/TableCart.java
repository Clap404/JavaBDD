package com.iut;

import java.sql.Timestamp;

public class TableCart extends Table {
	
	private int id_cart;
	private int id_user;
	private boolean checkout;
	private Timestamp date_checkout;
	
    
    @Column("id_cart")
    public int getId_cart() {
        return id_cart;
    }
    
    @Column("id_cart")
    public void setId_cart(int id_cart) {
        this.id_cart = id_cart;
    }
    
    @Column("id_user")
    public int getId_user() {
        return id_user;
    }
    
    @Column("id_user")
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    @Column("date_checkout")
    public Timestamp getDate_checkout() {
        return date_checkout;
    }
    
    @Column("date_checkout")
    public void setDate_checkout(Timestamp date_checkout) {
        this.date_checkout = date_checkout;
    }
    
    @Column("checkout")
	public boolean isCheckout() {
		return checkout;
	}
    
    @Column("checkout")
	public void setCheckout(boolean checkout) {
		this.checkout = checkout;
	}
	
}