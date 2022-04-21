
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import java.util.EventObject;

import javax.swing.*;

@SuppressWarnings("serial")
public class NameSurfer extends Program implements NameSurferConstants
{
	JLabel label;
	JTextField txtfld;
	JButton clear;
	JButton graphbut;
	JButton delete;
	JButton changeColors;
	NameSurferGraph NSG;
	NameSurferDataBase NSDB;
	JRadioButton graph2;
	JRadioButton columnGraph;
	JRadioButton table;
	ButtonGroup btngrp;
	String empty;
	public static String selected;

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	public void init()
	{
		interactorsInit();
		classesInit();
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so you
	 * will have to define a method to respond to button actions.
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == columnGraph || source == graph2)
		{
			NSG.update(selected);
		}

		if (source == clear)
		{
			NSG.clear();
			txtfld.setText(empty);
		}
		if (source == graphbut || source == txtfld)
		{
			graphButtonAction();
		}
		if (source == table)
		{
			selected = "table";
			NSG.update(selected);

		}
		
		if(source==graph2)
		{
			selected = "graph";
			NSG.update(selected);
		}
		if(source==columnGraph)
		{
			selected = "column";
			NSG.update(selected);
		}

		if (source == delete)
		{
			deleteButtonAction();
		}
		if (source == changeColors)
		{
			NSG.changeColors();
			NSG.update(selected);
		}
	}

	private void graphButtonAction()
	{
		String str = txtfld.getText();
		if (!str.isEmpty())
		{
			str = Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
			NameSurferEntry nameSurf = NSDB.findEntry(str);
			if (nameSurf != null)
			{
				NSG.addEntry(nameSurf);
				NSG.update(selected);
			}
			txtfld.setText(empty);
		}
	}

	private void deleteButtonAction()
	{
		String str = txtfld.getText();
		if (!str.isEmpty())
		{
			str = Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
			NameSurferEntry nameSurf = NSDB.findEntry(str);
			if (nameSurf != null)
			{
				NSG.clear(nameSurf);
				NSG.update(selected);
			}
			txtfld.setText(empty);

		}
	}

	private void interactorsInit()
	{

		label = new JLabel("Name");
		txtfld = new JTextField(7);
		graphbut = new JButton("Graph");
		clear = new JButton("Clear");
		delete = new JButton("Delete");
		graph2 = new JRadioButton("Graph");
		columnGraph = new JRadioButton("Column");
		table = new JRadioButton("Table");
		btngrp = new ButtonGroup();
		changeColors = new JButton("Change Colors");
		selected = "graph";
		empty = "";

		btngrp.add(graph2);
		btngrp.add(columnGraph);
		btngrp.add(table);
		graph2.setSelected(true);
		add(graph2, SOUTH);
		add(columnGraph, SOUTH);
		add(table,SOUTH);
		add(label, SOUTH);
		add(txtfld, SOUTH);
		add(graphbut, SOUTH);
		add(clear, SOUTH);
		add(delete, SOUTH);
		add(changeColors, SOUTH);
		addActionListeners();
		txtfld.addActionListener(this);
		columnGraph.addActionListener(this);
		graph2.addActionListener(this);
		table.addActionListener(this);
	
	}

	private void classesInit()
	{
		NSG = new NameSurferGraph();
		add(NSG);
		NSDB = new NameSurferDataBase(NAMES_DATA_FILE);
	}
}
