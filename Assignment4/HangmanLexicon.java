
import java.io.*;
import java.util.*;

import acm.util.*;
import acmx.export.java.io.FileReader;

public class HangmanLexicon
{
	ArrayList<String> words = new ArrayList<>();

	public HangmanLexicon() {

		try
		{
			int i = 0;
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true)
			{
				String line = rd.readLine();
				if (line == null)
					break;
				words.add(line);
				i++;

			}

		} catch (Exception exp)
		{
		}
	}

	public int getWordCount()
	{
		return words.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index)
	{
		return words.get(index);
	};
}
