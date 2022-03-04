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
	

	
	private JLabel statusbar = new JLabel("目前功能:新增魚            魚數量:0  烏龜數量:0 鯊魚數量:0 釣竿數量:0 已釣到魚數量:0 已釣到烏龜數量:0");
	private String buttonselected = "新增魚";
	private String statustring = "目前功能:%s            魚數量:%d  烏龜數量:%d 飼料數量:%d 釣竿數量:%d 已釣到魚數量: 已釣到烏龜數量:";
	
	private mousePanel fishbowl = new mousePanel(statusbar);
	private Color water = new Color(0,255,255);
	
	public Aquarium() {
		super("FishBowl");
		
		setLayout(new BorderLayout());
		
		topPanel.setLayout(new GridLayout(3,4));

		newFish = new JButton("新增魚");
		topPanel.add(newFish);
		newFish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(1);
				buttonselected = "新增魚";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		newTurtle = new JButton("新增烏龜");
		topPanel.add(newTurtle);
		newTurtle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(2);
				buttonselected = "新增烏龜";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		newShark = new JButton("放鯊魚");
		topPanel.add(newShark);
		newShark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(4);
				buttonselected = "放鯊魚";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		newStick = new JButton("放釣竿");
		topPanel.add(newStick);
		newStick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(5);
				buttonselected = "放釣竿";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		getStick = new JButton("收釣竿");
		topPanel.add(getStick);
		getStick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(6);
				buttonselected = "收釣竿";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		delete = new JButton("移除選取");
		topPanel.add(delete);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(3);
				buttonselected = "移除選取";
				statusbar.setText(String.format(statustring,buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle(),fishbowl.getTotalShark(),fishbowl.getTotalFishing()));
			}
		});
		
		deleteAll = new JButton("移除全部");
		topPanel.add(deleteAll);
		deleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.clear();
				buttonselected = "移除全部";
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
