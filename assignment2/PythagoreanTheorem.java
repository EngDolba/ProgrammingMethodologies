/*
 * File: PythagoreanTheorem.java
 * Name: Nikoloz Dolbaia
 * Section Leader:	 nino gogoerishvili
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	
	private int getNumber()
	{
		int a = readInt("enter a number: ");
		return a;
	}
	private void findC(int a,int b)
	{
		double c = Math.sqrt((a*a)+(b*b));
		println("c = " + c);
	}
	public void run() {
	findC(getNumber(),getNumber());
	}
}
