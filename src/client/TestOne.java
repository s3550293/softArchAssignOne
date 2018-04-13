package client;

import model.GameEngineCallbackImpl;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;


public class TestOne
{
	public static void main(String args[])
	{
		final GameEngine gameEngine = new GameEngineImpl();
		
		// create two test players (NOTE: you will need to implement the 3 arg contructor in SimplePlayer)
        gameEngine.addPlayer(new SimplePlayer("1", "Ava", 999999999));
        gameEngine.addPlayer(new SimplePlayer("2", "Georgie", 100));
        gameEngine.addPlayer(new SimplePlayer("3", "Olivia", 0));
        gameEngine.addPlayer(new SimplePlayer("4", "Jas", 99));

		// register the callback for notifications (all logging output is done by GameEngineCallbackImpl)
		// see provided skeleton class GameEngineCallbackImpl.java
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

		// main loop to add players place a bet and roll
		for (Player player : gameEngine.getAllPlayers())
		{
			gameEngine.placeBet(player, 100);
			gameEngine.addPlayer(player);
			gameEngine.rollPlayer(player, 1, 100, 20);
		}

		// all players have rolled so now house rolls (GameEngineCallBack is
		// called) and results are calculated
		gameEngine.rollHouse(1, 100, 20);
	}
}
