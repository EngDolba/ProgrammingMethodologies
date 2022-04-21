
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;

public class Hangman extends ConsoleProgram
{
	private HangmanCanvas canvas;
	int guessAmount = 8;
	HangmanLexicon lex = new HangmanLexicon();
	RandomGenerator rgen = RandomGenerator.getInstance();

	// counts chars in a string
	private int countChar(String string, char ch)
	{
		int cnt = 0;

		for (int i = 0; i < string.length(); i++)
		{
			if (string.charAt(i) == ch)
			{
				cnt++;
			}
		}

		return cnt;
	}

	// replaces dash with an actual character if guessed within a dashed string
	private String replaceAllChars(String letter, String dashString, char ch)
	{
		int num = countChar(letter, ch);
		String string1 = letter;
		for (int i = 0; i < num; i++)
		{
			int k = string1.indexOf(ch);
			string1 = replaceDashWithChar(string1, k, '!'); // replaces the character within string so it does not occur
															// anymore
			dashString = replaceDashWithChar(dashString, k, ch);
		}
		return dashString;

	}

	// submethod of the method above
	private String replaceDashWithChar(String string, int index, char ch)
	{
		if (index == 0)
			string = ch + string.substring(1);
		else if (index == string.length() - 1)
			string = string.substring(0, string.length() - 1) + ch;
		else
		{
			string = string.substring(0, index) + ch + string.substring(index + 1);
		}
		return string;
	}

	// creates String with dashes in it
	private String createDashString(int dashNumber)
	{
		String string = "";
		for (int i = 0; i < dashNumber; i++)
		{
			string += "-";
		}
		return string;
	}

	// does preparatory works, chooses random word and returns it
	private String initialize()
	{
		String chosenWord = "dolba"; // lex.getWord(rgen.nextInt(lex.getWordCount()));
		chosenWord = chosenWord.toLowerCase();
		println("Welcome to the game \n" + "I believe the rules are known to you");
		println(createDashString(chosenWord.length()));
		println(" Here is the letter it consists of " + chosenWord.length() + " characters");
		println("now the game may begin. please make your choice");
		return chosenWord;
	}

	// gets letter and checks it
	private char getWord()
	{
		char letter;
		while (true)
		{
			String ch = readLine("enter the character.if you enter multiple ones only the first will be accepted: ");

			letter = ch.charAt(0);
			if ('A' <= letter && letter <= 'Z')
				letter += 32;
			if ('a' > letter || letter > 'z')
			{
				println("the character entered was not letter. try again");
			}
			if (Character.isLetter(letter))
				break;
		}
		return letter;
	}

	// game logic
	private void getAndCheckWord(String chosenWord)
	{
		int wordCount = chosenWord.length();
		String guessWord = createDashString(chosenWord.length());
		String guessChars = "";
		canvas.displayWord(guessWord);
		canvas.addRamp();

		while (true)
		{
			if (wordCount == 0)
			{
				println("You Guessed the letter");
				return;
			}
			if (guessAmount == 0)
			{
				println("you didn't guess the letter");
				return;
			}
			char letter = getWord();

			if (countChar(chosenWord, letter) == 0)
			{
				println("nope there are no " + letter + "-s");
				guessAmount -= 1;
				guessChars += " " + Character.toUpperCase(letter);
				canvas.noteIncorrectGuess(guessChars, guessAmount);
				println("you have left " + guessAmount + " guesses");
			}
			if (countChar(guessWord, letter) != 0)
				println("you  already guessed that one");
			else if (countChar(chosenWord, letter) != 0)
			{
				println("you guessed " + countChar(chosenWord, letter) + " characters");
				wordCount -= countChar(chosenWord, letter); // to clarify guessWord is one with the dashes
				guessWord = replaceAllChars(chosenWord, guessWord, letter); // and guessed characters and the chosenWord
																			// is one who keeps the actual word
				println(guessWord);
				canvas.displayWord(guessWord.toUpperCase());
			}
		}
	}

	private void game()
	{
		getAndCheckWord(initialize());
	}

	public void init()
	{
		canvas = new HangmanCanvas();
		add(canvas);
	}

	public void run()
	{
		while (true)
		{
			game();
			while (true)
			{
				String prompt = readLine("Do you wish to try again?(Y/N): ");
				if (prompt.charAt(0) == 'Y')
				{
					canvas.reset();
					guessAmount = 8;
					break;
				}
				if (prompt.charAt(0) == 'N')
					return;
			}
		}
	}

}
