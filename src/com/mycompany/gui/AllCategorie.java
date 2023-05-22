/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.services.ServiceCategorie;
import java.util.ArrayList;
import static java.util.concurrent.ThreadLocalRandom.current;

import com.codename1.ui.util.Resources;

/**
 *
 * @author hamza
 */
public class AllCategorie extends BaseForm {

    Form current;
    ImageViewer imgv;

    public AllCategorie(Resources res) {
        super("categorie", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("categorie");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "  ", "", " ");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0x0000FF);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0x0000FF);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

//        Button Rech = new Button("Rechercher");
//        Rech.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                   new SearchReclamation(res).show();
//            }
//        });
//        add(Rech);
        Button Ajouter = new Button("Ajouter");
        Ajouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new AddCategorie (res, current).show();
            }
        });
        add(Ajouter);
        ButtonGroup barGroup = new ButtonGroup();
        Container co = new Container(BoxLayout.xCenter());;
        ArrayList<Categorie> Categories = new ArrayList();
        ServiceCategorie re = new ServiceCategorie();
        Categories = re.affichageCategorie();

        for (Categorie fi : Categories) {
            Container ct = new Container(BoxLayout.y());
            Label l1 = new Label("id: " + fi.getId(), "SmallLabel");

            Label l2 = new Label("labelcat: " + fi.getlabelcat(), "SmallLabel");

            l1.getAllStyles().setFgColor(0x0000FF);
            ct.add(l1);
            ct.add(l2);

            Button Modif = new Button("Modifier");

            Modif.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    new EditCategorie(res, current, fi).show();
                }

            });
            Button Supprimer = new Button("Supprimer");
            Supprimer.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (Dialog.show("Confirmation", "Voulez vous supprimer cette Categorie ?", "Oui", "Annuler")) {

                        if (ServiceCategorie.getInstance().deleteCategorie(fi)) {
                            Dialog.show("Success", "supprimer", new Command("OK"));
                            new AllCategorie(res).show();
                        }

                    }

                }
            });

            ct.add(Modif);
            ct.add(Supprimer);
////                      
////                        Button pdf = new Button("PDF");
//
//                       pdf.addActionListener(new ActionListener() {
//                               @Override
//                               public void actionPerformed(ActionEvent evt) {
//                                         
//                                            if (Dialog.show("Confirmation", "Voulez vous imprimer cette produit en pdf ?", "Oui", "Annuler")) {
//                                                
//                                                               
//                                                                    
//                                                                     
//                                                                        Dialog.show("Success","imprimer",new Command("OK"));
//                                                                 //       new PDF(res).show();
//                                                           // new AllReclamation(res).show();
//                                                                     
//                                                                  
//
//                                           }
//                                }                           });
//                         ct.add(pdf);
//                     
//                      Label separator = new Label("","Separator");
//                      ct.add(separator);
            add(ct);

        }
    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0x0000FF);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);

    }

    private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0x0000FF);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));

    }
}
