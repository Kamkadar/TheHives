/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.board.Board;
import hive.model.board.TilesPerInsect;

/**
 *
 * @author Thomas
 */
public class AlgorithmsData
{
    public TilesPerInsect tiles; // to get tiles of a specific insect in constant time
    
    public AlgorithmsData(TilesPerInsect tiles)
    {
        this.tiles = tiles;
    }
    
    public static AlgorithmsData getFrom(Board board)
    {
        AlgorithmsData data = null;
        
        return data;
    }
}
