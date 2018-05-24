/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.thehives;

import hive.controller.Controller;
import hive.vue.CacheImage;
import java.awt.Dimension;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
public class TheHives extends Application
{

    public int HEIGHT = 700;
    public int WIDTH = 800;
    Dimension screenSize;
    Group root;
    Scene scene;
    Stage primaryStage;
    CacheImage cache;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        cache = new CacheImage();
        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT, Color.LIGHTBLUE);
        screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.primaryStage = primaryStage;

        setPrimaryStage();

        primaryStage.show();

        Controller controller = new Controller(primaryStage, scene, cache, screenSize);
    }

    private void setPrimaryStage()
    {
        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.setMaxHeight((int) screenSize.getHeight() + 20);
        primaryStage.setMaxWidth((int) screenSize.getWidth() + 20);
        primaryStage.setTitle("The Hives");
        primaryStage.sizeToScene();
    }

    private void setFullScreen()
    {
        primaryStage.setFullScreen(true); //passer en affichage plein écran
        primaryStage.setFullScreenExitHint("Sortie de plein écran - esc"); //changer le message qui s'affiche après le passage en mode plein écran
        root.setCursor(Cursor.CROSSHAIR); //changer l'apparence du curseur de souris
    }

    public static void main(String[] args)
    {
        Application.launch(TheHives.class, args);
    }
}
