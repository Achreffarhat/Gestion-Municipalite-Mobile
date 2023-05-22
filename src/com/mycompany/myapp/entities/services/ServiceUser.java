/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.mycompany.myapp.entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.NewsfeedForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.SignUpForm;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


/**
 *
 * @author hamza
 */
public class ServiceUser {
     public static ServiceUser instance = null;
    
    public static boolean resultOk = true;
    String json;
    public static float active;
    public static float verified;

    private ConnectionRequest req;
    public User userone;
    
    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
    
      public ServiceUser() {
        req = new ConnectionRequest();
    }
      
      public void getData() {
        String url = Statics.BASE_URL + "/user";
        req = new ConnectionRequest(url, false);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            json = new String(req.getResponseData()) + "";
            try {
                Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
                active = Float.parseFloat(obj.get("active").toString());
                verified = Float.parseFloat(obj.get("verified").toString());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
              NetworkManager.getInstance().addToQueueAndWait(req);
       }
      
      public float getActive()
    {
        getData();
        return active;
    }
    
    public float getBanned()
    {
        getData();
        return verified-active;
    }
    
    public void signup(String email,String password,String nom,String prenom,int tel,String adresse){
          ConnectionRequest req = new ConnectionRequest();
            String url =  Statics.BASE_URL + "/register?email="+email+"&password="+password+"&nom="+nom+"&prenom="+prenom+"&tel="+tel+"&adresse="+adresse;
            req.setUrl(url);

            req.addResponseListener((e) -> {

                byte[] data = (byte[]) e.getMetaData();
                String responseData = new String(data);
         Dialog.show("Success", "Utilisateur ajouté avec succés ", "OK", null);
     

            });
            NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
     public void signin(String username, String password, Resources rs) {
        String url = Statics.BASE_URL + "/Apilogin?email=" +  username+ "&password=" + password;
        req = new ConnectionRequest(url, false); //Création requete
        req.setUrl(url); //url de la requete 
        req.addResponseListener((e) -> { 
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData()) ;

                 System.out.println(json);
                 System.out.println(   SessionManager.getId());
                       try{
                           Reader reader = new StringReader(json);
                     JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
                  int id = jsonObject.getInt("id");
                  SessionManager.setId(id);
                       new NewsfeedForm(rs).show();
                      }catch(JSONException ex){
                     System.out.println(ex.getMessage());
                     }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
      
        public ArrayList<User> affichageUser() {
        ArrayList<User> result = new ArrayList<>();
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/user";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapUsers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapUsers.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        User re = new User();
                     // float id = Float.parseFloat(obj.get("id").toString());
                        String email = obj.get("email").toString();
                       // String password = obj.get("password").toString();
                       // String roles = obj.get("roles").toString();
                       
                       // int  tel = (int) Float.parseFloat(obj.get("tel").toString());
                        String nom = obj.get("nom").toString();
                        String prenom = obj.get("prenom").toString();
                         String adresse = obj.get("adresse").toString();
                        // re.setId((int) id);
                        re.setNomUtil(nom);
                        re.setPrenomUtil(prenom);
                        re.setEmail(email);
                        //  re.setTel(tel);
                       // re.setPassword(password);
                        re.setAdresse(adresse);
                        result.add(re);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
        
        public User getOnById(int id ){
            User u = new User();
            ConnectionRequest req = new ConnectionRequest();
            String url = Statics.BASE_URL + "/"+id;
            req.setPost(false);
            req.setUrl(url);
           req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
            public void actionPerformed(NetworkEvent evt) {
                userone = parseUser(new String(req.getResponseData()));
            }
           });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return userone;
        }
        
        public User parseUser(String jsonText) {
        User user = null;
        
        JSONParser j = new JSONParser();
                 System.out.println(jsonText);
                 System.out.println(   SessionManager.getId());
                       try{
                           Reader reader = new StringReader(jsonText);
                     JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
                  String email = jsonObject.getString("email");
                  String nom = jsonObject.getString("nom");
                  String prenom = jsonObject.getString("prenom");
                     int tel = jsonObject.getInt("tel");
                   String adresse = jsonObject.getString("adresse");
                     user = new User(email,nom,prenom,tel,adresse);
               
                      }catch(JSONException ex){
                     System.out.println(ex.getMessage());
                     }
        return user;
    }


    
}
