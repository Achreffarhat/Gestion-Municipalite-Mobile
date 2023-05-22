/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

public class User {
     private int id;
    private String email;
    private  String password;
    private String roles ;
    private  String nomUtil;
    private  String prenomUtil;
    private  int tel;
    private  String adresse;

    public User(int id, String email, String password, String roles, String nomUtil, String prenomUtil){
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.nomUtil = nomUtil;
        this.prenomUtil = prenomUtil;
    }

    public User(int id, String email, String nomUtil, String prenomUtil, int tel, String adresse) {
        this.id = id;
        this.email = email;
        this.nomUtil = nomUtil;
        this.prenomUtil = prenomUtil;
        this.tel = tel;
        this.adresse = adresse;
    }

    public User(String email, String nomUtil, String prenomUtil, int tel, String adresse) {
        this.email = email;
        this.nomUtil = nomUtil;
        this.prenomUtil = prenomUtil;
        this.tel = tel;
        this.adresse = adresse;
    }
    
    
    
    
    
    public User(int id, String email, String password, String roles, String nomUtil, String prenomUtil, int tel, String adresse) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.nomUtil = nomUtil;
        this.prenomUtil = prenomUtil;
        this.tel = tel;
        this.adresse = adresse;
    }

    public User(String email, String password, String roles, String nomUtil, String prenomUtil, int tel, String adresse) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.nomUtil = nomUtil;
        this.prenomUtil = prenomUtil;
        this.tel = tel;
        this.adresse = adresse;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    public String getPrenomUtil() {
        return prenomUtil;
    }

    public void setPrenomUtil(String prenomUtil) {
        this.prenomUtil = prenomUtil;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", roles=" + roles + ", nomUtil=" + nomUtil + ", prenomUtil=" + prenomUtil + ", tel=" + tel + ", adresse=" + adresse + '}'+"\n";
    }
    
    
}