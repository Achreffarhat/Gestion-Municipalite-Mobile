/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.util.Resources;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.NewsfeedForm;
import com.mycompany.gui.WalkthruForm;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.services.ServiceReclamation;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hamza
 */
public class SearchReclamation extends BaseForm {
 Form current;
    public SearchReclamation(Resources res) {
    
        setTitle("Liste des Reclamation");

        Container co;
        //search
        Toolbar.setGlobalToolbar(true);
        add(new InfiniteProgress());

        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...
            Display.getInstance().callSerially(() -> {
                removeAll();
                ArrayList <Reclamation> reclamations = new ArrayList();
                ServiceReclamation sa = new ServiceReclamation();
                reclamations = sa.affichageReclamation();
                for (Reclamation f : reclamations) {
                    MultiButton m = new MultiButton();
                    m.setTextLine1("nom : " + f.getNom()
                           );
                   
                    m.setTextLine2("prenom: " + f.getPrenom()+ "description : " + f.getDescription());;

                    m.setTextLine3("email : " + f.getEmail());
                m.setTextLine4("etat : " + f.getEtat());
               
                    add(m);
                }
                revalidate();
            });
        });
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                getContentPane().animateLayout(150);
            }
        }, 4);
        //Tool Bar
        getToolbar().addCommandToSideMenu("Newsfeed", null, e -> new NewsfeedForm(res).show());
        getToolbar().addCommandToSideMenu("Bagage", null, e -> new AllReclamation(res).show());
        getToolbar().addCommandToSideMenu("Logout", null, e -> new WalkthruForm(res).show());

    }
}


