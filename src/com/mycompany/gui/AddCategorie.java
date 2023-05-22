/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
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
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.services.ServiceCategorie;

/**
 *
 * @author hamza
 */
public class AddCategorie extends BaseForm {

    String Imagecode;
    String filePath = "";

    public AddCategorie(Resources res, Form current) {
        super("AddCategorie", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("AddCategorie");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        TextComponent labelcat = new TextComponent().label("labelcat");
        add(labelcat);

        Button Ajouter = new Button("Ajouter");
        Ajouter.addActionListener((evt) -> {
            if (labelcat.getText().equals("")) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {

                ServiceCategorie sp = new ServiceCategorie();
                Categorie fi = new Categorie();
                fi.setlabetcat(labelcat.getText());

                sp.ajoutReclamation(fi);
                Dialog.show("Success", "Categorie ajoute avec success", new Command("OK"));
                new AllCategorie(res).show();

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
