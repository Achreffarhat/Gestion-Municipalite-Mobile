
package com.mycompany.myapp.entities;

import java.util.Date;
import java.util.Date;


public class Reclamation {
    private int id;
  
 
    private String nom;
    private String prenom;
    private String email;
    private String description;


   
   
    private String etat;
  

    public Reclamation() {
    }

    public Reclamation(int id,  String nom, String prenom, String email, String description,  String etat ) {
        this.id = id;
  
       
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.description = description;
        this.etat = etat;
      

    }

   
    public Reclamation( String nom, String prenom, String email, String description,  String etat) {
       
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.description = description;
        this.etat = etat;
        
    }

   


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }



    
   
}

