/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.services.ServiceReclamation;


/**
 *
 * @author hamza
 */
public class AddReclamation  extends BaseForm{
      String Imagecode;
    String filePath = "";

    AddReclamation(Resources res, Form current) {
       super("AddReclamation", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("AddReclamation");
        getContentPane().setScrollVisible(false);


        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

       
        TextComponent nom = new TextComponent().label("nom");
        add(nom);
        
        TextComponent prenom = new TextComponent().label("prenom");
        add(prenom);
        
        TextComponent email = new TextComponent().label("email");
        add(email);

       
        TextComponent etat = new TextComponent().label("etat");
        add(etat);

         TextComponent description = new TextComponent().label("description");
        add(description);

        Button Ajouter = new Button("Ajouter");
        Ajouter.addActionListener((evt) -> {
            if (nom.getText().equals("") || (prenom.getText().equals("")) || (email.getText().equals("")) || (etat.getText().equals(""))|| (description.getText().equals(""))) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {

                ServiceReclamation sp = new ServiceReclamation();
               Reclamation fi = new Reclamation();
                fi.setNom(nom.getText());
                fi.setPrenom(prenom.getText());
                 fi.setEmail(email.getText());
                 fi.setEtat(etat.getText());
                fi.setDescription(description.getText());
               

                sp.ajoutReclamation(fi);
                Dialog.show("Success", "Reclamation ajoute avec success", new Command("OK"));
                new AllReclamation(res).show();

            }
        });
        addStringValue("", FlowLayout.encloseRightMiddle(Ajouter));

        

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

      

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
