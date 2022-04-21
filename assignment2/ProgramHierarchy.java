/*
 * File: ProgramHierarchy.java
 * Name: Nikoloz Dolbaia
 * Section Leader: nino gogoberishvili
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram 
{	
	private static final int RECT_WIDTH = 100;
	private static final int RECT_HEIGHT = 75;
	private static final double proportionCoefficient = 1.25; // proportion coefficent is here to separate rects by some distance from the center
	
	// the method draws program located above
	private void drawMainRect()
	{
		// constructing rect
		double xLocation = getWidth()/2-RECT_WIDTH/2;
		double yLocation = getHeight()/2-proportionCoefficient*RECT_HEIGHT-RECT_HEIGHT;
		GRect MainRect = new GRect(xLocation,yLocation,RECT_WIDTH,RECT_HEIGHT);
		//constructing label
		GLabel label = new GLabel("Program");
		double labelWidth = label.getWidth();
		double labelAscent = label.getHeight();
		GLabel MainRectLabel = new GLabel("Program",xLocation+RECT_WIDTH/2-labelWidth/2,yLocation+RECT_HEIGHT/2+labelAscent/4);
		add(MainRect);
		add(MainRectLabel);
	}
	//the method draws console program rect
	private void drawMiddleRect()
	{
		// constructing Rect
		double xLocation = getWidth()/2-RECT_WIDTH/2;
		double yLocation = getHeight()/2+proportionCoefficient*RECT_HEIGHT;
		GRect middleRect = new GRect(xLocation,yLocation,RECT_WIDTH,RECT_HEIGHT);
		// constructing label
		GLabel label = new GLabel("ConsoleProgram");
		double labelWidth = label.getWidth();
		double labelAscent = label.getHeight();
		GLabel middleRectLabel = new GLabel("ConsoleProgram",xLocation+RECT_WIDTH/2-labelWidth/2,yLocation+RECT_HEIGHT/2+labelAscent/4);
		add(middleRect);
		add(middleRectLabel);		
	}
	//the method draws graphics program rect
	private void drawLeftRect()
	{
		double xLocation = getWidth()/2-RECT_WIDTH/2-proportionCoefficient*RECT_WIDTH;
		double yLocation = getHeight()/2+proportionCoefficient*RECT_HEIGHT;
		GRect leftRect = new GRect(xLocation,yLocation,RECT_WIDTH,RECT_HEIGHT);
		GLabel label = new GLabel("GraphicsProgram");
		double labelWidth = label.getWidth();
		double labelAscent = label.getHeight();
		GLabel leftRectLabel = new GLabel("GraphicsProgram",xLocation+RECT_WIDTH/2-labelWidth/2,yLocation+RECT_HEIGHT/2+labelAscent/4);
		add(leftRect);
		add(leftRectLabel);
	}
	//the method draws DialogProgramRect
	private void drawRightRect()
	{
		double xLocation = getWidth()/2-RECT_WIDTH/2+proportionCoefficient*RECT_WIDTH;
		double yLocation = getHeight()/2+proportionCoefficient*RECT_HEIGHT;
		GRect rightRect = new GRect(xLocation,yLocation,RECT_WIDTH,RECT_HEIGHT);
		GLabel label = new GLabel("DialogProgram");
		double labelWidth = label.getWidth();
		double labelAscent = label.getHeight();
		GLabel rightRectLabel = new GLabel("DialogProgram",xLocation+RECT_WIDTH/2-labelWidth/2,yLocation+RECT_HEIGHT/2+labelAscent/4);
		add(rightRect);
		add(rightRectLabel);
	}
	// this method and ones below draws necessary lines
	private void drawRightLine()
	{
		double rightRectX = getWidth()/2+proportionCoefficient*RECT_WIDTH;
		double rightRectY = getHeight()/2+proportionCoefficient*RECT_HEIGHT;
		double centerRectX = getWidth()/2;
		double centerRectY= getHeight()/2-proportionCoefficient*RECT_HEIGHT;
		GLine rightLine = new GLine(rightRectX,rightRectY,centerRectX,centerRectY);
		add(rightLine);
	}
	private void drawLeftLine()
	{
		double leftRectX = getWidth()/2-proportionCoefficient*RECT_WIDTH;
		double leftRectY = getHeight()/2+proportionCoefficient*RECT_HEIGHT;
		double centerRectX = getWidth()/2;
		double centerRectY= getHeight()/2-proportionCoefficient*RECT_HEIGHT;
		GLine leftLine = new GLine(leftRectX,leftRectY,centerRectX,centerRectY);
		add(leftLine);
	}
	private void drawMiddleLine()
	{
		double middleRectX = getWidth()/2;
		double middleRectY = getHeight()/2+proportionCoefficient*RECT_HEIGHT;
		double centerRectX = getWidth()/2;
		double centerRectY= getHeight()/2-proportionCoefficient*RECT_HEIGHT;
		GLine middleLine = new GLine(middleRectX,middleRectY,centerRectX,centerRectY);
		add(middleLine);
	}

	public void run() 
	{
	drawMainRect();
	drawMiddleRect();
	drawLeftRect();
	drawRightRect();
	drawRightLine();
	drawLeftLine();
	drawMiddleLine();
	}
}

