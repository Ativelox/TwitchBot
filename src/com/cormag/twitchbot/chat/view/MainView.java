/**
 * 
 */
package com.cormag.twitchbot.chat.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JCheckBox actAsBot, trollPaul, trollMatti, logToFile;
	private ChatArea chatArea;

	private JTextField chatAreaDescription, rawMessageAreaDescription;

	private ScreenEssentials essentials;

	private JPanel interactionPanel;

	private JButton joinChannel, leaveChannel;

	private JComboBox<String> joinChannelBox;
	private JTextArea logArea;

	private JTextField logHeader, interactionHeader;

	private JPanel logPanel;

	private JPanel mainRightPanel, rightPanel2, rightPanel3, rightPanel4, rightPanel5, rightPanel6;

	private RawMessageArea rawMessageArea;

	private JScrollPane scroll;

	public MainView() {
		super("CormagBot (TwitchChatBot)");

		this.essentials = new ScreenEssentials();

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		this.setVisible(true);
		this.setResizable(false);
		this.setPreferredSize(this.essentials.getFrameSize());
		this.setLocation(this.essentials.getFrameLocation());

		managePanels();
		manageTextFields();
		manageButtons();

		addComponents();

		pack();

	}

	public void appendTextToLog(String line) {
		this.logArea.append(line);
	}

	public void setActionListener(ActionListener a) {
		this.joinChannelBox.addActionListener(a);
		this.joinChannel.addActionListener(a);
		this.leaveChannel.addActionListener(a);
	}

	public void setItemListener(ItemListener i) {
		this.actAsBot.addItemListener(i);
		this.trollMatti.addItemListener(i);
		this.trollPaul.addItemListener(i);
		this.logToFile.addItemListener(i);

	}

	public void setKeyListener(KeyListener l) {
		this.chatArea.addKeyListener(l);
		this.rawMessageArea.addKeyListener(l);
	}

	public void setWindowListener(WindowListener w) {
		this.addWindowListener(w);
	}

	private void addComponents() {

		this.rightPanel2.add(this.chatAreaDescription);
		this.rightPanel2.add(this.chatArea);

		this.rightPanel3.add(this.rawMessageAreaDescription);
		this.rightPanel3.add(this.rawMessageArea);

		this.rightPanel4.add(this.joinChannel);
		this.rightPanel4.add(this.joinChannelBox);

		this.rightPanel5.add(this.leaveChannel);

		this.rightPanel6.add(this.actAsBot);
		this.rightPanel6.add(this.trollPaul);
		this.rightPanel6.add(this.trollMatti);
		this.rightPanel6.add(this.logToFile);

		this.mainRightPanel.add(this.rightPanel2);
		this.mainRightPanel.add(this.rightPanel3);
		this.mainRightPanel.add(this.rightPanel4);
		this.mainRightPanel.add(this.rightPanel5);
		this.mainRightPanel.add(this.rightPanel6);

		this.logPanel.add(this.scroll, BorderLayout.CENTER);
		this.logPanel.add(this.logHeader, BorderLayout.NORTH);

		this.interactionPanel.add(this.interactionHeader, BorderLayout.NORTH);
		this.interactionPanel.add(this.mainRightPanel, BorderLayout.CENTER);

		this.add(this.logPanel);
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(this.interactionPanel);

	}

	private void manageButtons() {
		this.joinChannel = new JButton("Join Channel");
		this.joinChannel.setPreferredSize(
				new Dimension(this.chatAreaDescription.getPreferredSize().width, this.joinChannel.getPreferredSize().height));
		this.joinChannel.setBorder(BorderFactory.createBevelBorder(1));

		this.leaveChannel = new JButton("Leave Channel");
		this.leaveChannel.setPreferredSize(
				new Dimension(this.chatAreaDescription.getPreferredSize().width, this.joinChannel.getPreferredSize().height));
		this.leaveChannel.setBorder(BorderFactory.createBevelBorder(1));

		this.actAsBot = new JCheckBox("React to !");
		this.actAsBot.setSelected(true);

		this.trollPaul = new JCheckBox("Copy Swaul");
		this.trollPaul.setSelected(false);

		this.trollMatti = new JCheckBox("Copy GeniusMatti");
		this.trollMatti.setSelected(false);

		this.logToFile = new JCheckBox("Log To File");
		this.logToFile.setSelected(true);

	}

	private void managePanels() {
		this.logPanel = new JPanel();
		this.interactionPanel = new JPanel();
		this.logPanel.setPreferredSize(new Dimension(this.essentials.getFrameSize().width / 2, this.essentials.getFrameSize().height));

		this.mainRightPanel = new JPanel();
		this.rightPanel2 = new JPanel();
		this.rightPanel3 = new JPanel();
		this.rightPanel4 = new JPanel();
		this.rightPanel5 = new JPanel();
		this.rightPanel6 = new JPanel();

		this.logPanel.setLayout(new BorderLayout());
		this.logPanel.setBorder(new EmptyBorder(10, 30, 30, 30));

		this.interactionPanel.setLayout(new BorderLayout());
		this.interactionPanel.setBorder(new EmptyBorder(10, 0, 30, 30));

		this.mainRightPanel.setLayout(new BoxLayout(this.mainRightPanel, BoxLayout.Y_AXIS));
		this.mainRightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		// rightPanel2.setBackground(Color.green);
		this.rightPanel2.setBorder(new EmptyBorder(50, 0, -90, 0));

		// rightPanel3.setBackground(Color.ORANGE);
		this.rightPanel3.setBorder(new EmptyBorder(0, 0, -140, 0));

		// rightPanel4.setBackground(Color.yellow);
		this.rightPanel4.setBorder(new EmptyBorder(50, 0, -90, 0));

		// rightPanel5.setBackground(Color.CYAN);

		// rightPanel6.setBackground(Color.black);
	}

	private void manageTextFields() {

		this.chatArea = new ChatArea();
		this.chatArea.setToolTipText("Message");
		this.chatArea.setFont(new Font("", Font.BOLD, 15));
		this.chatArea.setPreferredSize(new Dimension(this.essentials.getFrameSize().width / 2 - 300, 25));
		this.chatArea.setBorder(BorderFactory.createBevelBorder(1));

		this.rawMessageArea = new RawMessageArea();
		this.rawMessageArea.setToolTipText("Raw Message");
		this.rawMessageArea.setFont(new Font("", Font.BOLD, 15));
		this.rawMessageArea.setPreferredSize(new Dimension(this.essentials.getFrameSize().width / 2 - 300, 25));
		this.rawMessageArea.setBorder(BorderFactory.createBevelBorder(1));

		this.logArea = new JTextArea();
		this.logArea.setEditable(false);
		this.logArea.setFont(new Font("arial", Font.BOLD, 12));
		this.logArea.setBorder(BorderFactory.createBevelBorder(1));

		DefaultCaret caret = (DefaultCaret) this.logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		this.scroll = new JScrollPane(this.logArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.logHeader = new JTextField("Communication Log");
		this.logHeader.setEditable(false);
		this.logHeader.setFont(new Font("arial", Font.BOLD, 25));
		this.logHeader.setBackground(this.getBackground());
		this.logHeader.setBorder(new EmptyBorder(0, 0, 10, 0));
		this.logHeader.setHorizontalAlignment(SwingConstants.CENTER);

		this.interactionHeader = new JTextField("Interaction Header");
		this.interactionHeader.setEditable(false);
		this.interactionHeader.setFont(new Font("arial", Font.BOLD, 25));
		this.interactionHeader.setBackground(this.getBackground());
		this.interactionHeader.setBorder(new EmptyBorder(0, 0, 10, 0));
		this.interactionHeader.setHorizontalAlignment(SwingConstants.CENTER);

		this.chatAreaDescription = new JTextField("Send Message:");
		this.chatAreaDescription.setEditable(false);
		this.chatAreaDescription.setBackground(this.getBackground());
		this.chatAreaDescription.setBorder(new EmptyBorder(0, 0, 0, 130 - this.chatAreaDescription.getPreferredSize().width));

		this.rawMessageAreaDescription = new JTextField("Send Raw Message:");
		this.rawMessageAreaDescription.setEditable(false);
		this.rawMessageAreaDescription.setBackground(this.getBackground());
		this.rawMessageAreaDescription
				.setBorder(new EmptyBorder(0, 0, 0, 130 - this.rawMessageAreaDescription.getPreferredSize().width));

		this.joinChannelBox = new JComboBox<>();
		this.joinChannelBox.setPreferredSize(new Dimension(this.essentials.getFrameSize().width / 2 - 300, 25));
		this.joinChannelBox.setBorder(BorderFactory.createBevelBorder(1));
		this.joinChannelBox.setEditable(true);
		this.joinChannelBox.insertItemAt("ukogmonkey", 0);
		this.joinChannelBox.insertItemAt("lobosjr", 1);
		this.joinChannelBox.insertItemAt("imaqtpie", 2);
		this.joinChannelBox.insertItemAt("iwilldominate", 3);

	}
}
