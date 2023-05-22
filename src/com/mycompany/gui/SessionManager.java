/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;

public class SessionManager {
    
    private static SessionManager instance= null;

   public static int getId() {
       return Preferences.get("id", -1);
    }
   
   public User getOneUser(int id ){
       return null;
   }
   
   protected SessionManager(){
       
   }

    public static void setId(int id) {
      Preferences.set("id",id);
    }
    
    public static SessionManager getInstance(){
        if(instance ==null){
            instance= new SessionManager();
        }
        return instance;
    }
    
    public static void removeId(){
        Preferences.clearAll();
        
    }
    
   
    
}
