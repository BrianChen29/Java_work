//資管三B 陳柏澔 107403046

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.*;

public class Game extends JFrame{

	
	private String monster = "小火龍";
	private String nickname;
	
	private final JPanel monsterPanel = new JPanel();
	private JLabel monsterIcon;
	private final ImageIcon[] sIcons = { 
		      new ImageIcon(getClass().getResource("f_s.png")),
		      new ImageIcon(getClass().getResource("w_s.png")), 
		      new ImageIcon(getClass().getResource("g_s.png"))};
	
	private  ImageIcon[] fireIcons = { 
		      new ImageIcon(getClass().getResource("f_s.png")),
		      new ImageIcon(getClass().getResource("f_m.png")), 
		      new ImageIcon(getClass().getResource("f_l.png")),
		      new ImageIcon(getClass().getResource("f_mega.png"))};
	private  ImageIcon[] waterIcons = { 
		      new ImageIcon(getClass().getResource("w_s.png")),
		      new ImageIcon(getClass().getResource("w_m.png")), 
		      new ImageIcon(getClass().getResource("w_l.png")),
		      new ImageIcon(getClass().getResource("w_mega.png"))};
	private  ImageIcon[] grassIcons = { 
		      new ImageIcon(getClass().getResource("g_s.png")),
		      new ImageIcon(getClass().getResource("g_m.png")), 
		      new ImageIcon(getClass().getResource("g_l.png")),
		      new ImageIcon(getClass().getResource("g_mega.png"))};
	
	private  int candy = 0;
	
	private final JPanel funcPanel;
	private final JTextField name;
	
	private final JPanel playPanel;
	private final JButton giveCandy;
	private final JLabel candyNum;
	private final JButton back;
	
	private final JPanel filePanel;
	private final JButton openGame;
	private final JButton saveButton;
	private final JButton saveasButton;
	
	
	private final JLabel statusbar;
	
	private Path path = Paths.get("src\\");
	private JFileChooser fileChooser = new JFileChooser();
	private static Scanner input;
	
	public Game() {
		//迎賓訊息
		super("寶可夢~~");

		try {
			ObjectInputStream input = new ObjectInputStream(Files.newInputStream(path));
			PokeSerializable ps = (PokeSerializable) input.readObject();
			
			
			switch(ps.getMonster()) {
			  case "小火龍":{
				  FileReader fr = new FileReader("src\\fire.txt");
				  Image image = sIcons[0].getImage();
				  monsterIcon = new JLabel(sIcons[0]);
				  monsterPanel.add(monsterIcon);
				  break;
			  }
			  case "傑尼龜":{
				  FileReader fr = new FileReader("src\\water.txt");
				  monsterIcon = new JLabel(sIcons[1]);
				  monsterPanel.add(monsterIcon);
				  break;
			  }
			  case "妙蛙種子":{
				  FileReader fr = new FileReader("src\\grass.txt");
				  monsterIcon = new JLabel(sIcons[2]);
				  monsterPanel.add(monsterIcon);
				  break;
			  }
			}
			
			nickname = ps.getNickname();
			candy = ps.getCandy();
			input.close();
		}
		catch (IOException e) {
			System.err.println("IOException error");
		}
		catch (ClassNotFoundException cnfe) {
			System.err.println("ClassNotFoundException error");
		}
		
		
		funcPanel = new JPanel();
		funcPanel.setLayout(new GridLayout(3,1));
		
		name = new JTextField("設置暱稱");
		funcPanel.add(name);
		
		playPanel = new JPanel();
		giveCandy = new JButton("經驗糖果");
		playPanel.add(giveCandy);
		giveCandy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				candy++;
			}
		});
		candyNum = new JLabel(candy+"/"+"3");
		playPanel.add(candyNum);
		back = new JButton("退化");
		playPanel.add(back);
		
		funcPanel.add(playPanel);
		
		
		filePanel = new JPanel();
		
		//OpenGame按鈕
		openGame = new JButton("Open Game");
		filePanel.add(openGame);
		openGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.CANCEL_OPTION) {
					System.exit(1);
				}

				Path choosePath = fileChooser.getSelectedFile().toPath();

				if (choosePath != null && Files.exists(choosePath)) {
					try {
						input = new Scanner(choosePath);
					} 
					catch (IOException ioex) {
						System.err.println("Error opening file. Terminating.");
						System.exit(1);
					}
				}
			}
		});
		
		
		// 儲存按鈕		
		saveButton = new JButton("Save");
		filePanel.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {

					ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(null));
					PokeSerializable ps = new PokeSerializable();
					output.writeObject(ps);
					output.flush();
					output.close();
					

				} 
				catch (IOException ioex) {
					System.err.println("Error IOException.");
				}
			}
		});
		
		
		// 另存內容		
		saveasButton = new JButton("Save as");
		filePanel.add(saveasButton);
		saveasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.CANCEL_OPTION) {
					System.exit(1);
				}
				if (fileChooser.getSelectedFile() != null) {
					try {
						ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(null));
						PokeSerializable ps = new PokeSerializable();
						FileWriter fw = new FileWriter(fileChooser.getSelectedFile());
						JOptionPane.showInputDialog(null,"請輸入檔名(需含副檔名):","訊息");
						//fw.write(ps.getNickname(),ps.getMonster(),ps.getCandy());
						fw.close();
					} catch (IOException ioex) {
						System.err.println("Error IOException.");
					}
				}
			}
		});
		
		funcPanel.add(filePanel);
		
		statusbar = new JLabel("New File");
		
		add(monsterPanel,BorderLayout.NORTH);
		add(funcPanel,BorderLayout.CENTER);
		add(statusbar,BorderLayout.SOUTH);

	}
}
