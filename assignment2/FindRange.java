/*
 * File: FindRange.java
 * Name: Nikoloz Dolbaia
 * Section Leader: nino gogoberishvili
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int STOPPER = 0;
	// the program reads ints and prints maximum and minimum among them
	private void ReadIntsAndPrintMaxAndMin()
	{
		
		int max = STOPPER;
		int min = STOPPER;
		while(true) // this cycle obtains numbers, operates on them and finds maximum and minimum and stores them in variables
			{
				int number = readInt("Enter a number(if you wish to stop enter "+STOPPER+"): ");
				if (number == STOPPER) break; 
				else if(number>max || max == STOPPER) max = number;
				if(number<min || min == STOPPER) min = number;	
			}
		if(max == STOPPER) println("No number was entered");
		else if(max == min) println("only number was entered so max and min are equal = " + max);
		else
			{
				println("max: "+max+" min: "+min);
			}
	}
	public void run() {
		ReadIntsAndPrintMaxAndMin();
	}
}

