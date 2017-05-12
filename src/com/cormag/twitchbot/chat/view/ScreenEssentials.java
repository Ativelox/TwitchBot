/**
 * 
 */
package com.cormag.twitchbot.chat.view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class ScreenEssentials {
	public final Dimension screenSize;

	public ScreenEssentials() {
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	}

	public Point getFrameLocation() {
		return new Point((this.getScreenWidth() / 2) - (this.getFrameSize().width / 2),
				(this.getScreenHeight() / 2) - (this.getFrameSize().height / 2));

	}

	public Dimension getFrameSize() {
		return new Dimension((int) (this.getScreenWidth() * 0.7), (int) (this.getScreenHeight() * 0.7));

	}

	public int getScreenHeight() {
		return this.screenSize.height;

	}

	public Dimension getScreenSize() {
		return this.screenSize;
	}

	public int getScreenWidth() {
		return this.screenSize.width;

	}
}
