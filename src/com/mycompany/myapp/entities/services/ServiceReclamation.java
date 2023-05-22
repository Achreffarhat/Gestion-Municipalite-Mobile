package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *0
 * @author dell
 */
public class ServiceReclamation {

    public static ServiceReclamation instance = null;
    private ConnectionRequest req;

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public void ajoutReclamation(Reclamation reclamation) {
        String url = Statics.BASE_URL + "/addReclamation?nom=" + reclamation.getNom()
                + "&prenom=" + reclamation.getPrenom()
                + "&email=" + reclamation.getEmail()
               
                + "&description=" + reclamation.getDescription()
                + "&etat=" + reclamation.getEtat();
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            //reponse json hedhi elli rynaha fil naviguateur 

            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution mtaie request sinon yitada chay dima nalkawha 

    }
    



  public void EditReclamation( Reclamation reclamation) {
    String url = Statics.BASE_URL + "/editeReclamation/" + reclamation.getId() + "?nom=" + reclamation.getNom()
            + "&prenom=" + reclamation.getPrenom()
            + "&email=" + reclamation.getEmail()
            + "&etat=" + reclamation.getEtat()
            + "&description=" + reclamation.getDescription();
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
    public ArrayList<Reclamation> affichageReclamation() {
        ArrayList<Reclamation> result = new ArrayList<>();
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/listReclamations";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReclamations.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Reclamation re = new Reclamation();
                      float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        String prenom = obj.get("prenom").toString();
                        String email = obj.get("email").toString();
                       String etat = obj.get("etat").toString();
                        String description = obj.get("description").toString();
                         re.setId((int) id);
                        re.setNom(nom);
                        re.setPrenom(prenom);
                        re.setEmail(email);
                        re.setDescription(description);
                    re.setEtat(etat);
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

    public boolean deleteReclamation(Reclamation fi ) {
        String url = Statics.BASE_URL + "/delReclamation/" + fi.getId();
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
