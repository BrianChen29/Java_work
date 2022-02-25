//���f�P 107403046 ��ޤTB

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
	//�ƦC�覡 �W��u��� �e���� �U�説�A�C
	
	private final JPanel comboPanel;
	private final JLabel comboTitle;
	private final JComboBox<String> toolsJComboBox;
	private static final String[] tools = {"����","���u","����","�x��","�ꨤ�x��"};
	//ø�Ϥu�� combobox
	
	private final JPanel radioPanel;
	private final JLabel radioTitle;
	private final JRadioButton BigRadioButton;
	private final JRadioButton MidRadioButton;
	private final JRadioButton SmlRadioButton;
    private final ButtonGroup sizeGroup;
    //����j�p radiobutton
    
    private final JPanel checkPanel;
    private final JLabel checkTitle;
	private final JCheckBox FillCheckBox;
	//�� checkbox
	
	private final JButton colorJButton;
	private Color color;
	private Color bgColor = Color.WHITE;
	
	private final JButton cleanJButton;
	//�����C�� �M���e�� button
	
	public Frame() {
		super("�p�e�a");
		
		//�ﻫ�T��
		JOptionPane.showMessageDialog(null,"Welcome","�T��",JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("java.png")));
		
		//�ƦC�覡
		setLayout(layout = new BorderLayout());
		
		//�W��u���
		toolPanel = new JPanel();
		toolPanel.setLayout(new FlowLayout());
		
		
		//ø�Ϥu�� combobox
		comboPanel = new JPanel();
		comboPanel.setLayout(new GridLayout(2,1));
		
		comboTitle = new JLabel("ø�Ϥu��");
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
					System.out.printf("��� %s\n",toolsJComboBox.getSelectedItem());
					
				}
			}
		});
		
		
	    //����j�p radiobutton
		radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(2,3));
		
		radioTitle = new JLabel("����j�p");
		radioPanel.add(radioTitle);
		
		radioPanel.add(new JLabel(" "));
		radioPanel.add(new JLabel(" "));
		
    	SmlRadioButton = new JRadioButton("�p",true);
    	MidRadioButton = new JRadioButton("��",false);
    	BigRadioButton = new JRadioButton("�j",false);
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
    	
    	
    	//�� checkbox    	
		checkPanel = new JPanel();
		checkPanel.setLayout(new GridLayout(2,1));
		
		checkTitle = new JLabel("��");
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

						System.out.print("��� ��\n");
					}
					else {

						System.out.print("���� ��\n");
					}
				}
			}
		});
		
		
		//�C��  button
		colorJButton = new JButton("�����C��");
		toolPanel.add(colorJButton);
		colorJButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("�I�� �����C��");
				//��ܽզ�L
				color = JColorChooser.showDialog(Frame.this, "����C��", color);

			}
		});
		
		//�M�� button
	    cleanJButton = new JButton("�M���e��");
	    toolPanel.add(cleanJButton);
		cleanJButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				drawPanel.clear();
				System.out.println("�I�� �M���e��");
			}
		});
		
		drawPanel.setBackground(Color.WHITE);//�e���C��:��
		
		statusBar = new JLabel("���Ц�m");//���A�C
		
		MouseTracker tracker = new MouseTracker();
		drawPanel.addMouseMotionListener(tracker);
		//��Ц�m����

		
		add(toolPanel, BorderLayout.NORTH);//�W��u���
		add(drawPanel, BorderLayout.CENTER);//�����e����
		add(statusBar, BorderLayout.SOUTH);//�U�説�A�C

	}
	
	
	
	//�j���p���s���������α�����ʧ@
    private class RadioButtonListener implements ItemListener{
    	private final int index;
    	public RadioButtonListener(int index){
    		this.index = index;
    	}
    	
    	public void itemStateChanged(ItemEvent event){
			if(event.getStateChange() == ItemEvent.SELECTED) {    		  
				switch(index) {
				  case 0: {
					  System.out.printf("��� �p����\n");
					  break;
				  }
				  case 1: {
					  System.out.printf("��� ������\n");
					  break;
				  }
				  case 2: {
					  System.out.printf("��� �j����\n");
					  break;
				  }
				}
			}
    	}
    }
    
    
    //��Ц�m����
    private class MouseTracker extends MouseMotionAdapter{
    	public void mouseMoved(MouseEvent event) {
    		statusBar.setText(String.format("���Ц�m : (%d, %d)", event.getX(), event.getY()));
    	}
    }
}
