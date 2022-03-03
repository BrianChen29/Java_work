//資管三B 陳柏澔 107403046

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame extends JFrame{
	private final JPanel choosePanel;
	private final JLabel comboTitle;
	private final JComboBox<String> pokJComboBox;
	private final String[] pokimons = {"小火龍","傑尼龜","妙蛙種子"};
	private final JPanel buttonPanel;
	private final JButton okButton;
	private final JButton cancel;
	
	private Path path = Paths.get("src\\");
	
	public Frame() {
		super("大木博士");
		setLayout(new BorderLayout());
	
		choosePanel = new JPanel();
		choosePanel.setLayout(new GridLayout(2,1));
		
		comboTitle = new JLabel("選擇你的御三家");

		pokJComboBox = new JComboBox<String>(pokimons);
		pokJComboBox.setMaximumRowCount(3);
		
		choosePanel.add(comboTitle);
		choosePanel.add(pokJComboBox);
		
		//寶可夢JComboBox
		pokJComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				
				try {
					ObjectInputStream input = new ObjectInputStream(Files.newInputStream(path));
					PokeSerializable ps = (PokeSerializable) input.readObject();
					if(event.getStateChange() == ItemEvent.SELECTED) {
						ps.setMonster(pokimons[pokJComboBox.getSelectedIndex()]);
						System.out.printf("選擇 %s\n",pokJComboBox.getSelectedItem());
						
					}
		            
				}
				catch (IOException e) {
					System.err.println("IOException error");
				}
				catch (ClassNotFoundException cnfe) {
					System.err.println("ClassNotFoundException error");
				}

			}
		});
		
		buttonPanel = new JPanel();
		//確定按鈕
	    okButton = new JButton("確定");
	    buttonPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("點選 確定");
				
				Game game = new Game();
				game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				game.setSize(700,1000);
				game.setVisible(true);
				
			}
		});
		
		//取消按鈕
	    cancel = new JButton("取消");
	    buttonPanel.add(cancel);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(1);
			}
		});
		
		add(choosePanel,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.SOUTH);
	
	}
}
