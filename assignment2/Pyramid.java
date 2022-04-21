/*
 * File: Pyramid.java
 * Name: Nikoloz Dolbaia
 * Section Leader: Nino gogoberishvili
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
//import java.awt.*;


public class Pyramid extends GraphicsProgram 
{
	private static final int BRICK_WIDTH = 30;
	private static final int BRICK_HEIGHT = 12;
	private static final int BRICKS_IN_BASE = 14;
	//the method below implements making one row of pyramid
	private void makingOneRow(double firstBrickLocation, int baseBrickAmount,double BrickHeightLocation,double brickWidth,double brickHeight)
	{
		
		for(int i=0;i<baseBrickAmount;i++)
		{
		GRect rect = new GRect(firstBrickLocation+brickWidth*i,BrickHeightLocation,brickWidth,brickHeight);
		add(rect);
		}
	}
	private void makingPyramid(int bricksInBase,double brickWidth,double brickHeight) //the method implements making pyramid
	{	
		double firstBrickLocation = (getWidth()-(brickWidth*bricksInBase))/2;
		for (int i=0;i<bricksInBase;i++)
		{
			makingOneRow(firstBrickLocation+(brickWidth/2)*i,bricksInBase-i,getHeight()-brickHeight*(i+1),brickWidth,brickHeight);
		}
	}
	
	public void run() 
	{
		makingPyramid(BRICKS_IN_BASE,BRICK_WIDTH,BRICK_HEIGHT);
	}
}

