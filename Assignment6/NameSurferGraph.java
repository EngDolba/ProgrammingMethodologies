
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.event.*;
import java.util.*;

import java.awt.*;

@SuppressWarnings("serial")
public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener
{
	ArrayList<NameSurferEntry> nameSurf = new ArrayList<NameSurferEntry>();
	HashMap<NameSurferEntry, Color> graphColors = new HashMap<NameSurferEntry, Color>();
	boolean[][] freePlace = new boolean[50][NDECADES];
	RandomGenerator rgen = RandomGenerator.getInstance();

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph()
	{
		addComponentListener(this);
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear()
	{
		nameSurf.clear();
		update(NameSurfer.selected);

	}

	public void clear(NameSurferEntry nse)
	{
		int i = nameSurf.indexOf(nse);
		if (i == -1)
			return;
		nameSurf.remove(i);
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of entries.
	 * Your application must call update after calling either clear or addEntry;
	 * update is also called whenever the size of the canvas changes.
	 */
	public void update(String selected)
	{
		removeAll();
		double width = getWidth();
		double height = getHeight();
		if (selected.equals("table"))
			drawInformationTableForAllEntries(width, height);
		else
		{

			drawVerticalLines(width, height);
			drawHorizontalLines(width, height);
			drawLabels(width, height);
			if (selected.equals("graph"))
				drawInformationGraphForAllEntries(width, height);
			else
				drawInformationColumnForAllEntries(width, height);
		}
	}

	private void drawInformationTableForAllEntries(double width, double height)
	{
		int tableSize = Math.max(nameSurf.size(), 21);
		for (int x = 0; x < NDECADES + 1; x++)
		{
			for (int y = 0; y < nameSurf.size() + 1; y++)
			{
				GRect rect = new GRect(x * width / 12, y * height / tableSize, width / 12, height / tableSize);
				add(rect);
				rect.setFillColor(Color.GRAY);
				rect.setFilled(true);
				if (nameSurf.size() == y)
					continue;
				GLabel lab1 = new GLabel(nameSurf.get(y).getName(), 2, (y + 1) * height / tableSize + 20);
				add(lab1);
				lab1.sendToFront();
			}
			for (int i = 0; i < NDECADES; i++)
			{
				for (int j = 0; j < nameSurf.size(); j++)
				{
					GLabel lab2 = new GLabel(Integer.toString(nameSurf.get(j).getRank(i)), 2 + (i + 1) * width / 12,
							(j + 2) * height / tableSize - 5);
					add(lab2);
				}
			}

		}

		GLabel lab = new GLabel("Name", width / 600, width / 60);
		add(lab);

		for (int j = 0; j < NDECADES + 1; j++)
		{

			int num = 1900 + (10 * j);
			GLabel G = new GLabel(Integer.toString(num), (j + 1) * width / (NDECADES + 1), width / 60);
			add(G);
		}

	}

	private void drawInformationGraphForOneEntry(NameSurferEntry nse, double width, double height)
	{
		nameAffairs(nse, width, height);
		graphAffairs(nse, width, height);
	}

	private void drawInformationGraphForAllEntries(double width, double height)
	{
		setAllValuesToFalse();
		for (int i = 0; i < nameSurf.size(); i++)
		{
			drawInformationGraphForOneEntry(nameSurf.get(i), width, height);
		}
	}

	private void drawInformationColumnForAllEntries(double width, double height)
	{
		setAllValuesToFalse();
		for (int i = 0; i < NDECADES; i++)
		{
			drawInformationColumnForOneDecade(i, width, height);
		}
		for (int i = 0; i < nameSurf.size(); i++)
		{
			nameAffairsForColumn(nameSurf.get(i), width, height);
		}

	}

	private void drawInformationColumnForOneDecade(int column, double width, double height)
	{
		double onePixelUnit = (height - 2 * GRAPH_MARGIN_SIZE) / 1000;
		// below code is used to sort NameSurfEntry objects with their values in
		// particular decades then sorted list is used to draw rects in ascending order
		// so they does not hide each other
		HashMap<Integer, NameSurferEntry> ndecades = new HashMap<Integer, NameSurferEntry>();
		int[] zOrder = new int[nameSurf.size()];

		for (int j = 0; j < nameSurf.size(); j++)
		{
			ndecades.put(nameSurf.get(j).getRank(column), nameSurf.get(j));
			zOrder[j] = nameSurf.get(j).getRank(column);
		}
		Arrays.sort(zOrder);
		for (int j = 0; j < nameSurf.size(); j++)
		{
			NameSurferEntry entry = ndecades.get(zOrder[j]);
			if (entry.getRank(column) == 0)
				continue;
			GRect rect = new GRect((width / NDECADES) * column,
					height - GRAPH_MARGIN_SIZE - ((1000 - entry.getRank(column)) * onePixelUnit),
					0.9 * width / NDECADES, (-entry.getRank(column) + 1000) * onePixelUnit);
			add(rect);
			rect.setFilled(true);
			rect.setFillColor(setGraphColors(entry));
		}
	}

	private void nameAffairsForColumn(NameSurferEntry nse, double width, double height)
	{

		double onePixelUnit = (height - 2 * GRAPH_MARGIN_SIZE) / 1000;
		for (int i = 0; i < NDECADES; i++)
		{
			if (nse.getRank(i) != 0)
			{
				GLabel label = new GLabel(nse.getName() + nse.getRank(i), (width / NDECADES) * i,
						65 + (nse.getRank(i) * onePixelUnit));
				add(label);
			}
			if (nse.getRank(i) == 0)
			{
				GLabel label = new GLabel(nse.getName() + "*", (width / NDECADES) * i,
						height - GRAPH_MARGIN_SIZE - (15 * findFreePlace(i)));
				add(label);
			}
		}
	}

	private void graphAffairs(NameSurferEntry nse, double width, double height)
	{
		double onePixelUnit = (height - 2 * GRAPH_MARGIN_SIZE) / 1000;
		for (int i = 0; i < NDECADES - 1; i++)
		{
			// deals with going to zero(without it the graph would tend to to the zero
			// place(up instead of down)
			if (nse.getRank(i + 1) == 0 && nse.getRank(i) != 0)
			{
				GLine line = new GLine((width / NDECADES) * i, GRAPH_MARGIN_SIZE + nse.getRank(i) * onePixelUnit,
						(width / NDECADES) * (i + 1), height - GRAPH_MARGIN_SIZE);
				add(line);
				line.setColor(setGraphColors(nse));
			}
			// deals with not drawing graph if there is two zero consecutively
			if (nse.getRank(i + 1) == 0 && nse.getRank(i) == 0)
			{
				continue;
			}
			// deals with drawing graph from the down if name is not zero rank anymore
			if (nse.getRank(i) == 0)
			{
				GLine line = new GLine((width / NDECADES) * i, -1 * GRAPH_MARGIN_SIZE + height,
						(width / NDECADES) * (i + 1), GRAPH_MARGIN_SIZE + nse.getRank(i + 1) * onePixelUnit);
				add(line);
				line.setColor(setGraphColors(nse));
			}
			// takes place if there is not edge case
			if (nse.getRank(i + 1) != 0 && nse.getRank(i) != 0) //
			{
				GLine line = new GLine((width / NDECADES) * i, GRAPH_MARGIN_SIZE + nse.getRank(i) * onePixelUnit,
						(width / NDECADES) * (i + 1), GRAPH_MARGIN_SIZE + nse.getRank(i + 1) * onePixelUnit);
				add(line);
				line.setColor(setGraphColors(nse));
			}
		}
	}

	private void nameAffairs(NameSurferEntry nse, double width, double height)
	{
		double onePixelUnit = (height - 2 * GRAPH_MARGIN_SIZE) / 1000;
		for (int i = 0; i < NDECADES; i++)
		{
			if (nse.getRank(i) == 0)
			{
				GLabel label = new GLabel(nse.getName() + "*", (width / NDECADES) * i,
						height - GRAPH_MARGIN_SIZE - (15 * findFreePlace(i)));
				add(label);
			}

		}
		for (int i = 0; i < NDECADES - 1; i++)
		{
			if (nse.getRank(i) != 0)
			{

				if (nse.getRank(i) > nse.getRank(i + 1))
				{
					GLabel label = new GLabel(nse.getName() + nse.getRank(i), (width / NDECADES) * i,
							65 + (nse.getRank(i) * onePixelUnit));
					add(label);

				}
				if (nse.getRank(i) < nse.getRank(i + 1))
				{
					GLabel label = new GLabel(nse.getName() + nse.getRank(i), (width / NDECADES) * i,
							40 + (nse.getRank(i) * onePixelUnit)); // deals with drawing names where rank is not zero
					add(label);
				}
			}
		}

		if (nse.getRank(NDECADES - 1) != 0)
		{
			GLabel label = new GLabel(nse.getName() + nse.getRank(NDECADES - 1), (width / NDECADES) * (NDECADES - 1),
					50 + (nse.getRank(NDECADES - 1) * onePixelUnit));
			add(label);
		}
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note that
	 * this method does not actually draw the graph, but simply stores the entry;
	 * the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry)
	{
		if (!nameSurf.contains(entry))
			nameSurf.add(entry);
	}

	public void drawVerticalLines(double width, double height)
	{
		for (int i = 0; i < NDECADES; i++)
		{
			GLine ln = new GLine((width / NDECADES) * i, 0, +(width) / NDECADES * i, height);
			add(ln);
		}

	}

	public void drawLabels(double width, double height)
	{
		for (int i = 0; i < NDECADES; i++)
		{
			int numb = 1900 + 10 * i;

			GLabel gLab = new GLabel("" + numb, 0 + (width / NDECADES) * i, height - 37);
			add(gLab);

		}
	}

	public void drawHorizontalLines(double width, double height)
	{
		GLine ln1 = new GLine(0, GRAPH_MARGIN_SIZE, width, GRAPH_MARGIN_SIZE);
		GLine ln2 = new GLine(0, height - GRAPH_MARGIN_SIZE, width, height - GRAPH_MARGIN_SIZE);
		add(ln1);
		add(ln2);

	}

	private Color setGraphColors(NameSurferEntry nse)
	{
		// used for assigning colors to graphs
		Color color = graphColors.get(nse);
		if (color == null)
		{
			color = rgen.nextColor();
			graphColors.put(nse, color);
		}
		return color;

	}

	private int findFreePlace(int column)
	{
		// this method finds free place for the label to be placed on(to be used only in
		// rank zero cases) won't work if there is more than 50 names but there is no
		// way to display 50+ names
		//
		if (column > NDECADES)
			return -1;
		for (int i = 0; i < 50; i++)
		{
			if (freePlace[i][column] == false)
			{
				freePlace[i][column] = true;
				return i;
			}
		}
		return -1;

	}

	private void setAllValuesToFalse()
	{
		for (int i = 0; i < 11; i++)
		{
			for (int j = 0; j < 50; j++)
			{
				freePlace[j][i] = false;
			}
		}
	}

	public void changeColors()
	{
		graphColors.clear();
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e)
	{
	}

	public void componentMoved(ComponentEvent e)
	{

	}

	public void componentResized(ComponentEvent e)
	{
		update(NameSurfer.selected);
	}

	public void componentShown(ComponentEvent e)
	{
	}
}
