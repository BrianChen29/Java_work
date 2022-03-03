//��ޤTB ���f�P 107403046

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
	private final String[] pokimons = {"�p���s","�ǥ��t","����ؤl"};
	private final JPanel buttonPanel;
	private final JButton okButton;
	private final JButton cancel;
	
	private Path path = Paths.get("src\\");
	
	public Frame() {
		super("�j��դh");
		setLayout(new BorderLayout());
	
		choosePanel = new JPanel();
		choosePanel.setLayout(new GridLayout(2,1));
		
		comboTitle = new JLabel("��ܧA���s�T�a");

		pokJComboBox = new JComboBox<String>(pokimons);
		pokJComboBox.setMaximumRowCount(3);
		
		choosePanel.add(comboTitle);
		choosePanel.add(pokJComboBox);
		
		//�_�i��JComboBox
		pokJComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				
				try {
					ObjectInputStream input = new ObjectInputStream(Files.newInputStream(path));
					PokeSerializable ps = (PokeSerializable) input.readObject();
					if(event.getStateChange() == ItemEvent.SELECTED) {
						ps.setMonster(pokimons[pokJComboBox.getSelectedIndex()]);
						System.out.printf("��� %s\n",pokJComboBox.getSelectedItem());
						
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
		//�T�w���s
	    okButton = new JButton("�T�w");
	    buttonPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("�I�� �T�w");
				
				Game game = new Game();
				game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				game.setSize(700,1000);
				game.setVisible(true);
				
			}
		});
		
		//�������s
	    cancel = new JButton("����");
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
