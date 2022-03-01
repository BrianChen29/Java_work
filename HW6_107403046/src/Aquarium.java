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
	

	
	private JLabel statusbar = new JLabel("目前功能:新增魚        魚數量:0 烏龜數量:0");
	private String buttonselected = "新增魚";
	
	private mousePanel fishbowl = new mousePanel(statusbar);
	private Color water = new Color(0,255,255);
	
	public Aquarium() {
		super("FishBowl");
		
		setLayout(new BorderLayout());
		
		topPanel.setLayout(new GridLayout(3,2));

		newFish = new JButton("新增魚");
		topPanel.add(newFish);
		newFish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(1);
				buttonselected = "新增魚";
				statusbar.setText(String.format("目前功能:新增魚        魚數量:%d 烏龜數量:%d",fishbowl.getTotalFish(),fishbowl.getTotalTurtle()));
			}
		});
		
		newTurtle = new JButton("新增烏龜");
		topPanel.add(newTurtle);
		newTurtle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(2);
				buttonselected = "新增烏龜";
				statusbar.setText(String.format("目前功能:新增烏龜        魚數量:%d 烏龜數量:%d",fishbowl.getTotalFish(),fishbowl.getTotalTurtle()));
			}
		});
		
		delete = new JButton("移除選取");
		topPanel.add(delete);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.setButtonSelected(3);
				buttonselected = "移除選取";
				statusbar.setText(String.format("目前功能:移除選取        魚數量:%d 烏龜數量:%d",fishbowl.getTotalFish(),fishbowl.getTotalTurtle()));
			}
		});
		
		deleteAll = new JButton("移除全部");
		topPanel.add(deleteAll);
		deleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fishbowl.clear();
				statusbar.setText(String.format("目前功能:%s        魚數量:%d 烏龜數量:%d",buttonselected,fishbowl.getTotalFish(),fishbowl.getTotalTurtle()));
			}
		});
		
		topPanel.add(statusbar);
		
		fishbowl.setBackground(water);
		
		add(topPanel, BorderLayout.NORTH);
		add(fishbowl, BorderLayout.CENTER);
		
	}
	
	
}
