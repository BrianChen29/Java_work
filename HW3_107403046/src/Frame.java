//資管三B 107403046 陳柏澔

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

public class Frame extends JFrame{
	private int checkUser;
	private static String passwrd = "123";
	private boolean pass = false;
	
	private JPanel titleBoard = new JPanel();
	private JLabel weatherLine, timeLine, title = new JLabel("聯絡簿");
	private int weather;
	private Icon sunny, cloudy, rainy;
	private JPanel weatherPanel = new JPanel();
	private OverlayLayout weatherLayout = new OverlayLayout(weatherPanel);
	private JComboBox<String> weatherJComboBox;
	private static final String[] weathers = {"晴天","陰天","雨天"};
	private final Icon[] weatherIcons = { 
			      new ImageIcon(getClass().getResource("sunny.png")),
			      new ImageIcon(getClass().getResource("cloudy.png")), 
			      new ImageIcon(getClass().getResource("rainy.png"))};
	
	private Path path = Paths.get("src\\post");
	private Date editTime = new Date();
	
	private JPanel contentBoard = new JPanel();
	private Color bgColor = new Color(30, 70, 36);
	private String content;
	private JTextArea text, textarea = new JTextArea(content, 13, 58);
	
	
	private JPanel funcBoard = new JPanel();
	private BoxLayout funcLayout = new BoxLayout(funcBoard, BoxLayout.X_AXIS);
	
	private ImageIcon likeIcon,unlikeIcon;
	private JButton likeButton = new JButton();
	private int click;// =0 unlike   =1 like
	private boolean isLike = false;
	
	// 可編輯模式 按鈕
	private JButton editButton = new JButton("編輯");
	private JButton newButton = new JButton("全新貼文");
	private JPanel buttonPanel = new JPanel();
	
	// 編輯模式 按鈕
	private JButton saveButton = new JButton("儲存");
	private JButton saveasButton = new JButton("另存內容");
	private JButton importButton = new JButton("匯入內容");
	private JButton cancelButton = new JButton("取消");
	
	
	private JFileChooser fileChooser = new JFileChooser();
	private static Scanner input;
	
	
	public Frame() {
		checkUser = JOptionPane.showConfirmDialog(null,"是否為發布者","聯絡簿",JOptionPane.YES_NO_CANCEL_OPTION);
		if(checkUser == 0) {
			String passwrd = JOptionPane.showInputDialog("請輸入密碼: ");
			if (this.passwrd.contentEquals(passwrd)) {
				JOptionPane.showMessageDialog(this, "發布者模式");
				pass = true;
			}
			else {
				JOptionPane.showMessageDialog(this, "訪客模式");
				pass = false;
			}

		}
		else if(checkUser == 1) {
			JOptionPane.showMessageDialog(this, "訪客模式");
		}
		else {
			System.exit(1);
		}
		
		try {
			editTime.setTime(Files.getLastModifiedTime(path).toMillis());
			
			ObjectInputStream input = new ObjectInputStream(Files.newInputStream(path));
			PostSerializable ps = (PostSerializable) input.readObject();
			
			timeLine = new JLabel(ps.getEditTime().toString());
			
			
			if (ps.getWeather() == 0) {
				weatherLine = new JLabel("天氣", weatherIcons[0], SwingConstants.LEFT);
				weatherLine.setHorizontalTextPosition(SwingConstants.LEFT);
			}
			else if(ps.getWeather() == 1) {
				weatherLine = new JLabel("天氣", weatherIcons[1], SwingConstants.LEFT);
				weatherLine.setHorizontalTextPosition(SwingConstants.LEFT);
			}
			else if(ps.getWeather() == 2) {
				weatherLine = new JLabel("天氣", weatherIcons[2], SwingConstants.LEFT);
				weatherLine.setHorizontalTextPosition(SwingConstants.LEFT);
			}
			
			isLike = ps.getIsLike();
			text = new JTextArea(ps.getContent(), 13, 58);
			content = ps.getContent();
			input.close();
		}
		catch (IOException e) {
			System.err.println("IOException error");
		}
		catch (ClassNotFoundException cnfe) {
			System.err.println("ClassNotFoundException error");
		}
		
		// 最上面標題+天氣+時間 字型顏色設定
		title.setFont(new Font("Dialog", 1, 48));
		title.setForeground(Color.WHITE);
		weatherLine.setFont(new Font("Dialog", 1, 32));
		weatherLine.setForeground(Color.WHITE);
		timeLine.setFont(new Font("Dialog", 1, 20));
		timeLine.setForeground(Color.WHITE);
		
		
		// 編輯模式 選擇天氣JCombobox
		weatherJComboBox = new JComboBox<String>(weathers);
		weatherJComboBox.setMaximumRowCount(3);
		weatherJComboBox.setVisible(false);
		weatherJComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event)
			{
				try {
					ObjectInputStream input = new ObjectInputStream(Files.newInputStream(path));
					PostSerializable ps = (PostSerializable) input.readObject();
					ps.setWeather(weatherJComboBox.getSelectedIndex());
		            if (event.getStateChange() == ItemEvent.SELECTED) {
		            	weatherLine.setIcon(weatherIcons[weatherJComboBox.getSelectedIndex()]);
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
		

		// 天氣panel放入weatherline跟JCombobox
		weatherPanel.setBackground(bgColor);
		weatherPanel.add(weatherLine);
		weatherPanel.add(weatherJComboBox);	
		weatherLine.setAlignmentX(LEFT_ALIGNMENT);
		weatherJComboBox.setAlignmentX(LEFT_ALIGNMENT);
		
		// panel背景色排序設定 label加進panel 
		titleBoard.setBackground(bgColor);
		titleBoard.setLayout(new BorderLayout());
		titleBoard.add(title,BorderLayout.NORTH);
		titleBoard.add(weatherPanel,BorderLayout.WEST);
		titleBoard.add(timeLine,BorderLayout.SOUTH);
		
		// 中間文字設定
		text.setForeground(Color.yellow);
		text.setFont(new Font("Dialog", 1, 18));
		text.setOpaque(false);
		text.setBorder(BorderFactory.createEmptyBorder());
		text.setEditable(false);
		
		// 編輯文字區塊
		textarea.setFont(new Font("Dialog", 1, 18));
		textarea.setOpaque(false);
		textarea.setBorder(BorderFactory.createEmptyBorder());
		
		// 中間內容設定
		contentBoard.setBackground(bgColor);
		contentBoard.add(text);
		contentBoard.add(textarea);
		
		
		// 設定愛心圖片
		likeIcon = new ImageIcon(getClass().getResource("like.png"));
		unlikeIcon = new ImageIcon(getClass().getResource("unlike.png"));
		Image likeImage = likeIcon.getImage();
		Image unlikeImage = unlikeIcon.getImage();
		likeImage = likeImage.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		unlikeImage = unlikeImage.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		likeIcon = new ImageIcon(likeImage);
		unlikeIcon = new ImageIcon(unlikeImage);
		
		// 愛心按鈕
		likeButton.setBorder(null);
		likeButton.setContentAreaFilled(false);
		likeButton.setOpaque(false);
		likeButton.setIcon(unlikeIcon);

		likeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (click == 0) {
					likeButton.setIcon(likeIcon);
					click = 1;
				} 
				else if(click == 1){
					likeButton.setIcon(unlikeIcon);
					click = 0;
				}
			}
		});
		
		
		
		// 編輯按鈕
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				weatherLine.setVisible(false);
				weatherJComboBox.setVisible(true);
				

				text.setVisible(false);
				textarea.setVisible(true);
				contentBoard.setBackground(Color.WHITE);
				textarea.setEditable(true);
				textarea.setText(content);

				likeButton.setVisible(false);
				editButton.setVisible(false);
				newButton.setVisible(false);
				saveButton.setVisible(true);
				saveasButton.setVisible(true);
				importButton.setVisible(true);
				cancelButton.setVisible(true);
				funcBoard.setLayout(funcLayout); //進入編輯以後按鈕要在左邊 還沒弄好 現在會靠右
				funcBoard.setAlignmentX(LEFT_ALIGNMENT);

			}
		});
		
		// 全新貼文按紐
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				weatherJComboBox.setVisible(true);
				weatherLine.setVisible(false);
				
				text.setVisible(false);
				textarea.setVisible(true);
				contentBoard.setBackground(Color.WHITE);
				textarea.setEditable(true);
				textarea.setText("");
				
				likeButton.setVisible(false);
				editButton.setVisible(false);
				newButton.setVisible(false);
				saveButton.setVisible(true);
				saveasButton.setVisible(true);
				importButton.setVisible(true);
				cancelButton.setVisible(true);

			}
		});
		
		
		
		// 下面加編輯 全新貼文按鈕
		buttonPanel.add(editButton);
		buttonPanel.add(newButton);
		
		
		// 發布者鎖定愛心按紐
		if (pass) {
			likeButton.setEnabled(false);
		} else {
			likeButton.setEnabled(true);
		}

		
		// 下半部分 
		funcBoard.setBackground(new Color(96,56,17));
		//funcBoard.setSize(1024, 300);
		funcBoard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		// 把四個功能按鈕加入下方功能欄位 讓他們剛開始不顯示
		funcBoard.add(saveButton,Component.LEFT_ALIGNMENT);
		funcBoard.add(saveasButton,Component.LEFT_ALIGNMENT);
		funcBoard.add(importButton,Component.LEFT_ALIGNMENT);
		funcBoard.add(cancelButton,Component.LEFT_ALIGNMENT);
		saveButton.setVisible(false);
		saveasButton.setVisible(false);
		importButton.setVisible(false);
		cancelButton.setVisible(false);
		
		//加入愛心按紐
		funcBoard.add(likeButton);
		
		//funcBoard的layout manager
		funcBoard.setLayout(funcLayout);
		
		// 發布者顯示"編輯" "全新貼文" 按紐
		if (pass) {
			funcBoard.add(Box.createHorizontalGlue());
			funcBoard.add(editButton);
			funcBoard.add(newButton);
		}
		
		
		
		// 儲存按鈕
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					editTime.setTime(Files.getLastModifiedTime(path).toMillis());
					timeLine.setText(editTime.toString());
					
					content = textarea.getText();
					ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(path));
					PostSerializable ps = new PostSerializable(content,isLike, editTime);
					output.writeObject(ps);
					output.flush();
					output.close();
					
					
					text.setText(ps.getContent());

					FileWriter fw = new FileWriter("src\\post.txt");
					fw.write(textarea.getText());
					fw.close();

				} 
				catch (IOException ioex) {
					System.err.println("Error IOException.");
				}

				weatherJComboBox.setVisible(false);
				weatherLine.setVisible(true);
				
				likeButton.setVisible(true);
				editButton.setVisible(true);
				newButton.setVisible(true);
				saveButton.setVisible(false);
				saveasButton.setVisible(false);
				importButton.setVisible(false);
				cancelButton.setVisible(false);

				textarea.setVisible(false);
				text.setVisible(true);
				contentBoard.setBackground(bgColor);
			}
		});
		
		
		// 另存內容
		saveasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.CANCEL_OPTION) {
					System.exit(1);
				}
				if (fileChooser.getSelectedFile() != null) {
					try {
						FileWriter fw = new FileWriter(fileChooser.getSelectedFile());
						fw.write(textarea.getText());
						fw.close();
					} catch (IOException ioex) {
						System.err.println("Error IOException.");
					}
				}
			}
		});
		
		
		// 匯入內容
		importButton.addActionListener(new ActionListener() {
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
					} catch (IOException ioex) {
						System.err.println("Error opening file. Terminating.");
						System.exit(1);
					}
				}
				String importSring = "";
				try {
					while (input.hasNext()) {
						importSring += input.nextLine() + "\n";
						textarea.setText(importSring);
					}
				} catch (NoSuchElementException nseex) {
					System.err.println("File improperly formed. Terminating.");
				} catch (IllegalStateException isex) {
					System.err.println("Error reading from file. Terminating.");
				}

			}
		});
		
		
		// 取消設定
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				weatherJComboBox.setVisible(false);
				weatherLine.setVisible(true);
				
				likeButton.setVisible(true);
				editButton.setVisible(true);
				newButton.setVisible(true);
				saveButton.setVisible(false);
				saveasButton.setVisible(false);
				importButton.setVisible(false);
				cancelButton.setVisible(false);
				
				try {
					ObjectInputStream input = new ObjectInputStream(Files.newInputStream(path));
					PostSerializable ps = (PostSerializable) input.readObject();
					
					timeLine = new JLabel(ps.getEditTime().toString());
					
					isLike = ps.getIsLike();
					content = ps.getContent();
					input.close();
				}
				catch (IOException e) {
					System.err.println("IOException error");
				}
				catch (ClassNotFoundException cnfe) {
					System.err.println("ClassNotFoundException error");
				}

				textarea.setVisible(false);
				text.setVisible(true);
				contentBoard.setBackground(bgColor);
			}
		});
		
		
		// 排列三塊Board的位置
		add(titleBoard, BorderLayout.NORTH);
		add(contentBoard, BorderLayout.CENTER);
		add(funcBoard, BorderLayout.SOUTH);
		
	}//constructer
	
}
