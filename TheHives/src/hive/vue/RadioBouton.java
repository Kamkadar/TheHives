/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.thehives.TheHives;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
class RadioBouton extends ToggleButton{
    Stage primaryStage;
    Controller controller;
    
    
    RadioBouton(Stage primaryStage, Controller controller) {
        this.primaryStage = primaryStage;
        this.controller = controller;
        
    }
    
    public ToggleButton creer(String type){   
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        int tailleDeCase = width/8;
        double hauteurDeGrille = height*0.7;
        double hauteurDeLigne = hauteurDeGrille/3.5;
        
        CacheImage c = new CacheImage();
        
        String police;
        if(controller.langue == "Russe"){
            police = "Copperplate";
        }
        else{
            police = "Papyrus";
        }
        
        ToggleButton bouton = new ToggleButton("");
            
        if(type=="humains"){
            bouton.setUserData("Humains");
            Image humains = c.getImage("plusDeBoutons/plusDeBoutons/BoutonHumainVsHumain.png");
            ImageView humainsIm = new ImageView(humains);
            humainsIm.setFitHeight(hauteurDeLigne);
            humainsIm.setFitWidth(hauteurDeLigne);
            bouton.setGraphic(humainsIm);
            //bouton.setFont(new Font("Copperplate", tailleDeCase/5));
            /*if(type=="humain1"){
                bouton.setLayoutX(width/2-width/4);
                bouton.setLayoutY(height/4);
            }
            else{
                bouton.setLayoutX(width/2-width/4);
                bouton.setLayoutY(height/1.8);
            }*/
        }
        else if(type=="h_IA"){
            bouton.setUserData("Humain_IA");
            Image h_ia = c.getImage("plusDeBoutons/plusDeBoutons/BoutonIAVsHumain.png");
            ImageView h_iaIm = new ImageView(h_ia);
            h_iaIm.setFitHeight(hauteurDeLigne);
            h_iaIm.setFitWidth(hauteurDeLigne);
            bouton.setGraphic(h_iaIm);
            //bouton.setFont(new Font("Copperplate", tailleDeCase/5));
            /*if(type=="IA1"){
                bouton.setLayoutX(width/2+width/4-width/10);
                bouton.setLayoutY(height/4);
            }
            else{
                bouton.setLayoutX(width/2+width/4-width/10);
                bouton.setLayoutY(height/1.8);
            }*/
        }
        else if(type=="IAs"){
            bouton.setUserData("IAs");
            Image ia_ia = c.getImage("plusDeBoutons/plusDeBoutons/BoutonPersoRobotVsRobo.png");
            ImageView ia_iaIm = new ImageView(ia_ia);
            ia_iaIm.setFitHeight(hauteurDeLigne);
            ia_iaIm.setFitWidth(hauteurDeLigne);
            bouton.setGraphic(ia_iaIm);
            //bouton.setFont(new Font("Copperplate", tailleDeCase/5));
            /*if(type=="IA1"){
                bouton.setLayoutX(width/2+width/4-width/10);
                bouton.setLayoutY(height/4);
            }
            else{
                bouton.setLayoutX(width/2+width/4-width/10);
                bouton.setLayoutY(height/1.8);
            }*/
        }else if(type=="facile1" || type=="facile2"){
            /*Image fleche = c.getImage("Design/MenuPrincipaux/FlecheDuMenuDansHexagone.png");
            ImageView flecheImNG = new ImageView(fleche); 
            double flecheHauteur = hauteurDeLigne*0.2;
            double flecheLargeur = flecheHauteur*7.24;
            flecheImNG.setFitHeight(flecheHauteur);
            flecheImNG.setFitWidth(flecheLargeur);
            bouton.setGraphic(flecheImNG);*/
            bouton.setUserData("facile");
            if(controller.langue=="Français"){
                bouton.setText("Facile");
            }
            else if(controller.langue=="English"){
                bouton.setText("Easy");
            }
            else if(controller.langue=="Italiano"){
                bouton.setText("Facile");
            }
            else if(controller.langue=="Русский"){
                bouton.setText("Легкий");
            }
            else if(controller.langue=="Deutsch"){
                bouton.setText("Einfach");
            }
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
            /*if(type=="facile1"){
                bouton.setLayoutX(width/2-width/4);
                bouton.setLayoutY(height/4+width/9);
            }
            else{
                bouton.setLayoutX(width/2-width/4);
                bouton.setLayoutY(height/1.8+width/9);
            }*/
        }else if(type=="moyenne1" || type=="moyenne2"){
            bouton.setUserData("moyenne");
            if(controller.langue=="Français"){
                bouton.setText("Moyenne");
            }
            else if(controller.langue=="English"){
                bouton.setText("Medium");
            }
            else if(controller.langue=="Italiano"){
                bouton.setText("Media");
            }
            else if(controller.langue=="Русский"){
                bouton.setText("Средний");
            }
            else if(controller.langue=="Deutsch"){
                bouton.setText("Normal");
            }
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
            /*if(type=="facile1"){
                bouton.setLayoutX(width/2);
                bouton.setLayoutY(height/4+width/9);
            }
            else{
                bouton.setLayoutX(width/2);
                bouton.setLayoutY(height/1.8+width/9);
            }*/
        }else if(type=="difficile1" || type=="difficile2"){
            bouton.setUserData("difficile");
            if(controller.langue=="Français"){
            bouton.setText("Difficile");
            }
            else if(controller.langue=="English"){
                bouton.setText("Hard");
            }
            else if(controller.langue=="Italiano"){
                bouton.setText("Difficile");
            }
            else if(controller.langue=="Русский"){
                bouton.setText("Сложный");
            }
            else if(controller.langue=="Deutsch"){
                bouton.setText("Schwer");
            }
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
            /*if(type=="difficile1"){
                bouton.setLayoutX(width/2+width/4);
                bouton.setLayoutY(height/4+width/9);
            }
            else{
                bouton.setLayoutX(width/2+width/4);
                bouton.setLayoutY(height/1.8+width/9);
            }*/
        }
        return bouton;
    
    
    
    
    }
    
}
