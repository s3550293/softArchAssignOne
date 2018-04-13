package model;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineImpl implements GameEngine {

	private int _dice1 = 0;
	private int _dice2 = 0;

	private GameEngineCallback gecb = null;

	private Collection<Player> players = new ArrayList<Player>();

	public GameEngineImpl(){}

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.placeBet(bet);
	}

	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
		if(player.getBet() != 0){
			Random rand = new Random();
			while(initialDelay < finalDelay){
				final ScheduledExecutorService newRoll = Executors.newSingleThreadScheduledExecutor();
				newRoll.scheduleAtFixedRate(new Runnable() {
					@Override
					public void run() {
						roll(rand, player);
					}
				}, 0,initialDelay, TimeUnit.MILLISECONDS);
				initialDelay += delayIncrement;
			}
			player.setRollResult(new DicePairImpl(_dice1, _dice2, NUM_FACES));
			gecb.result(player, new DicePairImpl(_dice1, _dice2, NUM_FACES),this);
		}
	}

	private void roll(Random rand, Player player){
		_dice1 = rand.nextInt(NUM_FACES)+1;
		_dice2 = rand.nextInt(NUM_FACES)+1;
		if(player != null){
			gecb.intermediateResult(player, new DicePairImpl(_dice1, _dice2, NUM_FACES), this);
		}else{
			gecb.intermediateHouseResult(new DicePairImpl(_dice1, _dice2, NUM_FACES), this);
		}
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		Random rand = new Random();
		while(initialDelay < finalDelay){
			final ScheduledExecutorService newRoll = Executors.newSingleThreadScheduledExecutor();
			newRoll.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					roll(rand,null);
				}
			}, 0,initialDelay, TimeUnit.MILLISECONDS);
			initialDelay += delayIncrement;
			gecb.houseResult(new DicePairImpl(_dice1, _dice2, NUM_FACES),this);
		}
		
	}

	@Override
	public void addPlayer(Player player) {
		players.add(player);
	}

	@Override
	public Player getPlayer(String id) {
		for(Player p:players){
			if(p.getPlayerId() == id){
				return p;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		for(Player p:players){
			if(p == player){
				players.remove(p);
				return true;
			}
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		gecb = gameEngineCallback;
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if(gameEngineCallback == gecb){
			return true;
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		if(players.size() != 0){
			return players;
		}
		return null;
	}

}
