/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.players.TeamColor;
import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

/**
 *
 * @author jonathan
 */
public class InterfaceComb extends Parent {

    private ArrayList<InterfacePion> pions;
    private InterfacePion socle;
    private CacheImage c;

    public InterfaceComb(CacheImage c) {
        this.c = c;
        this.socle = new InterfacePion(Color.TRANSPARENT, null, c);
        this.getChildren().add(pions.get(0));
    }

    public InterfaceComb(CacheImage c, int taille) {
        this.c = c;
        socle = new InterfacePion(Color.TRANSPARENT, null, c, taille);
        this.getChildren().add(socle);
    }

    public void addTile(Cell tile) {
        Color couleur = null;

        if (tile.getTile().color == TeamColor.BLACK) {
            couleur = Color.GRAY;
        } else {
            couleur = Color.WHITE;
        }
        int i = 0;
        this.pions.add(tile.level, new InterfacePion(couleur, tile.getTile().type, c));
        this.getChildren().add(this.pions.get(this.pions.size()-1));
    }

    public void removeTile() {
        this.pions.remove(this.pions.size()-1);
    }

    /*public void modifierTaille(int longueur) {
        this.getChildren().clear();
        Color couleur = null;
        if (this.comb != null) {
            if (this.comb.value().get(0).color == TeamColor.BLACK) {
                couleur = Color.GRAY;
            } else {
                couleur = Color.WHITE;
            }
            int i = 0;
            while (this.comb.value().get(i) != null) {
                InterfacePion pionx = new InterfacePion(couleur, this.comb.value().get(i).type, c);
                this.getChildren().add(pionx);
                this.pions = pionx;
                i++;
            }
        } else {
            this.pions = new InterfacePion(Color.TRANSPARENT, null, c, longueur);
            this.getChildren().add(pions);

        }
    }
*/
}
