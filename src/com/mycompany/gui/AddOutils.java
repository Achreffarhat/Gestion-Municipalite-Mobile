/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
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
import com.mycompany.myapp.entities.Outils;
import com.mycompany.myapp.entities.services.ServiceOutils;


/**
 *
 * @author Ala
 */
public class AddOutils extends BaseForm{
      String Imagecode;
    String filePath = "";

    public AddOutils(Resources res, Form current) {
       super("AddOutils", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("AddOutils");
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

       
        TextComponent label_outils = new TextComponent().label("label_outils");
        add(label_outils);
        
        TextComponent quantite = new TextComponent().label("quantite");
        add(quantite);
        
        TextComponent image = new TextComponent().label("image");
        add(image);

       

        Button Ajouter = new Button("Ajouter");
        Ajouter.addActionListener((evt) -> {
            if (label_outils.getText().equals("") || (image.getText().equals("")) || (quantite.getText().equals("")) ) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {

                ServiceOutils sp = new ServiceOutils();
               Outils fi = new Outils();
                fi.setLabel_outils(label_outils.getText());
                fi.setQuantite(quantite.getText());
                 fi.setImage(image.getText());
               

                sp.ajoutOutils(fi);
                Dialog.show("Success", "Outils ajoute avec success", new Command("OK"));
                new AllOutils(res).show();

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
