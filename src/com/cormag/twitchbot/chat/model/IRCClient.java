package com.cormag.twitchbot.chat.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import com.cormag.twitchbot.commons.Utils;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class IRCClient {

	private static final String FILE_NAME = "log.txt";

	public String currentChannelName = null;

	private LinkedList<String> protocol;

	private LinkedList<String> protocolToAdd;
	/**
	 * A BufferedReader being able to read characters from the sockets input
	 * stream.
	 */
	private BufferedReader reader;

	private Socket socket;

	/**
	 * A BufferedWriter being able to write characters to the sockets output
	 * stream.
	 */
	private OutputStreamWriter writer;

	/**
	 * Creates a new Instance of an IRC client being able to connect, logOn, ...
	 * to the twitchs' chat interface.
	 */
	public IRCClient() {
		this.protocolToAdd = new LinkedList<>();
		this.protocol = new LinkedList<>();
	}

	/**
	 * Directly connects to twitchs' IRC Server, thus initializing a socket.
	 */
	public void connect() {
		try {
			this.socket = new Socket(IRCServer.URL, IRCServer.PORT);

		} catch (UnknownHostException e) {
			System.err.println(
					"Couldn't open a connection to the server. IP address of the host couldn't be determined.");
			System.err.println(e);

		} catch (IOException e) {
			System.err.println("An I/O Exception occured while trying to connect to the server.");
			System.err.println(e);
		}
	}

	/**
	 * Disconnects from the Server, thus closing all buffers and the socket.
	 * 
	 * @return
	 */
	public boolean disconnect() {

		boolean successful = false;
		boolean writerClosed = false;
		boolean readerClosed = false;
		boolean socketClosed = false;

		try {
			this.writer.close();
			writerClosed = true;

		} catch (IOException e) {
			System.err.println("An I/O Exception occured while trying to close the writer.");
		}

		try {
			this.reader.close();
			readerClosed = true;

		} catch (IOException e) {
			System.err.println("An I/O Exception occured while trying to close the reader.");
		}
		try {
			this.socket.close();
			socketClosed = true;

		} catch (IOException e) {
			System.out.println("An I/O Exception occured while trying to close the socket.");
		}

		if (writerClosed && readerClosed && socketClosed) {
			successful = true;
		}

		return successful;
	}

	public String getCurrentChannel() {
		return this.currentChannelName;
	}

	public LinkedList<String> getProtocol() {
		return this.protocolToAdd;
	}

	public void joinChannel(String channelName) {
		this.currentChannelName = channelName;
		this.sendRawMessageToServer("JOIN #" + channelName);

	}

	public void leaveChannel() {
		if (this.currentChannelName != null) {
			this.sendRawMessageToServer("PART #" + this.currentChannelName);
		}
	}

	/**
	 * Logs onto twitchs' IRC Server by writing the needed messages (NICK,
	 * PASS).
	 * 
	 * @param username
	 *            The username of the person using this bot.
	 * @param password
	 *            The auth-key delivered by twitch per bot.
	 */
	public void logOn(String username, String password) {

		boolean writerSucceed = false, readerSucceed = false;

		try {
			this.writer = new OutputStreamWriter(this.socket.getOutputStream());
			writerSucceed = true;

		} catch (IOException e) {
			System.err
					.println("An I/O Exception occured while trying to create a writer to the sockets output stream.");
		}
		try {
			this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			readerSucceed = true;

		} catch (IOException e) {
			System.err.println("An I/O Exception occured while trying to create a reader to the sockets input stream.");
		}

		// Don't continue if either the writer or reader failed to be created!

		if (!(writerSucceed && readerSucceed)) {
			return;
		}

		this.sendRawMessageToServer("PASS " + password);
		this.sendRawMessageToServer("NICK " + username);
	}

	/**
	 * 
	 */
	public void logToFile(boolean log) {
		if (!log) {
			return;
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

			try {

				bw.write("Protocol Start: " + Utils.getDate() + System.lineSeparator() + System.lineSeparator());

				while (!this.protocol.isEmpty()) {
					bw.write(this.protocol.pollLast());

				}

			} catch (IOException e) {
				e.printStackTrace();

			} finally {
				try {
					bw.close();
					
				} catch (IOException e) {
					e.printStackTrace();
					
				}
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public String pollFirst() {
		String line = this.protocolToAdd.pollFirst();
		this.protocol.addFirst(line);
		return line;
	}

	/**
	 * Reads lines from the input stream of the socket.
	 * 
	 * @return LinkedList&ltString&gt: The lines read.
	 */
	public LinkedList<String> readServerMessages() {

		String line = null;
		LinkedList<String> answer = new LinkedList<>();

		try {
			while (this.reader.ready()) {
				line = this.reader.readLine();
				answer.addLast(line);

				this.addToProtocol("SERVER: " + line);

				if (line.startsWith("PING")) {
					pingRespond();
				}
			}

		} catch (IOException e) {
			System.err.println("An I/O Exception occured while trying to read from the input stream.");

		}

		return answer;
	}

	public void removeProtocolEntry(String toRemove) {
		this.protocolToAdd.remove(toRemove);
	}

	public void sendMessageToChat(String text) {
		sendRawMessageToServer("PRIVMSG #" + this.currentChannelName + " :" + text);
	}

	/**
	 * Writes a line to the output stream of the socket.
	 * 
	 * @param line
	 *            The line to be written.
	 * 
	 * @return boolean: <b>true</b> if the writing succeeded, <b>false</b>
	 *         otherwise.
	 */
	public boolean sendRawMessageToServer(String line) {
		try {
			this.writer.write(line + System.lineSeparator());
			this.writer.flush();
			this.addToProtocol("CLIENT: " + line);
			return true;

		} catch (IOException e) {
			System.err.println("An I/O Exception occured while trying to write to the output stream. " + "\n"
					+ "Message: " + line);
			return false;
		}
	}

	private void addToProtocol(String mLine) {
		String line = mLine;
		
		if (line.contains(System.lineSeparator())) {
			line = mLine.replace(System.lineSeparator(), "");
		} else if (line.contains("\n")) {
			line = mLine.replaceAll("\n", "");
		}

		this.protocolToAdd.add(Utils.getTimestamp() + " " + line + System.lineSeparator());
	}

	private void pingRespond() {
		this.sendRawMessageToServer("PONG :tmi.twitch.tv");
	}
}
