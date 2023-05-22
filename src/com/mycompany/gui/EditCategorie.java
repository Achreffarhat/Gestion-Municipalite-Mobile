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
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.services.ServiceCategorie;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author hamza
 */
public class EditCategorie extends BaseForm{
     String Imagecode;
    String filePath = "";
    public EditCategorie(Resources res, Form previous, Categorie categorie ) {
        super("Modifier categorie", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
//        setTitle("Modifier categorie");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

      
       
    
        
        TextComponent labelcat = new TextComponent().labelAndHint("labelcat");
        labelcat.text(categorie.getlabelcat());
        add(labelcat);
       
       

        
        Button Modifier = new Button("Modifier");
        Button Screen = new Button("Screen");

        Modifier.addActionListener((evt) -> {
            if (labelcat.getText().equals("")) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {

                

                ServiceCategorie sf = new ServiceCategorie();
            
                categorie.setlabetcat(labelcat.getText());
               
                sf.EditCategorie(categorie);
                Dialog.show("Success", "Categorie modifiee avec success", new Command("OK"));
                new AllCategorie(res).show();

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
