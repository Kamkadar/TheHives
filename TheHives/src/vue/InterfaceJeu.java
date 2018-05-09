/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import hive.thehives.TheHives;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class InterfaceJeu extends Parent {

    GridPane grille;

    Stage stage;
    String nomJoueur1;
    String nomJoueur2;

    public InterfaceJeu(TheHives i, Stage stage, String nomJoueur1, String nomJoueur2) {
        this.nomJoueur1 = nomJoueur1;
        this.nomJoueur2 = nomJoueur2;

        grille = new GridPane();
        //grille.setHgap(10);
        //grille.setVgap(10);


        
        grille.prefHeightProperty().bind(stage.heightProperty());
        grille.prefWidthProperty().bind(stage.widthProperty());
        Outils.fixerRepartition(grille, Outils.HORIZONTAL, 10, 90);
        Outils.fixerRepartition(grille, Outils.VERTICAL, 100);
        grille.add(new InterfacePlateauTool(new CacheImage(), stage), 0, 0);
        
        grille.add(new InterfacePlateau(stage), 0, 1);

        this.getChildren().add(grille);

    }

}
