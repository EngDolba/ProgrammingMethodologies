/*
 * File: Hailstone.java
 * Name: Nikoloz Dolbaia
 * Section Leader:nino gogoberishvili
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram 
{
	// this function does everything
	private void readIntAndOperate()
	{
		int counter = 0; // this will count steps
		int n = readInt("ENTER A NUMBER: ");
		while(n!=1)
		{
			if(n%2==0) // this if acts if n is even
			{
				n /= 2;
				println("n was even so I took half of it: "+n);
				counter +=1;
			}
			else // this else acts if n is odd
			{
				n = 3*n+1;
				println("n was odd so I multiplied it by 3 and added 1: "+n);
				counter +=1;
			}
		}
		println("the process took "+ counter + " steps"); // this println prints the end result
	}
	
	public void run() 
	{
		readIntAndOperate();
	}
}

