package com.iut;

import java.math.BigDecimal;

public class TableArticle extends Table {
    
    private int id_cat;
    private int id_article;
    private BigDecimal price_article;
    private String name_article;
    private int stock_article;
    private String description_article;
    
    @Column("id_cat")
    public int getId_cat() {
        return id_cat;
    }
    
    @Column("id_cat")
    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }
    
    @Column("id_article")
    public int getId_article() {
        return id_article;
    }
    
    @Column("id_article")
    public void setId_article(int id_article) {
        this.id_article = id_article;
    }
    
    @Column("price_article")
    public BigDecimal getPrice_article() {
        return price_article;
    }
    
    @Column("price_article")
    public void setPrice_article(BigDecimal price_article) {
        this.price_article = price_article;
    }
    
    @Column("name_article")
    public String getName_article() {
        return name_article;
    }
    
    @Column("name_article")
    public void setName_article(String name_article) {
        this.name_article = name_article;
    }
    
    @Column("stock_article")
    public int getStock_article() {
        return stock_article;
    }
    
    @Column("stock_article")
    public void setStock_article(int stock_article) {
        this.stock_article = stock_article;
    }
    
    @Column("description_article")
    public String getDescription_article() {
        return description_article;
    }
    
    @Column("description_article")
    public void setDescription_article(String description_article) {
        this.description_article = description_article;
    }
    
}