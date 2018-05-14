/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.rules;

import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.game.GameState;
import hive.model.insects.InsectType;
import hive.model.players.Player;
import hive.model.players.TeamColor;
import hive.model.players.actions.NoAction;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Thomas
 */
public class HiveRules implements Rules
{
    public HivePutRules put_rules;
    public HiveMoveRules move_rules;
    
    public HiveRules()
    {
        this.put_rules = new HivePutRules();
        this.move_rules = new HiveMoveRules();
    }
    
    public ArrayList<Cell> getPossiblePlacements(GameState state, InsectType type)
    {
        if(queenMustBePut(state))
        {
            if(type == InsectType.QUEEN_BEE)
                return getPossiblePlacementsConstantTime(state);
            else
                return new ArrayList<>();
        }
        else
            return getPossiblePlacementsConstantTime(state);
    }

    @Override
    public ArrayList<Cell> getPossiblePlacements(GameState state, Tile tile)
    {
        return getPossiblePlacements(state, tile.type);
    }

    @Override
    public ArrayList<Cell> getPossibleDestinations(GameState state, Cell cell)
    {
        if(!queenIsPut(state))
            return new ArrayList<>();
        if(queenMustBePut(state))
        {
            if(cell.getTile().type == InsectType.QUEEN_BEE)
                return move_rules.getPossibleDestinations(state, cell);
            else
                return new ArrayList<>();
        }
        else
            return move_rules.getPossibleDestinations(state, cell);
    }
    
    @Override
    public GameStatus getStatus(GameState state)
    {
        boolean current_wins = queenIsSurrounded(state, state.turn.getOpponent());
        boolean opponent_wins = queenIsSurrounded(state, state.turn.getCurrent());
        
        if((current_wins && opponent_wins) || nobodyCanPlay(state))
            return GameStatus.DRAW;
        else if(current_wins)
            return GameStatus.CURRENT_WINS;
        else if(opponent_wins)
            return GameStatus.OPPONENT_WINS;
        else
            return GameStatus.CONTINUES;
    }
    
    private ArrayList<Cell> getPossiblePlacementsConstantTime(GameState state)
    {
        if(state.data.placements == null)
            state.data.placements = put_rules.getPossiblePlacements(state, null);
        return state.data.placements;
    }
    
    private boolean queenIsSurrounded(GameState state, Player player)
    {
        HashSet<Cell> queen_cells = state.data.tiles.get(player.color).get(InsectType.QUEEN_BEE);
        
        // Queen already put
        if(!queen_cells.isEmpty())
        {
            assert queen_cells.size() == 1;
            return HiveFunctions.isSurrounded(queen_cells.iterator().next());
        }
        else
            return false;
    }
    
    private int getMaxQueenTurn()
    {
        return 4;
    }
    
    private boolean queenMustBePut(GameState state)
    {
        return HiveFunctions.nbTurns(state) == getMaxQueenTurn() && !queenIsPut(state);
    }
    
    private boolean queenIsPut(GameState state)
    {
        return !state.data.tiles.get(state.turn.getCurrent().color).get(InsectType.QUEEN_BEE).isEmpty();
    }

    private boolean nobodyCanPlay(GameState state)
    {
        return state.data.trace.get(state.data.trace.size() - 2) instanceof NoAction && state.data.trace.get(state.data.trace.size() - 1) instanceof NoAction;
    }
}
