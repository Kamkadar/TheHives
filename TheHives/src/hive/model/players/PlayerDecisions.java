/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

import java.util.EnumMap;

/**
 *
 * @author Thomas
 */
public class PlayerDecisions extends EnumMap<ActionType, Decision>
{
    public PlayerDecisions()
    {
        super(ActionType.class);
    }
}
