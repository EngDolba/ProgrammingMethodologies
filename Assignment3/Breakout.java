/*
 * File: Breakout.java
 * -------------------
 * Name:Nikoloz Dolbaia
 * Section Leader: Nino Gogoberishvili
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {
	RandomGenerator rgen = RandomGenerator.getInstance();
	double ballVelocityX = rgen.nextDouble(-10,-4); //randomly generates ball velocity
	int leftHearts = NTURNS; // counts how many lives are left
	double ballVelocityY = 3;//this variable indicates if game is over or not
	boolean gameFlag = true; // saves whether game is going or should be stopped
	GRect paddle; // paddle object
	GOval ball; // ball object
	
	
/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;
	
/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;
/** number of bricks */
	private int NBRICK = NBRICKS_PER_ROW * NBRICK_ROWS;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	
	// implements putting bricks on canvas
	private void putBricks() 
	{	// the method uses cycles in cycles where one cycle deals with rows and the other with columns
		for(int i = 0;i<NBRICK_ROWS;i++)
		{
			for(int k = 0;k<NBRICKS_PER_ROW;k++)
			{
			Color color = Color.WHITE;
			if(i == 0 || i==1) color = Color.red;
			if(i == 2 || i==3) color = Color.orange;
			if(i == 4 || i==5) color = Color.yellow;
			if(i == 6 || i==7) color = Color.green;
			if(i == 8 || i==9) color = Color.cyan;
			GRect rect = new GRect(0+k*(BRICK_WIDTH+4),70+i*(BRICK_HEIGHT+4),BRICK_WIDTH,BRICK_HEIGHT);
			rect.setFillColor(color);
			rect.setFilled(true);
			add(rect);
			}
		}
	}
	// implements putting paddle on canvas
	private void putPaddle()
	{ // self-explanatory
		 paddle = new GRect(APPLICATION_WIDTH/2-PADDLE_WIDTH/2,APPLICATION_HEIGHT-20,PADDLE_WIDTH,PADDLE_HEIGHT);
		 paddle.setFilled(true);
		 paddle.setFillColor(Color.black);
		 add(paddle);
		
	}
	// implements paddle moving based on mouse
	public void mouseMoved(MouseEvent msev)
	{ //
		int mouseLocationX = msev.getX();
		if(mouseLocationX<APPLICATION_WIDTH-PADDLE_WIDTH && mouseLocationX>0) paddle.setLocation(msev.getX(),APPLICATION_HEIGHT-20);
		
	}
	// puts ball on canvas does not implements its moving
	private void putBall()
	{	
		// this randomizes whether the ball will go to the left or right( I needed a method to use IF but there was no neccesity of creating  new method
		if(rgen.nextBoolean()) ballVelocityX *= -1; 		
		ball = new GOval(WIDTH/2-BALL_RADIUS/2,HEIGHT/2-BALL_RADIUS/2,BALL_RADIUS,BALL_RADIUS);
		ball.setFillColor(Color.yellow);
		ball.setFilled(true);
		add(ball);
	}
	// if something overlaps with the either of vertexes of the ball returns the object otherwise returns null
	private GObject getCollidedObject()
	{
		
		if(getElementAt(ball.getX(),ball.getY()) != null) return getElementAt(ball.getX(),ball.getY());
		else if (getElementAt(ball.getX()+BALL_RADIUS,ball.getY()) != null) return getElementAt(ball.getX()+BALL_RADIUS,ball.getY());
		else if (getElementAt(ball.getX()+BALL_RADIUS,ball.getY()+BALL_RADIUS) != null) return getElementAt(ball.getX()+BALL_RADIUS,ball.getY()+BALL_RADIUS);
		else if (getElementAt(ball.getX(),ball.getY()+BALL_RADIUS) != null) return getElementAt(ball.getX(),ball.getY()+BALL_RADIUS);
		return null;
	}
	// implements moving of the ball
	private void ballMove()
	{
	ball.move(ballVelocityX,ballVelocityY);
	if(ball.getX()+BALL_RADIUS>WIDTH || ball.getX()<0) ballVelocityX *= -1;
	if(ball.getY()<0) ballVelocityY *= -1;
	pause(35);
	}
	// checks if either win or lose situation is present and if it is changes gameFlag variable
	private void checkingForWinLose()
	{
		
		if(ball.getY()+BALL_RADIUS>HEIGHT)
		{
			ball.setLocation(WIDTH/2-BALL_RADIUS/2,HEIGHT/2-BALL_RADIUS/2);
			leftHearts -= 1;
		}
		if(leftHearts == 0)
		{ 
			GLabel label = new GLabel("GAME OVER",APPLICATION_WIDTH/2-20,APPLICATION_HEIGHT/2-20);
			add(label);
			gameFlag = false;
			return;
		}
		if(NBRICK==0) 
		{
			GLabel label = new GLabel("YOU WIN",APPLICATION_WIDTH/2-20,APPLICATION_HEIGHT/2-20);
			add(label);
			gameFlag = false;
			return;
		}
	}
	// implements collisions and also counts remaining bricks
	private void implementingCollisions()
	{
		if(getCollidedObject() != null) 
		{
			if(getCollidedObject() == paddle)
			{
				ballVelocityY  *= -1;
				ball.move(ballVelocityX,ballVelocityY);
				/*  the code is below is a try to weaken
				 * the effects of coliding the paddle with the ball
				 * but I was not able to fully debug it
				 */
				if(getCollidedObject()==paddle)
				{
					ball.move(0,-5);
				}
			}
			else 
			{
				remove(getCollidedObject());
				ballVelocityY *= -1;
				NBRICK -=1 ;
			}
		}
	}
	// puts everything necessary on the canvas but does not actually starts the game
	private void initialize() 
	{
		
		putBricks();
		putPaddle();
		putBall();
	}
	// this actually starts the game
	private void game()
	{
		while(gameFlag)
		{
			ballMove();
			implementingCollisions();
			checkingForWinLose();
		}
	}
	
	public void run() 
	{
	addMouseListeners();
	initialize();
	waitForClick();
	game();
	}
	

}
