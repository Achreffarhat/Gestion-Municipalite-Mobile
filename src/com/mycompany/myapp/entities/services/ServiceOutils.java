/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Outils;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Ala
 */
public class ServiceOutils {

    public static ServiceOutils instance = null;
    private ConnectionRequest req;

    public static ServiceOutils getInstance() {
        if (instance == null) {
            instance = new ServiceOutils();
        }
        return instance;
    }

    public ServiceOutils() {
        req = new ConnectionRequest();
    }

    public void ajoutOutils(Outils o) {
        String url = Statics.BASE_URL + "/Addoutils?label_outils=" + o.getLabel_outils()
                + "&quantite=" + o.getQuantite()
                + "&image=" + o.getImage();
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            //reponse json hedhi elli rynaha fil naviguateur 

            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution mtaie request sinon yitada chay dima nalkawha 

    }
    



  public void EditOutils( Outils o) {
    String url = Statics.BASE_URL + "/editeOutils/" + o.getId() + "?label_outils="  + o.getLabel_outils()
                + "&quantite=" + o.getQuantite()
                + "&image=" + o.getImage();
    System.out.println(url);
    req.setUrl(url);
    req.addResponseListener((e) -> {

        String str = new String(req.getResponseData());
        //reponse json hedhi elli rynaha fil naviguateur 

        System.out.println("data == " + str);
    });

    NetworkManager.getInstance().addToQueueAndWait(req);//execution mtaie request sinon yitada chay dima nalkawha 
}


    //affichage 
    public ArrayList<Outils> affichageOutils() {
        ArrayList<Outils> result = new ArrayList<>();
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/allOutils";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapOutils = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapOutils.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Outils re = new Outils();
                      float id = Float.parseFloat(obj.get("id").toString());
                        String label_outils = obj.get("label_outils").toString();
                        String quantite = obj.get("quantite").toString();
                        String image = obj.get("image").toString();

                         re.setId((int) id);
                        re.setLabel_outils(label_outils);
                        re.setQuantite(quantite);
                        re.setImage(image);
                    
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

    public boolean resultOK;

    public boolean deleteOutils(Outils fi ) {
        String url = Statics.BASE_URL + "/delete/" + fi.getId();
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }



   

}
