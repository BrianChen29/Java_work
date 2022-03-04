import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Aquarium extends JFrame{
	private final JPanel topPanel = new JPanel();
	private final JButton newFish;
	private final JButton newTurtle;
	private final JButton newShark;
	private final JButton newStick;
	private final JButton getStick;
	private final JButton delete;
	private final JButton deleteAll;
	

	
	private JLabel statusbar = new JLabel("�ثe�\��:�s�W��            ���ƶq:0  �Q�t�ƶq:0 �T���ƶq:0 ����ƶq:0 �w���쳽�ƶq:0 �w����Q�t�ƶq:0");
	private String buttonselected = "�s�W��";
	private String statustring = "�ثe�\��:%s            ���ƶq:%d  �Q�t�ƶq:%d �}�Ƽƶq:%d ����ƶq:%d �w���쳽�ƶq: �w����Q�t�ƶq:";
	
	private mousePanel fishbowl = new mousePanel(statusbar);
	private Color water = new Color(0,255,255);
	
	public Aquarium() {
		super("FishBowl");
		
		setLayout(new BorderLayout());
		
		topPanel.setLayout(new GridLayout(3,4));

		newFish = new JButton("�s�W��");
		topPanel.add(newFish);
		newFish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(1);
				buttonselected = "�s�W��";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		newTurtle = new JButton("�s�W�Q�t");
		topPanel.add(newTurtle);
		newTurtle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(2);
				buttonselected = "�s�W�Q�t";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		newShark = new JButton("���T��");
		topPanel.add(newShark);
		newShark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(4);
				buttonselected = "���T��";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		newStick = new JButton("�񳨬�");
		topPanel.add(newStick);
		newStick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(5);
				buttonselected = "�񳨬�";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		getStick = new JButton("������");
		topPanel.add(getStick);
		getStick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(6);
				buttonselected = "������";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		delete = new JButton("�������");
		topPanel.add(delete);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(3);
				buttonselected = "�������";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		deleteAll = new JButton("��������");
		topPanel.add(deleteAll);
		deleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.clear();
				buttonselected = "��������";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		//topPanel.add(new JLabel());
		//topPanel.add(new JLabel());
		
		topPanel.add(statusbar);
		
		fishbowl.setBackground(water);
		
		add(topPanel, BorderLayout.NORTH);
		add(fishbowl, BorderLayout.CENTER);
		
	}
	
	
}
