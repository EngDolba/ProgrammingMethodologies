/*
 * File: Target.java
 * Name: Nikoloz Dolbaia
 * Section Leader: nino gogoberishvili
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	private static final int outerCircleRadius = 72; 
	private static final double middleCircleRadius = 46.7; 
	private static final double	innerCircleRadius = 21.5; 

	
	// paints outer Circle
	private void paintingOuterCircle()
	{
		GOval outerCircle = new GOval(getWidth()/2-outerCircleRadius/2,getHeight()/2-outerCircleRadius/2,outerCircleRadius,outerCircleRadius);
		outerCircle.setFillColor(Color.RED);
		outerCircle.setFilled(true);
		add(outerCircle);
	}
	//paints middle circle
	private void paintingMiddleCircle()
	{
		GOval middleCircle = new GOval(getWidth()/2-middleCircleRadius/2,getHeight()/2-middleCircleRadius/2,middleCircleRadius,middleCircleRadius);
		middleCircle.setFillColor(Color.WHITE);
		middleCircle.setFilled(true);
		add(middleCircle);
	}
	// paints inner circle
	private void paintingInnerCircle()
	{
		GOval innerCircle = new GOval(getWidth()/2-innerCircleRadius/2,getHeight()/2-innerCircleRadius/2,innerCircleRadius,innerCircleRadius);
		innerCircle.setFillColor(Color.RED);
		innerCircle.setFilled(true);
		add(innerCircle);
	}
	
	public void run() {
		paintingOuterCircle();
		paintingMiddleCircle();
		paintingInnerCircle();
	}
}
