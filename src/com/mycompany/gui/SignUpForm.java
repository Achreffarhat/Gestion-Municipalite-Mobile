

package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.services.ServiceUser;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
       
        TextField email = new TextField("", "email", 20, TextField.EMAILADDR);
     
        TextField password = new TextField("", "mot de passe", 20, TextField.PASSWORD);
            TextField nom = new TextField("", "nom", 20, TextField.ANY);
            TextField prenom = new TextField("", "prenom", 20, TextField.ANY);
            TextField  tel= new TextField("", "tel", 20, TextField.ANY);
            TextField  adresse= new TextField("", "adresse", 20, TextField.ANY);
        
        email.setSingleLineTextArea(false);
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        tel.setSingleLineTextArea(false);
        adresse.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button next = new Button("Signup now");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
               
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(tel),
                createLineSeparator(),
                new FloatingHint(adresse),
                createLineSeparator()
       );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next
         ));
        next.requestFocus();
        next.addActionListener(e -> {
                 ServiceUser ss = new ServiceUser();
         
                 ss.signup(email.getText(),password.getText(),nom.getText(),prenom.getText(),Integer.valueOf(tel.getText()),adresse.getText());
                     new SignInForm(res).show();
          
        });
    }
    
}
