/*
 /*
 * File: StoneMasonKarel.java
 * --------------------------
  this program is written in order to solve the problem 2 shown in assignment 1
 */

import stanford.karel.*;
public class stoneMasonKarel extends SuperKarel 
{
	private void putOneBeeper() // puts beeper only when there is not one present
	{
		if(noBeepersPresent()) 
		{
			putBeeper();
		}
	
	}
	private void moveCarefully() // karel moves only when front is not blocked
	{
		if(frontIsClear())
		{
			move();
		}
	}
	private void fillRowWithStones() // fills one row with stones 
	{
		turnLeft();
		putOneBeeper();
		while(frontIsClear()) 
		{	
			moveCarefully();
			putOneBeeper();	
		}
	}
	private void getToInitialPosition() // after filling one row this method makes karel go back to initial position
	{
		turnAround();
		while(frontIsClear()) 
		{
			moveCarefully();
		}
		turnLeft();
	}
	private void findNextRow() // this method tells karel to move 4 times and find next row
	{
		for(int i = 0; i<4; i++) moveCarefully();
	}
	public void run() 
	{
		while(frontIsClear()) 
		{
		fillRowWithStones();
		getToInitialPosition();
		findNextRow();
		}
		fillRowWithStones();
	}

}