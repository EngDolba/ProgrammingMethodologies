/*
 /*
 * File: checkerBoardKarel.java
 * --------------------------
  this program is written in order to solve the problem 3 shown in assignment 1
 */

import stanford.karel.*;
public class checkerboardKarel extends SuperKarel 
{
	private void moveCarefully() // moves karel only when the front is clear
	{
		if(frontIsClear())
		{
			move();
		}
	}
	
	private void fillOneRowWithBeepersIncluded() // fills one row with beepers(included means it includes the first ujra
	{
		putBeeper();
		while(frontIsClear()) 
		{
			if(frontIsClear()) move();
			if(frontIsClear()) 
			{
				move();
				putBeeper();
			}
		}
		
	}
	private void fillOneRowWithBeepersExcluded() // fills one row with beepers(excluded means it excludes the first ujra
	{
		move();
		putBeeper();
		while(frontIsClear()) 
		{
			if(frontIsClear()) move();
			if(frontIsClear()) 
			{
				move();
				putBeeper();
			}
		}
		
	}
	private void getToInitialPosition() // gets karel to the first ujra in the row and faces it east
	{
		turnAround();
		while(frontIsClear()) move();
		turnAround();
	}
	private void checkerBoardKarel()  // the function implements logic needed for program
	{
		while(frontIsClear())
		{
			fillOneRowWithBeepersIncluded();
			getToInitialPosition();
			turnLeft();
			if(frontIsClear())
				{
				move();
				turnRight();
				fillOneRowWithBeepersExcluded();
				getToInitialPosition();
				turnLeft();
				}
			if(frontIsClear())
				{
				move();
				turnRight();
				}
		}
	}
	private void solveEdgeCase() // this function implements edge case: one-column world
	{
		if(frontIsBlocked())
		{
			turnLeft();
			putBeeper();
			moveCarefully();
			while(frontIsClear()) 
			{
				moveCarefully();
				putBeeper();
				moveCarefully();
			}
		}
	}
	public void run() 
	{
		solveEdgeCase();
		checkerBoardKarel();
	}
}