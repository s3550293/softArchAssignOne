package model;

import model.interfaces.DicePair;

public class DicePairImpl implements DicePair
{   
    private int _Dice1, _Dice2, _NumFaces;

    public DicePairImpl(int dice1, int dice2, int numFaces){
        _Dice1 = dice1;
        _Dice2 = dice2;
        _NumFaces = numFaces;
    }

    @Override
	public int getDice1(){
        return _Dice1;
    }

    @Override
	public int getDice2(){
        return _Dice2;
    }

    @Override
	public int getNumFaces(){
        return _NumFaces;
    }
}