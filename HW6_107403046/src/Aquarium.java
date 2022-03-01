import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Aquarium extends JFrame{
	private final JPanel topPanel = new JPanel();
	private final JButton newFish;
	private final JButton newTurtle;
	private final JButton delete;
	private final JButton deleteAll;
	

	
	private JLabel statusbar = new JLabel("�ثe�\��:�s�W��        ���ƶq:0 �Q�t�ƶq:0");
	private String buttonselected = "�s�W��";
	
	private mousePanel fishbowl = new mousePanel(statusbar);
	private Color water = new Color(0,255,255);
	
	public Aquarium() {
		super("FishBowl");
		
		setLayout(new BorderLayout());
		
		topPanel.setLayout(new GridLayout(3,2));

		newFish = new JButton("�s�W��");
		topPanel.add(newFish);
		newFish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(1);
				buttonselected = "�s�W��";
				statusbar.setText(String.format("�ثe�\��:�s�W��        ���ƶq:%d �Q�t�ƶq:%d",fishbowl.getTotalFish(),fishbowl.getTotalTurtle()));
			}
		});
		
		newTurtle = new JButton("�s�W�Q�t");
		topPanel.add(newTurtle);
		newTurtle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(2);
				buttonselected = "�s�W�Q�t";
				statusbar.setText(String.format("�ثe�\��:�s�W�Q�t        ���ƶq:%d �Q�t�ƶq:%d",fishbowl.getTotalFish(),fishbowl.getTotalTurtle()));
			}
		});
		
		delete = new JButton("�������");
		topPanel.add(delete);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(3);
				buttonselected = "�������";
				statusbar.setText(String.format("�ثe�\��:�������        ���ƶq:%d �Q�t�ƶq:%d",fishbowl.getTotalFish(),fishbowl.getTotalTurtle()));
			}
		});
		
		deleteAll = new JButton("��������");
		topPanel.add(deleteAll);
		deleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.clear();
				statusbar.setText(String.format("�ثe�\��:%s        ���ƶq:%d �Q�t�ƶq:%d",buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle()));
			}
		});
		
		topPanel.add(statusbar);
		
		fishbowl.setBackground(water);
		
		add(topPanel, BorderLayout.NORTH);
		add(fishbowl, BorderLayout.CENTER);
		
	}
	
	
}
