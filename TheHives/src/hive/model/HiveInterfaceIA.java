/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.game.Game;
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveRules;
import hive.model.game.rules.HiveUtil;
import hive.model.insects.InsectType;
import hive.model.players.Player;
import hive.model.players.actions.Action;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.PutAction;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.SimulatedDecision;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import util.hexagons.iterators.Neighbor;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author lucas
 */
public class HiveInterfaceIA implements InterfaceIA
{

    @Override
    public Player currentPlayer(Game game)
    {
        return game.state.turn.getCurrent();
    }

    @Override
    public Player opponentPlayer(Game game)
    {
        return game.state.turn.getOpponent();
    }

    @Override
    public boolean winCurrent(Game game)
    {
        return game.rules.getStatus(game.state) == GameStatus.CURRENT_WINS;
    }

    @Override
    public boolean winOpponent(Game game)
    {
        return game.rules.getStatus(game.state) == GameStatus.OPPONENT_WINS;
    }

    @Override
    public boolean winBoth(Game game)
    {
        return game.rules.getStatus(game.state) == GameStatus.DRAW;
    }

    @Override
    public int queenFreeNeighbour(Player p, Game game)
    {
        HashSet<Cell> queen_positions = game.state.data.tiles.get(p.color).get(InsectType.QUEEN_BEE);
        if (queen_positions.isEmpty())
        {
            return 0;
        }
        NeighborsIterator<TilesStack> neighIter = new NeighborsIterator<>(queen_positions.iterator().next().comb);
        int nbNeighbor = 0;
        while (neighIter.hasNext())
        {
            if (neighIter.next().hexagon.value().isEmpty())
            {
                nbNeighbor++;
            }
        }
        return nbNeighbor;
    }
    @Override
    public ArrayList<Tile> queenNeighbours(Player p, Game game)
    {
        HashSet<Cell> queen_positions = game.state.data.tiles.get(p.color).get(InsectType.QUEEN_BEE);
        ArrayList<Tile> neighbours = new ArrayList<>();
        Neighbor<TilesStack> hex;
        if(queen_positions.isEmpty()){
            return neighbours;
        }
        NeighborsIterator<TilesStack> neighIter = new NeighborsIterator<>(queen_positions.iterator().next().comb);
        while (neighIter.hasNext()){
            hex = neighIter.next();
            if (!hex.hexagon.value().isEmpty()){
                neighbours.add(hex.hexagon.value().peek());

            }
        }
        return neighbours;
    }
    
    // does not treat NoAction case
    @Override
    public ArrayList<Action> currentPlayerPossibilities2(Game game)
    {
        return HiveUtil.getActions(game);

    }
    
    // does not treat NoAction case
    @Override
    public void currentPlayerPossibilities(Game game, ArrayList<Action> actions)
    {
        actions.clear();
        HiveUtil.setPutActions(game, actions);
        HiveUtil.setMoveActions(game, actions);
    }

    @Override
    public ArrayList<Tile> freeTiles(Game game, Player p)
    {
        ArrayList<Tile> free_tiles = new ArrayList<>();
        for (InsectType type : InsectType.implemented_insects)
        {
            HashSet<Cell> sources = game.state.data.tiles.get(p.color).get(type);
            for(Cell source : sources)
            {
                if (game.rules.isFree(game.state, source))
                {
                    free_tiles.add(source.getTile());
                }
            }
        }
        return free_tiles;
    }
    
    public int nbPossibilitiesQueen(Game game, Player p){
        Tile tile = new Tile(InsectType.QUEEN_BEE, p.color);
        
        ArrayList<Cell> placements = new ArrayList<>();
        game.rules.consumePlacements(game.state, tile, placement -> placements.add(placement));
        int nbPossibilities = placements.size();
        
        return nbPossibilities;
    }

    public ArrayList<Tile> blockedTiles(Player p, Game game)
    {
        ArrayList<Tile> blocked_tiles = new ArrayList<>();
        for (InsectType type : InsectType.implemented_insects)
        {
            HashSet<Cell> sources = game.state.data.tiles.get(p.color).get(type);
            for(Cell source : sources)
            {
                if (!game.rules.isFree(game.state, source))
                {
                    blocked_tiles.add(source.getTile());
                }
            }
        }
        return blocked_tiles;
    }

    @Override
    public void doAction(Game game, Action action)
    {
        ((SimulatedDecision) game.state.turn.getCurrent().decision).setAction(action);
        GameProgress gameprogress = new GameProgress(game);
        gameprogress.doAction();
    }

    @Override
    public Action undoAction(Game game)
    {
        GameProgress gameprogress = new GameProgress(game);
        gameprogress.undoAction();
        return game.state.data.last_undo;
    }

    @Override
    public ArrayList<Decision> startSimulation(Game game)
    {
        ArrayList<Decision> decisions = new ArrayList<>();
        Iterator<Player> player_iterator = game.state.players.iterator();
        while (player_iterator.hasNext())
        {
            Player player = player_iterator.next();
            decisions.add(player.decision);
            player.decision = new SimulatedDecision();
        }
        return decisions;
    }

    @Override
    public void endSimulation(Game game, ArrayList<Decision> decisions)
    {
        Iterator<Player> player_iterator = game.state.players.iterator();
        Iterator<Decision> decision_iterator = decisions.iterator();
        while (player_iterator.hasNext())
        {
            Player player = player_iterator.next();
            player.decision = decision_iterator.next();
        }
    }

}
