/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.controller.plateauscene.game.GameController;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.players.TeamColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class InterfacePlateau extends Interface
{

    BorderPane borderPane;
    public NodePlateauMain mainGauche;
    public NodePlateauMain mainDroite;
    public NodeRuche ruche;
    GameController gameController;
    private StackPane centerPane;
    ScrollPane scrollPane;
    BorderPane centerMainG;
    BorderPane centerMainD;
    Game game;

    HiveBouton boutonHome;
    HiveBouton boutonSave;
    HiveBouton boutonReplay;
    HiveBouton boutonAnnuler;
    HiveBouton boutonRecommencer;
    HiveBouton boutonConseil;
    HiveBouton boutonRegle;

    BorderPane pane;
    HBox gauche;
    HBox centre;
    HBox droite;
    String j1;
    String j2;

    public InterfacePlateau(Stage stage, Controller controller, Game game, CacheImage c, String joueur1, String joueur2)
    {

        super(stage, controller, c);

        this.game = game;
        this.controller = controller;
        borderPane = new BorderPane();
        centerPane = new StackPane();
        scrollPane = new ScrollPane();
        centerMainG = new BorderPane();
        centerMainD = new BorderPane();

        borderPane.prefWidthProperty().bind(stage.widthProperty());
        borderPane.prefHeightProperty().bind(stage.heightProperty());

        gameController = new GameController(game, this);

        mainGauche = new NodePlateauMain(gameController.game.state.players.get(0).collection, stage, joueur1, c, gameController, this, TeamColor.WHITE);
        mainDroite = new NodePlateauMain(gameController.game.state.players.get(1).collection, stage, joueur2, c, gameController, this, TeamColor.BLACK);

        Image bimMainauche = c.getImage("Design/FenetrePlateau/poseJetona.png");
        BackgroundSize bsiMainGauche = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage baimMainGauche = new BackgroundImage(bimMainauche, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bsiMainGauche);
        Background backgroundMainGauche = new Background(baimMainGauche);

        mainGauche.pions.setBackground(backgroundMainGauche);

        Image bimMainDroite = c.getImage("Design/FenetrePlateau/poseJetona.png");
        BackgroundSize bsiMainDroite = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage baimMainDroite = new BackgroundImage(bimMainDroite, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bsiMainDroite);
        Background backgroundMainDroite = new Background(baimMainDroite);

        mainDroite.pions.setBackground(backgroundMainDroite);

        centerMainD.prefHeightProperty().bind(stage.heightProperty());
        centerMainG.prefHeightProperty().bind(stage.heightProperty());

        centerMainD.setCenter(mainDroite);
        centerMainG.setCenter(mainGauche);

        Image bimPlateau = c.getImage("Design/FenetrePlateau/PlateauCentral.png");
        BackgroundSize bsiPlateau = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage baimPlateau = new BackgroundImage(bimPlateau, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bsiPlateau);
        Background backgroundPlateau = new Background(baimPlateau);

        centerPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setBackground(backgroundPlateau);

        ruche = new NodeRuche(c, gameController);
        ruche.setHandler(this);

        StackPane.setAlignment(ruche, Pos.TOP_CENTER);
        centerPane.getChildren().add(ruche);
        scrollPane.setContent(centerPane);

        scrollPane.setHvalue(0.5);
        scrollPane.setVvalue(0.5);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        BorderPane.setMargin(scrollPane, new Insets(20, 20, 48, 20));
        BorderPane.setMargin(centerMainG, new Insets(20, 20, 48, 20));
        BorderPane.setMargin(centerMainD, new Insets(20, 20, 48, 20));

        borderPane.setLeft(centerMainG);
        borderPane.setRight(centerMainD);
        borderPane.setCenter(scrollPane);
        borderPane.setTop(setTool());

        this.panePrincipale.getChildren().add(borderPane);

        majJoueurCourant(TeamColor.WHITE);

        gameController.start();

    }
    
    public void update()
    {
        ruche.updateTab(); //TODO !!!
        // TODO : main.update()
    }

    private BorderPane setTool()
    {
        width = (int) primaryStage.getWidth();

        String repertoire = "Design/FenetrePlateau/";

        pane = new BorderPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());

        gauche = new HBox();
        droite = new HBox();
        centre = new HBox();

        boutonSave = new HiveBouton(c.getImage(repertoire + "BoutonDisquette.png"), width);
        boutonHome = new HiveBouton(c.getImage(repertoire + "bouttonRetourMenu.png"), width);
        boutonAnnuler = new HiveBouton(c.getImage(repertoire + "FlecheUndo.png"), width);
        boutonConseil = new HiveBouton(c.getImage(repertoire + "Ampoule.png"), width);
        boutonReplay = new HiveBouton(c.getImage(repertoire + "FlecheRedo.png"), width);
        boutonRegle = new HiveBouton(c.getImage(repertoire + "Boutonlivre.png"), width);
        boutonRecommencer = new HiveBouton(c.getImage(repertoire + "replay.png"), width);

        boutonHome.setOnMouseClicked(value -> {
            Stage quitStage = new Stage();
            quitStage.initModality(Modality.APPLICATION_MODAL);
            NodePopup root = new NodePopup("Etes vous sur de vouloir quitter la partie ?", "Quitter", "Annuler", "Sauvegarder et quitter");
            quitStage.setScene(new Scene(root));
            quitStage.show();

            root.valider.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {

                quitStage.close();
                controller.goToMenu();

            });

            root.quitter.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                quitStage.close();
            });

            root.validerSave.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                Stage saveStage = new Stage();
                saveStage.initModality(Modality.APPLICATION_MODAL);
                NodePopupSave rootSave = new NodePopupSave(controller, saveStage, game);
                saveStage.setScene(new Scene(rootSave));
                saveStage.show();

            });

        });

        boutonSave.setOnMouseClicked(value -> {
            Stage primaryStage = new Stage();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            NodePopupSave root = new NodePopupSave(controller, primaryStage, game);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        });

        boutonRegle.setOnMouseClicked(value ->
        {

            Stage primaryStage = new Stage();
            Parent root;
            root = new InterfaceRegles(primaryStage, controller, c, true);
            primaryStage.setTitle("Regles");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        });
        
        
        boutonRecommencer.setOnMouseClicked(value ->
        {
            gameController.restart();
        });
        
        boutonAnnuler.setOnMouseClicked(value ->
        {
            gameController.undo();
        });

        boutonReplay.setOnMouseClicked(value ->
        {
            gameController.redo();
        });

        boutonConseil.setOnMouseClicked(value ->
        {
            gameController.help();
        });

        Group g = new Group();
        g.getChildren().add(centre);
        pane.setLeft(gauche);
        pane.setRight(droite);
        pane.setCenter(g);

        gauche.getChildren().add(boutonHome);
        gauche.getChildren().add(boutonSave);
        gauche.getChildren().add(boutonRecommencer);
        droite.getChildren().add(boutonRegle);
        droite.getChildren().add(boutonPreference);
        droite.getChildren().add(boutonPleinEcran);
        centre.getChildren().add(boutonAnnuler);
        centre.getChildren().add(boutonConseil);
        centre.getChildren().add(boutonReplay);
        return pane;

    }

    public NodePlateauMain getInterfacePlateauMain(TeamColor color)
    {
        return color == TeamColor.BLACK ? mainDroite : mainGauche;
    }

    public void majTileMain(Tile tile, int nbTiles)
    {
        if (tile.color == TeamColor.BLACK)
        {
            mainDroite.maj(tile, nbTiles);
        } else
        {
            mainGauche.maj(tile, nbTiles);
        }
    }

    public void majJoueurCourant(TeamColor color)
    {
        mainDroite.setIsCourant(color == TeamColor.BLACK);
        mainGauche.setIsCourant(color == TeamColor.WHITE);
    }

    @Override
    public void setTextWithCurrentLanguage()
    {
        //pas de texte dans cette interface => rien a mettre a jour
    }
}
