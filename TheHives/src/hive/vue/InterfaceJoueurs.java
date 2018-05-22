/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.model.players.decisions.Level;
import javafx.geometry.Pos;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import hive.thehives.TheHives;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Adeline
 */
public class InterfaceJoueurs extends Parent{

    String versionIA1;
    String versionIA2;
    int est_ai_ai=0, est_h_ai=0, est_h_h=0;
    TextField Name1 = new TextField();
    TextField Name2 = new TextField();
    public InterfaceJoueurs(Stage primaryStage, Controller controller) {
        
        if(controller.pleinEcran==1){
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        }
        
        CacheImage c = new CacheImage();
        
        String police;
        if(controller.langue == "Russe"){
            police = "Copperplate";
        }
        else{
            police = "Papyrus";
        }
        
        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());
        
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        int tailleDeCase = width/8;
        int maxJoueur = (int) ((int) width/2.5);
        int minJoueur = maxJoueur/2;
        
        Image fond = c.getImage("Design/Fond/fondMontagne.png");
        ImageView fondIm = new ImageView(fond);
        fondIm.fitHeightProperty().bind(primaryStage.heightProperty());
        fondIm.fitWidthProperty().bind(primaryStage.widthProperty());
        AnchorPane.setRightAnchor(fondIm, (double) 0);
        AnchorPane.setLeftAnchor(fondIm, (double) 0);
        AnchorPane.setTopAnchor(fondIm, (double) 0);
        AnchorPane.setBottomAnchor(fondIm, (double) 0);
        pane.getChildren().add(fondIm);
        
        StackPane Preferences = new StackPane();
        Image preferences = c.getImage("Design/MenuPrincipaux/BouttonParametre.png");
        ImageView prefIm = new ImageView(preferences); 
        prefIm.setFitHeight(tailleDeCase/2);
        prefIm.setFitWidth(tailleDeCase/2*1.07);
        Preferences.getChildren().add(prefIm);
        Preferences.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            Preferences p = new Preferences(primaryStage, controller);
            pane.getChildren().add(p);
            StackPane pref = new StackPane();
            Image imageQ = c.getImage("exit3.png");
            ImageView ImQ = new ImageView(imageQ);
            ImQ.setFitHeight(tailleDeCase/2.5);
            ImQ.setFitWidth(tailleDeCase/2.5);
            pref.getChildren().add(ImQ);
            pref.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
                pane.getChildren().remove(pane.getChildren().size()-2, pane.getChildren().size());
                controller.goToChoixJoueur();
            });
            AnchorPane.setRightAnchor(pref, (double) 5);
            AnchorPane.setTopAnchor(pref, (double) 5);
            pane.getChildren().add(pref);
        });
        AnchorPane.setRightAnchor(Preferences, (double) tailleDeCase/2*1.07 + 15);
        AnchorPane.setTopAnchor(Preferences, (double) 5);
        pane.getChildren().add(Preferences);
        
        StackPane Plein = new StackPane();
        Image plein = c.getImage("Design/MenuPrincipaux/pleinEcran.png");
        ImageView pleinIm = new ImageView(plein); 
        pleinIm.setFitHeight(tailleDeCase/2);
        pleinIm.setFitWidth(tailleDeCase/2*1.07);
        Plein.getChildren().add(pleinIm);
        Plein.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.pleinEcran=1;
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");    
        });
        AnchorPane.setRightAnchor(Plein, (double) 10);
        AnchorPane.setTopAnchor(Plein, (double) 5);
        pane.getChildren().add(Plein);
        
        StackPane Menu = new StackPane();
        Image menu = c.getImage("Design/FenetrePlateau/bouttonRetourMenu.png");
        ImageView menuIm = new ImageView(menu); 
        menuIm.setFitHeight(tailleDeCase/2);
        menuIm.setFitWidth(tailleDeCase/2*1.07);
        Menu.getChildren().add(menuIm);
        Menu.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToMenu();
        });
        AnchorPane.setLeftAnchor(Menu, (double) 5);
        AnchorPane.setTopAnchor(Menu, (double) 5);
        pane.getChildren().add(Menu);
        
        GridPane grille = new GridPane();
        int ligne = 100/4;
        int colonne = 100/3;
        Outils.fixerRepartition(grille, Outils.HORIZONTAL, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(grille, Outils.VERTICAL, colonne, colonne, colonne);
//        grille.prefHeightProperty().bind(primaryStage.heightProperty());
//        grille.prefWidthProperty().bind(primaryStage.widthProperty());
        grille.setMaxWidth(width/8);
        grille.setMinWidth(width/12);
        grille.setMaxHeight(height*0.8);
        grille.setMinHeight(height*0.7);
        double hauteurDeGrille = height*0.7;
        double hauteurDeLigne = hauteurDeGrille/6;
        
        
        
        Label joueur1 = new Label(); 
        Label joueur2 = new Label();
        
        Button valider = new Button(); // Invio, Enter Bestätigen
        if(controller.langue=="Français"){
            joueur1.setText("Joueur 1");
            joueur2.setText("Joueur 2");
            Name1.setPromptText("Votre prenom"); // nome , Name
            Name2.setPromptText("Votre prenom");
            valider.setText("Valider");
        }
        else if(controller.langue=="English"){
            joueur1.setText("Player 1");
            joueur2.setText("Player 2");
            Name1.setPromptText("Name");
            Name2.setPromptText("Name");
            valider.setText("Commit");
        }
        else if(controller.langue=="Italiano"){
            joueur1.setText("Jocatore 1");
            joueur2.setText("Jocatore 2");
            Name1.setPromptText("Nome");
            Name2.setPromptText("Nome");
            valider.setText("Invio");
        }
        else if(controller.langue=="Русский"){
            joueur1.setText("Игрок 1");
            joueur2.setText("Игрок 2");
            Name1.setPromptText("Имя");
            Name2.setPromptText("Имя");
            valider.setText("Подтвердить");
        }
        else if(controller.langue=="Deutsch"){
            joueur1.setText("Spiler 1");
            joueur2.setText("Spiler 2");
            Name1.setPromptText("Name");
            Name2.setPromptText("Name");
            valider.setText("Bestätigen");
        }
        
        
        final ToggleGroup j = new ToggleGroup();
        RadioBouton bouton = new RadioBouton(primaryStage, controller);
        ToggleButton humains;
        humains = bouton.creer("humains");
        humains.setBackground(Background.EMPTY);
        humains.setToggleGroup(j);
        StackPane hh = new StackPane();
        hh.getChildren().add(humains);
        grille.add(hh, 0, 1);
        ToggleButton hIA;
        hIA = bouton.creer("h_IA");
        hIA.setBackground(Background.EMPTY);
        
        
       
        hIA.setToggleGroup(j);
        StackPane h_ia = new StackPane();
        h_ia.getChildren().add(hIA);
        grille.add(h_ia, 1, 1);
        ToggleButton IAs;
        IAs = bouton.creer("IAs");
        IAs.setBackground(Background.EMPTY);
        IAs.setToggleGroup(j);
        StackPane ia_ia = new StackPane();
        ia_ia.getChildren().add(IAs);
        grille.add(ia_ia, 2, 1);
        
        Name1.setText(null);
        Name2.setText(null);
        final ToggleGroup ia1 = new ToggleGroup();
        final ToggleGroup ia2 = new ToggleGroup();
        j.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
                if (j.getSelectedToggle() != null) {
                    if(humains.isSelected()){
                        if(est_ai_ai==1){
                            grille.getChildren().remove(grille.getChildren().size()-6, grille.getChildren().size());
                            est_ai_ai=0;
                            versionIA1=null;
                            versionIA2=null;
                        }else if(est_h_ai==1){
                            grille.getChildren().remove(grille.getChildren().size()-4, grille.getChildren().size());
                            est_h_ai=0;
                            versionIA1=null;
                            Name1.setText(null);
                        }
                        est_h_h=1;
                        Name1.setMinSize(width/10, 30);
                        Name1.setMaxHeight(40);
                        Name1.setAlignment(Pos.CENTER);
                        StackPane n1 = new StackPane();
                        n1.getChildren().add(Name1);
                        grille.add(n1, 1, 2);
                        Name2.setMinSize(width/10, 30);
                        Name2.setMaxHeight(40);
                        Name2.setAlignment(Pos.CENTER);
                        StackPane n2 = new StackPane();
                        n2.getChildren().add(Name2);
                        grille.add(n2, 1, 3);
                    }
                    else if(hIA.isSelected()){
                        if(est_ai_ai==1){
                            grille.getChildren().remove(grille.getChildren().size()-6, grille.getChildren().size());
                            est_ai_ai=0;
                            versionIA1=null;
                            versionIA2=null;
                        }else if(est_h_h==1){
                            grille.getChildren().remove(grille.getChildren().size()-2, grille.getChildren().size());
                            est_h_h=0;
                            Name1.setText(null);
                            Name2.setText(null);
                        }
                        est_h_ai=1;
                        Name1.setMinSize(width/10, 30);
                        Name1.setMaxHeight(40);
                        Name1.setAlignment(Pos.CENTER);
                        StackPane n1 = new StackPane();
                        n1.getChildren().add(Name1);
                        grille.add(n1, 1, 2);
                        ToggleButton facile;
                        facile = bouton.creer("facile1"); //Facile, Einfach
                        facile.setToggleGroup(ia1);
                        StackPane f1 = new StackPane();
                        f1.getChildren().add(facile);
                        grille.add(f1, 0, 3);
                        ToggleButton moyenne;
                        moyenne = bouton.creer("moyenne1"); //Media, Mittel/Normal
                        moyenne.setToggleGroup(ia1);
                        StackPane m1 = new StackPane();
                        m1.getChildren().add(moyenne);
                        grille.add(m1, 1, 3);
                        ToggleButton difficile;
                        difficile = bouton.creer("difficile1"); //Difficile, Schwer
                        difficile.setToggleGroup(ia1);
                        StackPane d1 = new StackPane();
                        d1.getChildren().add(difficile);
                        grille.add(d1, 2, 3);
                        ia1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle old_toggle, Toggle new_toggle) {
                                    if (ia1.getSelectedToggle() != null) {
                                        versionIA1 = ia1.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA1 : " + versionIA1);
                                    }
                                }
                            });
                    }
                    else if(IAs.isSelected()){
                        if(est_h_ai==1){
                            grille.getChildren().remove(grille.getChildren().size()-4, grille.getChildren().size());
                            est_h_ai=0;
                            versionIA1=null;
                            Name1.setText(null);
                        }else if(est_h_h==1){
                            grille.getChildren().remove(grille.getChildren().size()-2, grille.getChildren().size());
                            est_h_h=0;
                            Name1.setText(null);
                            Name2.setText(null);
                        }
                        est_ai_ai=1;
                        ToggleButton facile1;
                        facile1 = bouton.creer("facile1"); //Facile, Einfach
                        facile1.setToggleGroup(ia1);
                        StackPane f1 = new StackPane();
                        f1.getChildren().add(facile1);
                        grille.add(f1, 0, 2);
                        ToggleButton moyenne1;
                        moyenne1 = bouton.creer("moyenne1"); //Media, Mittel/Normal
                        moyenne1.setToggleGroup(ia1);
                        StackPane m1 = new StackPane();
                        m1.getChildren().add(moyenne1);
                        grille.add(m1, 1, 2);
                        ToggleButton difficile1;
                        difficile1 = bouton.creer("difficile1"); //Difficile, Schwer
                        difficile1.setToggleGroup(ia1);
                        StackPane d1 = new StackPane();
                        d1.getChildren().add(difficile1);
                        grille.add(d1, 2, 2);
                        ia1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle old_toggle, Toggle new_toggle) {
                                    if (ia1.getSelectedToggle() != null) {
                                        versionIA1 = ia1.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA1 : " + versionIA1);
                                    }
                                }
                            });
                        ToggleButton facile2;
                        facile2 = bouton.creer("facile2"); //Facile, Einfach
                        facile2.setToggleGroup(ia2);
                        StackPane f2 = new StackPane();
                        f2.getChildren().add(facile2);
                        grille.add(f2, 0, 3);
                        ToggleButton moyenne2;
                        moyenne2 = bouton.creer("moyenne2"); //Media, Mittel/Normal
                        moyenne2.setToggleGroup(ia2);
                        StackPane m2 = new StackPane();
                        m2.getChildren().add(moyenne2);
                        grille.add(m2, 1, 3);
                        ToggleButton difficile2;
                        difficile2 = bouton.creer("difficile2"); //Difficile, Schwer
                        difficile2.setToggleGroup(ia2);
                        StackPane d2 = new StackPane();
                        d2.getChildren().add(difficile2);
                        grille.add(d2, 2, 3);
                        ia2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle old_toggle, Toggle new_toggle) {
                                    if (ia2.getSelectedToggle() != null) {
                                        versionIA2 = ia2.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA2 : " + versionIA2);
                                    }
                                }
                            });
                    }
                }
            }
        });
        
        
        
        
        
        
        
        
        
        
        
        /*joueur1.setFont(new Font("Copperplate", maxJoueur/10));
        joueur1.setAlignment(Pos.CENTER);
        joueur1.setMinSize(minJoueur, 30);
        joueur1.setMaxSize(maxJoueur, 70);
        StackPane jo1 = new StackPane();
        jo1.getChildren().add(joueur1);
        grille.add(jo1, 1, 0);
        
        final ToggleGroup j1 = new ToggleGroup();
        RadioBouton bouton = new RadioBouton(primaryStage, i);
        ToggleButton humain1;
        humain1 = bouton.creer("humain1");
        humain1.setToggleGroup(j1);
        StackPane hu1 = new StackPane();
        hu1.getChildren().add(humain1);
        grille.add(hu1, 0, 1);
        ToggleButton IA1;
        IA1 = bouton.creer("IA1");
        IA1.setToggleGroup(j1);
        StackPane i1 = new StackPane();
        i1.getChildren().add(IA1);
        grille.add(i1, 2, 1);
        
        Name1.setText(null);
        final ToggleGroup ia1 = new ToggleGroup();
        j1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
                if (j1.getSelectedToggle() != null) {
                    if(humain1.isSelected()){
                        if(est_ai1==1){
                            grille.getChildren().remove(grille.getChildren().size()-3, grille.getChildren().size());
                            est_ai1=0;
                            versionIA1=null;
                        }
                        est_h1=1;
                        Name1.setMinSize(width/10, 30);
                        Name1.setMaxHeight(40);
                        Name1.setAlignment(Pos.CENTER);
                        StackPane n1 = new StackPane();
                        n1.getChildren().add(Name1);
                        grille.add(n1, 1, 2);
                    }
                    else if(IA1.isSelected()){
                        if(est_h1==1){
                            grille.getChildren().remove(grille.getChildren().size()-1);
                            est_h1=0;
                            //Name1 = new TextField();
                             Name1.setText(null);
                        }
                        est_ai1=1;
                        ToggleButton facile;
                        facile = bouton.creer("facile1"); //Facile, Einfach
                        facile.setToggleGroup(ia1);
                        StackPane f1 = new StackPane();
                        f1.getChildren().add(facile);
                        grille.add(f1, 0, 2);
                        ToggleButton moyenne;
                        moyenne = bouton.creer("moyenne1"); //Media, Mittel/Normal
                        moyenne.setToggleGroup(ia1);
                        StackPane m1 = new StackPane();
                        m1.getChildren().add(moyenne);
                        grille.add(m1, 1, 2);
                        ToggleButton difficile;
                        difficile = bouton.creer("difficile1"); //Difficile, Schwer
                        difficile.setToggleGroup(ia1);
                        StackPane d1 = new StackPane();
                        d1.getChildren().add(difficile);
                        grille.add(d1, 2, 2);
                        ia1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle old_toggle, Toggle new_toggle) {
                                    if (ia1.getSelectedToggle() != null) {
                                        versionIA1 = ia1.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA1 : " + versionIA1);
                                    }
                                }
                            });
                    }
                }
            }
        });
        
        
        joueur2.setFont(new Font("Copperplate", maxJoueur/10));
        joueur2.setAlignment(Pos.CENTER);
        joueur2.setMinSize(width/10, 30);
        joueur2.setMaxSize(maxJoueur, 70);
        StackPane jo2 = new StackPane();
        jo2.getChildren().add(joueur2);
        grille.add(jo2, 1, 3);
        
        final ToggleGroup j2 = new ToggleGroup();
        ToggleButton humain2;
        humain2 = bouton.creer("humain2");
        humain2.setToggleGroup(j2);
        StackPane hu2 = new StackPane();
        hu2.getChildren().add(humain2);
        grille.add(hu2, 0, 4);
        ToggleButton IA2;
        IA2 = bouton.creer("IA2");
        IA2.setToggleGroup(j2);
        StackPane i2 = new StackPane();
        i2.getChildren().add(IA2);
        grille.add(i2, 2, 4);
        
        Name2.setText(null);
        
        final ToggleGroup ia2 = new ToggleGroup();
        j2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
                if (j2.getSelectedToggle() != null) {
                    if(humain2.isSelected()){
                        if(est_ai2==1){
                            grille.getChildren().remove(grille.getChildren().size()-3, grille.getChildren().size());
                            est_ai2=0;
                            versionIA2=null;
                        }
                        est_h2=1;
                        Name2.setMinSize(width/10, 30);
                        Name2.setMaxHeight(40);
                        Name2.setAlignment(Pos.CENTER);
                        StackPane n2 = new StackPane();
                        n2.getChildren().add(Name2);
                        grille.add(n2, 1, 5);
                    }
                    else if(IA2.isSelected()){
                        if(est_h2==1){
                            grille.getChildren().remove(grille.getChildren().size()-1);
                            est_h2=0;
                            //Name2 = new TextField();
                             Name2.setText(null);
                        }
                        est_ai2=1;
                        ToggleButton facile;
                        facile = bouton.creer("facile2");
                        facile.setToggleGroup(ia2);
                        StackPane f2 = new StackPane();
                        f2.getChildren().add(facile);
                        grille.add(f2, 0, 5);
                        ToggleButton moyenne;
                        moyenne = bouton.creer("moyenne2");
                        moyenne.setToggleGroup(ia2);
                        StackPane m2 = new StackPane();
                        m2.getChildren().add(moyenne);
                        grille.add(m2, 1, 5);
                        ToggleButton difficile;
                        difficile = bouton.creer("difficile2");
                        difficile.setToggleGroup(ia2);
                        StackPane d2 = new StackPane();
                        d2.getChildren().add(difficile);
                        grille.add(d2, 2, 5);
                        ia2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle old_toggle, Toggle new_toggle) {
                                    if (ia2.getSelectedToggle() != null) {
                                        versionIA2 = ia2.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA2 : " + versionIA2);
                                    }
                                }
                            });
                    }
                }
            }
        });
        */
       
        AnchorPane.setLeftAnchor(grille, (double) width/8);
        AnchorPane.setRightAnchor(grille, (double) width/8);
        AnchorPane.setTopAnchor(grille, (double) 60);
        AnchorPane.setBottomAnchor(grille, (double) 200);
        pane.getChildren().add(grille);
        
        DropShadow shadow = new DropShadow();
        valider.setFont(new Font(police, width/35));
        valider.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            valider.setEffect(shadow);
        });
        valider.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            valider.setEffect(null);
        });
        valider.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Enregistrer ! ");
                System.out.println("Name1 : " + Name1.getCharacters());
                System.out.println("IA1 : " + versionIA1);
                System.out.println("Name2 : " + Name2.getCharacters());
                System.out.println("IA2 : " + versionIA2);
                String joueur_1, joueur_2;
                if(Name1.getText()!=null){
                    joueur_1 = Name1.getCharacters().toString();
                }
                else{
                    joueur_1 = versionIA1;
                }
                if(Name2.getText()!=null){
                    joueur_2 = Name2.getCharacters().toString();
                }
                else{
                    joueur_2 = versionIA2;
                }   //i.goToPlateau(Name1.getCharacters().toString(), Name2.getCharacters().toString());
                
                Level level1 = Level.EASY; //TODO : faire une fonction qui donne le level de l'IA1 et l'IA2
                Level level2 = Level.EASY;
                controller.goToPlateau(joueur_1, joueur_2, level1, level2);
                
        System.out.println(joueur_1);
        System.out.println(joueur_2);
            }
        });
        AnchorPane.setBottomAnchor(valider, (double) 140);
        //AnchorPane.setTopAnchor(valider, (double) height - 50);
        AnchorPane.setLeftAnchor(valider, (double) tailleDeCase*3);
        AnchorPane.setRightAnchor(valider, (double) tailleDeCase*3);
        pane.getChildren().add(valider);
        
       
        this.getChildren().add(pane);

    }

    

}
