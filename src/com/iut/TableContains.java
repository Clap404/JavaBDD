package com.iut;

public class TableContains extends Table {
	private int id_cart;
	private int id_article;
	private int qty;
	
    
    @Column("id_cart")
    public int getId_cart() {
        return id_cart;
    }
    
    @Column("id_cart")
    public void setId_cart(int id_cart) {
        this.id_cart = id_cart;
    }
    
    @Column("id_article")
    public int getId_article() {
        return id_article;
    }
    
    @Column("id_article")
    public void setId_article(int id_article) {
        this.id_article = id_article;
    }
    
    @Column("qty_contains")
    public int getQty() {
        return qty;
    }
    
    @Column("qty_contains")
    public void setQty(int qty) {
        this.qty = qty;
    }
}