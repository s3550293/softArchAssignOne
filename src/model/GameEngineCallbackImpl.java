package model;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see model.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
	private Logger logger = Logger.getLogger("assignment1");

	public GameEngineCallbackImpl()
	{
		// FINE shows rolling output, INFO only shows result
		logger.setLevel(Level.FINE);
	}

	@Override
	public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
	{
		// intermediate results logged at Level.FINE
		// logger.log(Level.FINE, "Intermediate data to log .. String.format() is good here!");
		logger.log(Level.FINE, "Current Player: "+player.getPlayerName()+" rolling: "+dicePair.getDice1()+" | "+dicePair.getDice2());
	}

	@Override
	public void result(Player player, DicePair result, GameEngine gameEngine)
	{
		// final results logged at Level.INFO
		// logger.log(Level.INFO, "Result data to log .. String.format() is good here!");
		logger.log(Level.INFO, "Player: "+player.getPlayerName()+" has rolled a "+result.getDice1()+" and a "+result.getDice2());
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine){
		logger.log(Level.FINE, "The House is rolling: "+dicePair.getDice1()+" | "+dicePair.getDice2());
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine){
		logger.log(Level.INFO, "The House has rolled a "+result.getDice1()+" and a "+result.getDice2());
	}

}
