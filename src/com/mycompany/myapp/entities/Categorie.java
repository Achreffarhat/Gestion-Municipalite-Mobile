package com.mycompany.myapp.entities;

public class Categorie {
    private int id;
    private String labelcat;
   

    public Categorie() {
    }

    public Categorie(int id, String name) {
        this.id = id;
        this.labelcat = name;
    }
    
    public Categorie(String name) {
        this.labelcat = name;
        
    }

    public Categorie(int aInt, String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getlabelcat() {
        return labelcat;
    }

    public void setlabetcat(String name) {
        this.labelcat = name;
    }

   

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", name=" + labelcat + '}';
    }

}
