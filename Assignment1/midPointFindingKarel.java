/*
 /*
 * File: checkerBoardKarel.java
 * --------------------------
  this program is written in order to solve the problem shown in assignment 1
 */

import stanford.karel.*;
public class midPointFindingKarel extends SuperKarel 
{
	private void puttingTwoBeepersAtEnd() // puts each beeper at the end of the row so edge cases are solved and karel is located at 2x1
	{
		putBeeper();
		while(frontIsClear()) move();
		putBeeper();
		turnAround();
		move();
		while(noBeepersPresent()) move();
		turnAround();
		move();
		
	}
	private void moveCheckPut() // puts beepers in the row but puts two ones in the central part
	{
		if (noBeepersPresent())
		{
			putBeeper();
			move();
			while(noBeepersPresent())
			{
				move();
			}
			turnAround();
			move();
		}
	}
	private void pickAllBeepers()  //picks one beeper in the row
	{
		while(frontIsClear()) move();
		turnAround();
		while(frontIsClear())
		{
			pickBeeper();
			move();
		}
		pickBeeper();
	}
	public void run()
	{	
		if(frontIsClear())
		{
		puttingTwoBeepersAtEnd(); 
		while(noBeepersPresent()) moveCheckPut();
		putBeeper();
		pickAllBeepers();
		}
		else putBeeper();
		
		
	}
}