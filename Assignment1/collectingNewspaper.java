/*
 * File: collectingNewsPaper.java
 * ---------------------
 * this program is created in order to to solve problem 1 from assignment 1
 */

import stanford.karel.*;

public class collectingNewspaper extends SuperKarel {
	private void collectNewspaper()  // this function implements picking newspaper
		{
		turnRight();
		move();
		turnLeft();
		for(int i = 0; i < 3; i++) move();
		pickBeeper();
		}
	private void goBack()  // this function implements going back to initial position
	{
		turnAround();
		for(int i = 0; i < 3; i++) move();
		turnRight();
		move();
		turnRight();
	}
	public void run() 
	{
		
		collectNewspaper();
		goBack();
		
	}
}

