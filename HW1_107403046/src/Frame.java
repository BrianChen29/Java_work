//陳柏澔 107403046 資管三B

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;

public class Frame extends JFrame{
	private final BorderLayout layout;
	private final JPanel toolPanel;
	private final Drawpanel drawPanel = new Drawpanel();
	private final JLabel statusBar;
	//排列方式 上方工具區 畫布區 下方狀態列
	
	private final JPanel comboPanel;
	private final JLabel comboTitle;
	private final JComboBox<String> toolsJComboBox;
	private static final String[] tools = {"筆刷","直線","橢圓形","矩形","圓角矩形"};
	//繪圖工具 combobox
	
	private final JPanel radioPanel;
	private final JLabel radioTitle;
	private final JRadioButton BigRadioButton;
	private final JRadioButton MidRadioButton;
	private final JRadioButton SmlRadioButton;
    private final ButtonGroup sizeGroup;
    //筆刷大小 radiobutton
    
    private final JPanel checkPanel;
    private final JLabel checkTitle;
	private final JCheckBox FillCheckBox;
	//填滿 checkbox
	
	private final JButton colorJButton;
	private Color color;
	private Color bgColor = Color.WHITE;
	
	private final JButton cleanJButton;
	//筆刷顏色 清除畫面 button
	
	public Frame() {
		super("小畫家");
		
		//迎賓訊息
		JOptionPane.showMessageDialog(null,"Welcome","訊息",JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("java.png")));
		
		//排列方式
		setLayout(layout = new BorderLayout());
		
		//上方工具區
		toolPanel = new JPanel();
		toolPanel.setLayout(new FlowLayout());
		
		
		//繪圖工具 combobox
		comboPanel = new JPanel();
		comboPanel.setLayout(new GridLayout(2,1));
		
		comboTitle = new JLabel("繪圖工具");
		comboPanel.add(comboTitle);
		
		toolsJComboBox = new JComboBox<String>(tools);
		toolsJComboBox.setMaximumRowCount(5);
		comboPanel.add(toolsJComboBox);
		
		toolPanel.add(comboPanel);
		
		toolsJComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event)
			{
				if(event.getStateChange() == ItemEvent.SELECTED) {
					if(toolsJComboBox.getSelectedIndex() == 0 && FillCheckBox.isSelected() == true) {
						FillCheckBox.doClick();
					}
					drawPanel.setTool(toolsJComboBox.getSelectedIndex());
					System.out.printf("選擇 %s\n",toolsJComboBox.getSelectedItem());
					
				}
			}
		});
		
		
	    //筆刷大小 radiobutton
		radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(2,3));
		
		radioTitle = new JLabel("筆刷大小");
		radioPanel.add(radioTitle);
		
		radioPanel.add(new JLabel(" "));
		radioPanel.add(new JLabel(" "));
		
    	SmlRadioButton = new JRadioButton("小",true);
    	MidRadioButton = new JRadioButton("中",false);
    	BigRadioButton = new JRadioButton("大",false);
    	radioPanel.add(SmlRadioButton);
    	radioPanel.add(MidRadioButton);
    	radioPanel.add(BigRadioButton);
    	
    	sizeGroup = new ButtonGroup();
    	sizeGroup.add(SmlRadioButton);
    	sizeGroup.add(MidRadioButton);
    	sizeGroup.add(BigRadioButton);
    	toolPanel.add(radioPanel);
    	
    	SmlRadioButton.addItemListener(new RadioButtonListener(0));
    	MidRadioButton.addItemListener(new RadioButtonListener(1));
    	BigRadioButton.addItemListener(new RadioButtonListener(2));
    	
    	
    	//填滿 checkbox    	
		checkPanel = new JPanel();
		checkPanel.setLayout(new GridLayout(2,1));
		
		checkTitle = new JLabel("填滿");
		checkPanel.add(checkTitle);
		
		FillCheckBox = new JCheckBox("");
		checkPanel.add(FillCheckBox);
		
		toolPanel.add(checkPanel);

		FillCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event){
				if(drawPanel.getTool() == 0) {
					drawPanel.setFill(false);
					FillCheckBox.setSelected(false);
				}
				else {
					if(event.getStateChange() == ItemEvent.SELECTED) {

						System.out.print("選擇 填滿\n");
					}
					else {

						System.out.print("取消 填滿\n");
					}
				}
			}
		});
		
		
		//顏色  button
		colorJButton = new JButton("筆刷顏色");
		toolPanel.add(colorJButton);
		colorJButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("點選 筆刷顏色");
				//顯示調色盤
				color = JColorChooser.showDialog(Frame.this, "選擇顏色", color);

			}
		});
		
		//清除 button
	    cleanJButton = new JButton("清除畫面");
	    toolPanel.add(cleanJButton);
		cleanJButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				drawPanel.clear();
				System.out.println("點選 清除畫面");
			}
		});
		
		drawPanel.setBackground(Color.WHITE);//畫布顏色:白
		
		statusBar = new JLabel("指標位置");//狀態列
		
		MouseTracker tracker = new MouseTracker();
		drawPanel.addMouseMotionListener(tracker);
		//游標位置偵測

		
		add(toolPanel, BorderLayout.NORTH);//上方工具區
		add(drawPanel, BorderLayout.CENTER);//中間畫布區
		add(statusBar, BorderLayout.SOUTH);//下方狀態列

	}
	
	
	
	//大中小按鈕的接收器及接收後動作
    private class RadioButtonListener implements ItemListener{
    	private final int index;
    	public RadioButtonListener(int index){
    		this.index = index;
    	}
    	
    	public void itemStateChanged(ItemEvent event){
			if(event.getStateChange() == ItemEvent.SELECTED) {    		  
				switch(index) {
				  case 0: {
					  System.out.printf("選擇 小筆刷\n");
					  break;
				  }
				  case 1: {
					  System.out.printf("選擇 中筆刷\n");
					  break;
				  }
				  case 2: {
					  System.out.printf("選擇 大筆刷\n");
					  break;
				  }
				}
			}
    	}
    }
    
    
    //游標位置偵測
    private class MouseTracker extends MouseMotionAdapter{
    	public void mouseMoved(MouseEvent event) {
    		statusBar.setText(String.format("指標位置 : (%d, %d)", event.getX(), event.getY()));
    	}
    }
}
