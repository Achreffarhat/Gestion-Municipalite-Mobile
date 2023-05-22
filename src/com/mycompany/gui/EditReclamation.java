/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
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
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.services.ServiceReclamation;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author hamza
 */
public class EditReclamation   extends BaseForm{
     String Imagecode;
    String filePath = "";
    
      public EditReclamation(Resources res, Form previous, Reclamation reclamation) {
        super("Modifier reclamation", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier reclamation");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

      
       
    
        
        TextComponent nom = new TextComponent().labelAndHint("nom");
        nom.text(reclamation.getNom());
        add(nom);
       
        TextComponent prenom = new TextComponent().labelAndHint("prenom");
        prenom.text(reclamation.getPrenom());
        add(prenom);
       
        TextComponent email = new TextComponent().labelAndHint("email");
        email.text(reclamation.getEmail());
        add(email);
       
        TextComponent etat = new TextComponent().labelAndHint("etat");
        etat.text(reclamation.getEtat());
        add(etat);
     
        TextComponent description = new TextComponent().labelAndHint("description");
        description.text(reclamation.getDescription());
        add(description);

        
        Button Modifier = new Button("Modifier");
        Button Screen = new Button("Screen");

        Modifier.addActionListener((evt) -> {
            if (nom.getText().equals("") || (prenom.getText().equals("")) || (email.getText().equals("")) || (etat.getText().equals("")) || (description.getText().equals(""))) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {

                

                ServiceReclamation sf = new ServiceReclamation();
            
                reclamation.setNom(nom.getText());
                reclamation.setPrenom(prenom.getText());
                reclamation.setEmail(email.getText());
                reclamation.setEtat(etat.getText());
                 reclamation.setDescription(description.getText());
                  reclamation.setId(reclamation.getId());
                sf.EditReclamation(reclamation);
                Dialog.show("Success", "Reclamation modifiee avec success", new Command("OK"));
                new AllReclamation(res).show();

            }
        });

        addStringValue("", FlowLayout.encloseRightMiddle(Modifier));

        addStringValue("", FlowLayout.encloseRightMiddle(Screen));

        Screen.addActionListener(e -> {
            Form form = Display.getInstance().getCurrent();
            if (form != null) {

                Image screenshot = Image.createImage(form.getWidth(), form.getHeight());
                form.revalidate();
                form.setVisible(true);
                Screen.setVisible(false);

                form.paintComponent(screenshot.getGraphics(), true);

                String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
                try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                } catch (IOException err) {
                    Log.e(err);
                }
            }

        });
       

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    
    
}
