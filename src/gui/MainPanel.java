package client.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Director;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{
	
	private static final int LABELS_NUMBER = 5;
	private static final int FIELDS_NUMBER = 3;
	private static final int BUTTONS_NUMBER = 4;
	
	private Director director = Director.getOnlyDirector();
	
	private JLabel[] labels = new JLabel[LABELS_NUMBER];
	private JTextField[] fields = new JTextField[FIELDS_NUMBER];
	private JButton[] buttons = new JButton[BUTTONS_NUMBER];
	
	public MainPanel() {
		setLayout(new GridLayout(4, 3));
		generateUIElements();
		addUIElements();
		
		makeTheButtonsMagical();
	}

	private void makeTheButtonsMagical() {
		buttons[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				checkIPAddress();
			}
		});
		
		buttons[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setFilename();
			}
		});
		
		buttons[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setIP();
			}
		});
		
		buttons[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startDownload();
			}
		});
	}

	private void addUIElements() {
		labels[0].setText("Insert IP address and port here: ");
		buttons[0].setText("Check");
		add(labels[0]);
		add(fields[0]);
		add(buttons[0]);
		
		labels[1].setText("Insert name of file to download here: ");
		buttons[1].setText("Check");
		add(labels[1]);
		add(fields[1]);
		add(buttons[1]);
		
		labels[2].setText("Insert server IP address here: ");
		buttons[2].setText("Set IP");
		add(labels[2]);
		add(fields[2]);
		add(buttons[2]);
		
		buttons[3].setText("Connect");
		add(labels[3]);
		add(labels[4]);
		add(buttons[3]);
		
	}

	private void generateUIElements() {
		for (int i = 0; i < FIELDS_NUMBER; i++)
			fields[i] = new JTextField();
		
		for (int i = 0; i < BUTTONS_NUMBER; i++)
			buttons[i] = new JButton();
		
		for (int i = 0; i < LABELS_NUMBER; i++) 
			labels[i] = new JLabel();
	}
	
	public void checkIPAddress(){
		String tmp[] = fields[0].getText().split(":");
		
		try{
			if (director.createSocket(tmp[0], Integer.parseInt(tmp[1]))){
				labels[3].setForeground(Color.GREEN);
				labels[3].setText("Server is reachable");
			} else {
				labels[3].setForeground(Color.RED);
				labels[3].setText("Could not connect to server");
			}
		} catch (ArrayIndexOutOfBoundsException e1){
			fields[0].setText("hostname:port");
			labels[3].setForeground(Color.YELLOW);
			labels[3].setText("Please type address and port");
		} catch (NumberFormatException e2) {
			labels[3].setForeground(Color.YELLOW);
			labels[3].setText("Port has to be a number");
		}
		
	}
	
	public void startDownload(){
		if (director.startDownload()){
			labels[4].setForeground(Color.GREEN);
			labels[4].setText("Downloading file");
		} else {
			labels[4].setForeground(Color.RED);
			labels[4].setText("Could not download file");
		}	
	}
	
	public void setIP(){
		if (director.setIP(fields[2].getText())){
			labels[3].setForeground(Color.GREEN);
			labels[3].setText("Server IP Address set");
		} else {
			labels[3].setForeground(Color.RED);
			labels[3].setText("Could not set IP Address");
		}
		
	}
	
	public void setFilename(){
		if (fields[1].getText().isEmpty())
			fields[1].setText("DownloadedFile");
		if (director.setFilename(fields[1].getText())){
//		if (true){
			labels[2].setForeground(Color.GREEN);
			labels[2].setText("Output filename changed");
		}
	}

}