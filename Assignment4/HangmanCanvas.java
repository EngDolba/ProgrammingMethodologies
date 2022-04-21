
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas
{
	GLabel guessWord = new GLabel("", 0 + 20, 0 + 20);

	public void addRamp()
	{
		GLine line = new GLine(getWidth() / 2 - BEAM_LENGTH,
				OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH, getWidth() / 2 - BEAM_LENGTH,
				OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH - SCAFFOLD_HEIGHT);
		GLine line1 = new GLine(getWidth() / 2 - BEAM_LENGTH,
				-SCAFFOLD_HEIGHT + OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH, getWidth() / 2,
				-SCAFFOLD_HEIGHT + OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		GLine line2 = new GLine(getWidth() / 2,
				-SCAFFOLD_HEIGHT + OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH, getWidth() / 2,
				-SCAFFOLD_HEIGHT + OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH + ROPE_LENGTH * 2);
		add(line);
		add(line1);
		add(line2);
	}

	private void addHead()
	{
		GOval oval = new GOval(getWidth() / 2 - HEAD_RADIUS, 0 + OFFSET_FROM_UPPER, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		add(oval);
	}

	private void addBody()
	{
		GLine line = new GLine(getWidth() / 2, 0 + OFFSET_FROM_UPPER + HEAD_RADIUS * 2, getWidth() / 2,
				HEAD_RADIUS * 2 + OFFSET_FROM_UPPER + BODY_LENGTH);
		add(line);
	}

	private void addLeftHand()
	{
		GLine line = new GLine(getWidth() / 2, OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD,
				getWidth() / 2 - UPPER_ARM_LENGTH, OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD);
		GLine line1 = new GLine(getWidth() / 2 - UPPER_ARM_LENGTH,
				OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth() / 2 - UPPER_ARM_LENGTH,
				OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(line1);
		add(line);
	}

	private void addRightHand()
	{
		GLine line = new GLine(getWidth() / 2, OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD,
				getWidth() / 2 + UPPER_ARM_LENGTH, OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD);
		GLine line1 = new GLine(getWidth() / 2 + UPPER_ARM_LENGTH,
				OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, getWidth() / 2 + UPPER_ARM_LENGTH,
				OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(line1);
		add(line);
	}

	private void addLeftLeg()
	{
		GLine line = new GLine(getWidth() / 2, OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH,
				getWidth() / 2 - HIP_WIDTH, OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH);
		add(line);
		GLine line1 = new GLine(getWidth() / 2 - HIP_WIDTH, OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH,
				getWidth() / 2 - HIP_WIDTH, OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		add(line1);
	}

	private void addRightLeg()
	{
		GLine line = new GLine(getWidth() / 2, OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH,
				getWidth() / 2 + HIP_WIDTH, OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH);
		add(line);
		GLine line1 = new GLine(getWidth() / 2 + HIP_WIDTH, OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH,
				getWidth() / 2 + HIP_WIDTH, OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		add(line1);
	}

	private void addLeftFoot()
	{
		GLine line = new GLine(getWidth() / 2 - HIP_WIDTH,
				OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH,
				getWidth() / 2 - HIP_WIDTH - FOOT_LENGTH,
				OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		add(line);
	}

	private void addRightFoot()
	{
		GLine line = new GLine(getWidth() / 2 + HIP_WIDTH,
				OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH,
				getWidth() / 2 + HIP_WIDTH + FOOT_LENGTH,
				OFFSET_FROM_UPPER + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		add(line);
	}

	/** Resets the display so that only the scaffold appears */
	public void reset()
	{
		removeAll();
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word)
	{
		guessWord.setLabel(word);
		guessWord.setFont(new Font("Serif", Font.BOLD, 20));
		add(guessWord);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user. Calling
	 * this method causes the next body part to appear on the scaffold and adds the
	 * letter to the list of incorrect guesses that appears at the bottom of the
	 * window.
	 */

	public void noteIncorrectGuess(String string, int leftGuesses)
	{
		GLabel label1 = new GLabel("", 0 + 20, getHeight() - 20);
		label1.setLabel(string);
		label1.setFont(new Font("Serif", Font.BOLD, 20));
		add(label1);
		switch (leftGuesses)
		{
		case 7:
			addHead();
			break;
		case 6:
			addBody();
			break;
		case 5:
			addLeftHand();
			break;
		case 4:
			addRightHand();
			break;
		case 3:
			addLeftLeg();
			break;
		case 2:
			addRightLeg();
			break;
		case 1:
			addLeftFoot();
			break;
		case 0:
			addRightFoot();
			break;
		}

	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int OFFSET_FROM_UPPER = 75;

}
