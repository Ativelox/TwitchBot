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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class MainView extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JCheckBox actAsBot, trollPaul, trollMatti, logToFile;
	private ChatArea chatArea;
	
	private JTextField chatAreaDescription, rawMessageAreaDescription;
	
	private JPanel interactionPanel;
	
	private JButton joinChannel, leaveChannel;
	
	private JComboBox<String> joinChannelBox;
	
	private JTextArea logArea; 
	private JTextField logHeader, interactionHeader;
	
	private JPanel logPanel;
	
	private JPanel mainRightPanel, rightPanel2, rightPanel3, rightPanel4, rightPanel5, rightPanel6;
	
	private RawMessageArea rawMessageArea;
	
	private JScrollPane scroll;
	
	private ScreenEssentials essentials;
	
	public MainView() {
		super("CormagBot (TwitchChatBot)");
		
		essentials = new ScreenEssentials();
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		this.setVisible(true);
		this.setResizable(false);
		this.setPreferredSize(essentials.getFrameSize());
		this.setLocation(essentials.getFrameLocation());

		managePanels();
		manageTextFields();
		manageButtons();

		addComponents();
		
		pack();
		
	}

	public void appendTextToLog(String line){
		logArea.append(line);
	}
	
	public void setActionListener(ActionListener a){
		joinChannelBox.addActionListener(a);
		joinChannel.addActionListener(a);
		leaveChannel.addActionListener(a);
	}
	
	public void setItemListener(ItemListener i){
		actAsBot.addItemListener(i);
		trollMatti.addItemListener(i);
		trollPaul.addItemListener(i);
		logToFile.addItemListener(i);
		
	}
	
	public void setKeyListener(KeyListener l){
		chatArea.addKeyListener(l);
		rawMessageArea.addKeyListener(l);
	}
	
	public void setWindowListener(WindowListener w){
		this.addWindowListener(w);
	}
	
	private void addComponents(){
		
		
		rightPanel2.add(chatAreaDescription);
		rightPanel2.add(chatArea);
		
		rightPanel3.add(rawMessageAreaDescription);
		rightPanel3.add(rawMessageArea);
		
		rightPanel4.add(joinChannel);
		rightPanel4.add(joinChannelBox);
		
		rightPanel5.add(leaveChannel);
		
		rightPanel6.add(actAsBot);
		rightPanel6.add(trollPaul);
		rightPanel6.add(trollMatti);
		rightPanel6.add(logToFile);
		
		mainRightPanel.add(rightPanel2);
		mainRightPanel.add(rightPanel3);
		mainRightPanel.add(rightPanel4);
		mainRightPanel.add(rightPanel5);
		mainRightPanel.add(rightPanel6);
		
		logPanel.add(scroll, BorderLayout.CENTER);
		logPanel.add(logHeader, BorderLayout.NORTH);
		
		interactionPanel.add(interactionHeader, BorderLayout.NORTH);
		interactionPanel.add(mainRightPanel, BorderLayout.CENTER);
		
		this.add(logPanel);
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(interactionPanel);
		
	}
	
	private void manageButtons(){
		joinChannel = new JButton("Join Channel");
		joinChannel.setPreferredSize(new Dimension(chatAreaDescription.getPreferredSize().width, 
													joinChannel.getPreferredSize().height));
		joinChannel.setBorder(BorderFactory.createBevelBorder(1));
		
		leaveChannel = new JButton("Leave Channel");
		leaveChannel.setPreferredSize(new Dimension(chatAreaDescription.getPreferredSize().width, 
				joinChannel.getPreferredSize().height));
		leaveChannel.setBorder(BorderFactory.createBevelBorder(1));
		
		actAsBot = new JCheckBox("React to !");
		actAsBot.setSelected(true);
		
		trollPaul = new JCheckBox("Copy Swaul");
		trollPaul.setSelected(false);
		
		trollMatti = new JCheckBox("Copy GeniusMatti");
		trollMatti.setSelected(false);
		
		logToFile = new JCheckBox("Log To File");
		logToFile.setSelected(true);
		
	}
	
	private void managePanels(){
		logPanel = new JPanel();
		interactionPanel = new JPanel();
		logPanel.setPreferredSize(new Dimension(essentials.getFrameSize().width / 2, essentials.getFrameSize().height));
		
		mainRightPanel = new JPanel();
		rightPanel2 = new JPanel();
		rightPanel3 = new JPanel();
		rightPanel4 = new JPanel();
		rightPanel5 = new JPanel();
		rightPanel6 = new JPanel();
		
		logPanel.setLayout(new BorderLayout());
		logPanel.setBorder(new EmptyBorder(10, 30, 30, 30));
		
		interactionPanel.setLayout(new BorderLayout());
		interactionPanel.setBorder(new EmptyBorder(10, 0, 30, 30));
		
		mainRightPanel.setLayout(new BoxLayout(mainRightPanel, BoxLayout.Y_AXIS));
		mainRightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
//		rightPanel2.setBackground(Color.green);
		rightPanel2.setBorder(new EmptyBorder(50, 0, -90, 0));
		
//		rightPanel3.setBackground(Color.ORANGE);
		rightPanel3.setBorder(new EmptyBorder(0, 0, -140, 0));
		
//		rightPanel4.setBackground(Color.yellow);
		rightPanel4.setBorder(new EmptyBorder(50, 0, -90, 0));
		
//		rightPanel5.setBackground(Color.CYAN);

//		rightPanel6.setBackground(Color.black);
	}
	
	private void manageTextFields(){
		
		chatArea = new ChatArea();
		chatArea.setToolTipText("Message");
		chatArea.setFont(new Font("", Font.BOLD, 15));
		chatArea.setPreferredSize(new Dimension(essentials.getFrameSize().width / 2 - 300, 25));
		chatArea.setBorder(BorderFactory.createBevelBorder(1));
		
		rawMessageArea = new RawMessageArea();
		rawMessageArea.setToolTipText("Raw Message");
		rawMessageArea.setFont(new Font("", Font.BOLD, 15));
		rawMessageArea.setPreferredSize(new Dimension(essentials.getFrameSize().width / 2 - 300, 25));
		rawMessageArea.setBorder(BorderFactory.createBevelBorder(1));
		
		logArea = new JTextArea();
		logArea.setEditable(false);
		logArea.setFont(new Font("arial", Font.BOLD, 12));
		logArea.setBorder(BorderFactory.createBevelBorder(1));

		DefaultCaret caret = (DefaultCaret)logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		scroll = new JScrollPane(logArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
											JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		logHeader = new JTextField("Communication Log");
		logHeader.setEditable(false);
		logHeader.setFont(new Font("arial", Font.BOLD, 25));
		logHeader.setBackground(this.getBackground());
		logHeader.setBorder(new EmptyBorder(0, 0, 10, 0));
		logHeader.setHorizontalAlignment(JTextField.CENTER);
		
		interactionHeader = new JTextField("Interaction Header");
		interactionHeader.setEditable(false);
		interactionHeader.setFont(new Font("arial", Font.BOLD, 25));
		interactionHeader.setBackground(this.getBackground());
		interactionHeader.setBorder(new EmptyBorder(0, 0, 10, 0));
		interactionHeader.setHorizontalAlignment(JTextField.CENTER);
		
		chatAreaDescription = new JTextField("Send Message:");
		chatAreaDescription.setEditable(false);
		chatAreaDescription.setBackground(this.getBackground());
		chatAreaDescription.setBorder(new EmptyBorder(0, 0, 0,  130 - chatAreaDescription.getPreferredSize().width));
		
		rawMessageAreaDescription = new JTextField("Send Raw Message:");
		rawMessageAreaDescription.setEditable(false);
		rawMessageAreaDescription.setBackground(this.getBackground());
		rawMessageAreaDescription.setBorder(new EmptyBorder(0, 0, 0, 130 - rawMessageAreaDescription.getPreferredSize().width));
		
		joinChannelBox = new JComboBox<String>();
		joinChannelBox.setPreferredSize(new Dimension(essentials.getFrameSize().width / 2 - 300, 25));
		joinChannelBox.setBorder(BorderFactory.createBevelBorder(1));
		joinChannelBox.setEditable(true);
		joinChannelBox.insertItemAt("ukogmonkey", 0);
		joinChannelBox.insertItemAt("lobosjr", 1);
		joinChannelBox.insertItemAt("imaqtpie", 2);
		joinChannelBox.insertItemAt("iwilldominate", 3);

	}
}
