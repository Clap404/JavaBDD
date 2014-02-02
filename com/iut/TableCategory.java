package com.iut;

public class TableCategory extends Table {
    
    private int id_cat;
    private String name_cat;
    
    @Column("id_cat")
    public int getId_cat() {
        return id_cat;
    }
    
    @Column("id_cat")
    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }
    
    @Column("name_cat")
    public String getName_cat() {
        return name_cat;
    }
    
    @Column("name_cat")
    public void setName_cat(String name_cat) {
        this.name_cat = name_cat;
    }
    
}