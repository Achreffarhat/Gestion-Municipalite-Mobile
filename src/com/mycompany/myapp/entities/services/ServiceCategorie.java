package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dell
 */
public class ServiceCategorie {

    public static ServiceCategorie instance = null;
    private ConnectionRequest req;

    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }

    public ServiceCategorie() {
        req = new ConnectionRequest();
    }

    public void ajoutReclamation(Categorie categorie) {
        String url = Statics.BASE_URL + "/addCategorie?labelcat=" + categorie.getlabelcat();
             
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            //reponse json hedhi elli rynaha fil naviguateur 

            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution mtaie request sinon yitada chay dima nalkawha 

    }
    



  public void EditCategorie( Categorie categorie) {
    String url = Statics.BASE_URL + "/editeCategorie/" + categorie.getId() + "?labelcat=" + categorie.getlabelcat();
          
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
    public ArrayList<Categorie> affichageCategorie() {
        ArrayList<Categorie> result = new ArrayList<>();
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/listCategorie";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapCategorie = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCategorie.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Categorie re = new Categorie();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String labelcat = obj.get("labelcat").toString();
                  
                         re.setId((int) id);
                        re.setlabetcat(labelcat);
                     ;
                        result.add(re);
                        System.out.println(re);
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

    public boolean deleteCategorie(Categorie fi ) {
        String url = Statics.BASE_URL + "/delCategorie/" + fi.getId();
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
