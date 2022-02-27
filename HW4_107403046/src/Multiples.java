//��ޤTB 107403046 ���f�P

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.swing.*;

public class Multiples extends JFrame{
	private JLabel inputQ,rangeQ;
	private JTextField input,range;
	private JButton okButton;

	
	public Multiples() {
		super("Enter all your values");
		
		JPanel topic = new JPanel();
		topic.setLayout(new GridLayout(4,1));
		
		inputQ = new JLabel("��J�n�䭿�ƪ��Ʀr:");
		topic.add(inputQ);
		input = new JTextField(10);
		topic.add(input);
		
		rangeQ = new JLabel("��J�d��, ��X�d�򤺪�����:");
		topic.add(rangeQ);
		range = new JTextField(10);
		topic.add(range);
		
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Set<Integer> hashSet = new HashSet<>();
				List<Integer> list = new LinkedList<>();
				
				try {
					int num =Integer.valueOf(input.getText());
					int rangeNum =Integer.valueOf(range.getText());
					
					for(int i=0;i<=rangeNum;i++) {
						if(i%num == 0 && i != 0) {
							hashSet.add(i);
						}
					}
					
					for(int i:hashSet) {
						list.add(i);
					}
					
					ListIterator<Integer> iterator = list.listIterator();
					Collections.sort(list);
					
					String showAns = "";
					if(!hashSet.isEmpty()) {
						showAns += iterator.next();
						while(iterator.hasNext()) {
							showAns += ", "+iterator.next();
						}
						
						JOptionPane.showMessageDialog(null,showAns,num+"�b"+rangeNum+"��������",JOptionPane.PLAIN_MESSAGE);
					}
					
					else if(rangeNum <= num) {
						JOptionPane.showMessageDialog(null,"�d���Ʀr�٤p",rangeNum+"��"+num+"�٤p",JOptionPane.PLAIN_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null,"�S���b���d�򪺭���");
					}
				}
				
				catch(NumberFormatException nfe) {
					System.out.println("�п�J�����");
				}
			}
		});
		
		JPanel bot = new JPanel();
		bot.add(okButton);
		
		
		add(topic,BorderLayout.NORTH);
		add(bot, BorderLayout.SOUTH);
		
		
	}//contructer
}
