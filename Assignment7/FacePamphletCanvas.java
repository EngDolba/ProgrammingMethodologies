
/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants
{
	GLabel msg;
	String msg1 = "";

	/**
	 * Constructor This method takes care of any initialisation needed for the
	 * display
	 */
	public FacePamphletCanvas()
	{
	}

	/**
	 * This method displays a message string near the bottom of the canvas. Every
	 * time this method is called, the previously displayed message (if any) is
	 * replaced by the new message text passed in.
	 */
	public void setMessage(String str)
	{
		msg1 = str;
	}

	public void showMessage()
	{
		this.msg = new GLabel(msg1);
		this.msg = new GLabel(msg1, getWidth() / 2 - msg.getWidth() / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
		this.msg.setFont(MESSAGE_FONT);
		add(this.msg);
	}

	/**
	 * This method displays the given profile on the canvas. The canvas is first
	 * cleared of all existing items (including messages displayed near the bottom
	 * of the screen) and then the given profile is displayed. The profile display
	 * includes the name of the user from the profile, the corresponding image (or
	 * an indication that an image does not exist), the status of the user, and a
	 * list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile)
	{

		removeAll();
		showMessage();
		if (profile == null)
			return;
		drawName(profile);
		drawPhoto(profile);
		drawStatus(profile);
		drawFriends(profile);

	}

	private void drawFriends(FacePamphletProfile profile)
	{

		@SuppressWarnings("rawtypes")
		Iterator iterator = profile.getFriends();
		
			GLabel label = new GLabel("Friends", getWidth() / 2, TOP_MARGIN + IMAGE_MARGIN);
			add(label);
		
		int i = 0;
		while (iterator.hasNext())
		{
			String str = (String) iterator.next();
			GLabel label1 = new GLabel(str, getWidth() / 2, TOP_MARGIN + IMAGE_MARGIN + 20 * (i + 1));
			i++;
			add(label1);
		}
	}

	private void drawStatus(FacePamphletProfile profile)
	{
		GLabel label = new GLabel(profile.getStatus(), LEFT_MARGIN,
				TOP_MARGIN + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN);
		label.setFont(PROFILE_STATUS_FONT);
		add(label);
	}

	private void drawName(FacePamphletProfile profile)
	{
		GLabel label = new GLabel(profile.getName(), LEFT_MARGIN, TOP_MARGIN);
		label.setColor(Color.BLUE);
		label.setFont(PROFILE_NAME_FONT);
		add(label);
	}

	private void drawPhoto(FacePamphletProfile profile)
	{
		if (profile.getImage() == null)
		{
			GRect rect = new GRect(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(rect);
			String noPhoto = "NO PHOTO";
			GLabel label = new GLabel(noPhoto);
			label.setLocation(LEFT_MARGIN + IMAGE_WIDTH / 2 - label.getWidth(),
					TOP_MARGIN + IMAGE_HEIGHT / 2 + label.getHeight());
			label.setFont(PROFILE_IMAGE_FONT);
			add(label);
		} else
		{
			GImage img = profile.getImage();
			img.scale(IMAGE_WIDTH/img.getWidth(),IMAGE_HEIGHT/img.getHeight());
			img.setLocation(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN);
			add(img);

		}
	}

}
