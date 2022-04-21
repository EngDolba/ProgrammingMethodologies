
/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FacePamphlet extends Program implements FacePamphletConstants
{
	JButton statusButton;
	JTextField statusTextField;
	JButton pictureButton;
	JTextField pictureTextField;
	JButton friendButton;
	JTextField friendTextField;
	JButton addButton;
	JTextField addTextField;
	JButton deleteButton;
	JButton lookupButton;
	JLabel name;
	FacePamphletProfile currentProfile;
	FacePamphletDatabase FPD;
	FacePamphletCanvas FPC;
	String noProfileChosen;

	/**
	 * This method has the responsibility for initializing the interactors in the
	 * application, and taking care of any other initialization that needs to be
	 * performed.
	 */
	public void init()
	{
		addInteractors();
		addClassesAndVariables();
	}

	private void addClassesAndVariables()
	{
		FPD = new FacePamphletDatabase();
		FPC = new FacePamphletCanvas();
		noProfileChosen = "please select a profile to change";
		add(FPC);
	}

	private void addInteractors()
	{
		statusButton = new JButton("Change Status");
		statusTextField = new JTextField(TEXT_FIELD_SIZE);
		pictureButton = new JButton("Change Picture");
		pictureTextField = new JTextField(TEXT_FIELD_SIZE);
		friendButton = new JButton("Add Friend");
		friendTextField = new JTextField(TEXT_FIELD_SIZE);
		addButton = new JButton("Add");
		addTextField = new JTextField(TEXT_FIELD_SIZE);
		deleteButton = new JButton("Delete");
		lookupButton = new JButton("Lookup");
		name = new JLabel("Name");

		add(statusTextField, WEST);
		add(statusButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(pictureTextField, WEST);
		add(pictureButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(friendTextField, WEST);
		add(friendButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		add(name, NORTH);
		add(addTextField, NORTH);
		add(addButton, NORTH);
		add(deleteButton, NORTH);
		add(lookupButton, NORTH);

		addActionListeners();
		statusTextField.addActionListener(this);
		pictureTextField.addActionListener(this);
		friendTextField.addActionListener(this);

	}

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == statusButton || source == statusTextField)
		{
			statusButtonAction();
		}
		if (source == pictureButton || source == pictureTextField)
		{
			pictureButtonAction();
		}
		if (source == friendButton || source == friendTextField)
		{
			friendButtonAction();
		}
		if (source == addButton || source == addTextField)
		{
			addButtonAction();
		}
		if (source == deleteButton)
		{
			deleteButtonAction();

		}
		if (source == lookupButton)
		{
			lookupButtonAction();
		}
	}

	private void statusButtonAction()
	{
		String status = statusTextField.getText();
		if (status.isEmpty())
		{
			return;
		}
		if (currentProfile != null)
		{
			currentProfile.setStatus(status);
			FPC.setMessage("status updated to " + status);
		} else
		{
			FPC.setMessage(noProfileChosen + " status");
		}
		statusTextField.setText("");
		FPC.displayProfile(currentProfile);

	}

	private void pictureButtonAction()
	{
		String filename = pictureTextField.getText();
		if (filename.isEmpty())
		{
			return;
		}
		if (currentProfile != null)
		{

			GImage image = null;
			try
			{
				image = new GImage(filename);
				FPC.setMessage("Picture Updated");
			} catch (ErrorException ex)
			{
				FPC.setMessage("unable to open: " + filename);
				image = null;
			}
			currentProfile.setImage(image);

		} else
		{
			FPC.setMessage(noProfileChosen + " status");
		}
		pictureTextField.setText("");
		FPC.displayProfile(currentProfile);

	}

	private void friendButtonAction()
	{
		String name = friendTextField.getText();
		if (name.isEmpty())
			return;
		else if (currentProfile == null)
		{
			FPC.setMessage(noProfileChosen + " friend list");
		} else if (name.equals(currentProfile.getName()))
		{
			FPC.setMessage("You are trying to add profile to themself as a friend");
		} else if (FPD.getProfile(name) == null)
		{
			FPC.setMessage(name + " does not exist");
		} else if (FPD.getProfile(name) != null && currentProfile != null)
		{
			if (currentProfile.addFriend(name))
			{
				FPD.getProfile(name).addFriend(currentProfile.getName());
				FPC.setMessage(FPD.getProfile(name).getName() + " added as a friend");
			} else
			{
				FPC.setMessage(currentProfile.getName() + "already has " + name + " as a friend");
			}
		}

		friendTextField.setText("");
		FPC.displayProfile(currentProfile);

	}

	private void addButtonAction()
	{
		String name = addTextField.getText();
		if (name.isEmpty())
		{
			return;
		}
		if (FPD.getProfile(name) != null)
		{
			FPC.setMessage("such profile already exists");
		} else
		{
			FPD.addProfile(new FacePamphletProfile(name));
			FPC.setMessage("the profile with the name " + FPD.getProfile(name).getName() + " was created");
		}
		currentProfile = FPD.getProfile(name);
		addTextField.setText("");
		FPC.displayProfile(currentProfile);

	}

	private void deleteButtonAction()
	{
		String name = addTextField.getText();
		if (name.isEmpty())
			return;
		if (FPD.getProfile(name) != null)
		{
			FPD.deleteProfile(name);
			FPC.setMessage("profile of " + name + " deleted");
		} else
		{
			FPC.setMessage("a profile with the name " + name + " does not exist");
		}
		currentProfile = null;
		addTextField.setText("");
		FPC.displayProfile(currentProfile);

	}

	private void lookupButtonAction()
	{
		String name = addTextField.getText();
		if (name.isEmpty())
			return;
		currentProfile = FPD.getProfile(name);
		if (currentProfile == null)
			FPC.setMessage("A profile with name " + name + " does not exist");
		else
		{
			FPC.setMessage("displaying " + currentProfile.getName());
		}
		addTextField.setText("");
		FPC.displayProfile(currentProfile);

	}

}
