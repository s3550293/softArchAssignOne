package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	
	private String _id, _name;
	private int _points, _bet;
	private DicePair _rollResult;
	
	public SimplePlayer(String id, String name, int points) {
		_id = id;
		_name = name;
		_points = points;
	}
	
	
	@Override
	public String getPlayerName() {
		return _name;
	}

	@Override
	public void setPlayerName(String playerName) {
		_name = playerName;

	}

	@Override
	public int getPoints() {
		return _points;
	}

	@Override
	public void setPoints(int points) {
		_points = points;
	}

	@Override
	public String getPlayerId() {
		return _id;
	}

	@Override
	public boolean placeBet(int bet) {
		if(_points >= bet && bet != 0){
			_bet = bet;
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int getBet() {
		return _bet;
	}

	@Override
	public DicePair getRollResult() {
		return _rollResult;
	}

	@Override
	public void setRollResult(DicePair rollResult) {
		_rollResult = rollResult;

	}

}
