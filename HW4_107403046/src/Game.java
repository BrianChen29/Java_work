//資管三B 107403046 陳柏澔

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JFrame{
	private JLabel[] Item = new JLabel[100];
	String mapString = "";
	
	//血條
	bloodPanel HP = new bloodPanel();
	
	public void draw(Graphics g) {
		g.drawLine(10,10,330,10);
	}
	
	public Game() {
		super("電流急急棒");
		
		//讀檔
		try {
			FileReader fr = new FileReader("src\\map.txt");
			BufferedReader br = new BufferedReader(fr);
					
			while(br.ready()) {
				mapString += br.readLine()+"\n";
			}
			fr.close();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
				
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(10,10));
				
		Scanner s = new Scanner(mapString);
		
		//判斷要放入甚麼圖片，如果是牆壁用random有1/5的機率會出現愛心
		while(s.hasNext()) {
			int num = Integer.parseInt(s.next());
			int total = 0;
			
			if(num == 0) {
				Item[total] = new JLabel("");
				
				Item[total].addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent event) {
						HP.blood(true);
						repaint();
					}
				});
			}
			else if(num == 1) {
				Random r = new Random();
				int i = r.nextInt(5);
				if(i == 4) {
					Item[total] = new JLabel("",getHeart(),SwingConstants.LEFT);
				}
				else if(i == 3) {
					Item[total] = new JLabel("",getGhost(),SwingConstants.LEFT);
				}
				else {
					Item[total] = new JLabel("",getBrickwall(),SwingConstants.LEFT);
					//扣血
					Item[total].addMouseListener(new MouseAdapter() {
						public void mouseEntered(MouseEvent event) {
							HP.blood(false);
							repaint();
						}
					});
				}
			}
			else if(num == 2) {
				Item[total] = new JLabel("",getDiamond(),SwingConstants.LEFT);
				if(HP.getBlood() > 0 ) {
					Item[total].addMouseListener(new MouseAdapter() {
						public void mouseEntered(MouseEvent event) {
							System.out.println("Win!");
							System.exit(1);
						}
					});
				}
			}
			
			gamePanel.add(Item[total]);
			total++;
		}
		
		s.close();
		add(gamePanel, BorderLayout.CENTER);
		add(HP,BorderLayout.SOUTH);
	}
	
	
	//愛心圖案
	public static ImageIcon getHeart() {
		try {
			ImageIcon heart = new ImageIcon(ImageIO.read(new File("src\\heart.png")));
			Image image = heart.getImage();
			Image setting = image.getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
			heart = new ImageIcon(setting);
			
			return heart;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//鬼圖案
	public static ImageIcon getGhost() {
		try {
			ImageIcon ghost = new ImageIcon(ImageIO.read(new File("src\\ghost.png")));
			Image image = ghost.getImage();
			Image setting = image.getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
			ghost = new ImageIcon(setting);
			
			return ghost;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//牆壁圖案
	public static ImageIcon getBrickwall() {
		try {
			ImageIcon brickwall = new ImageIcon(ImageIO.read(new File("src\\brickwall.png")));
			Image image = brickwall.getImage();
			Image setting = image.getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
			brickwall = new ImageIcon(setting);
			
			return brickwall;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//鑽石圖案
	public static ImageIcon getDiamond() {
		try {
			ImageIcon diamond = new ImageIcon(ImageIO.read(new File("src\\diamond.png")));
			Image image = diamond.getImage();
			Image setting = image.getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
			diamond = new ImageIcon(setting);
			
			return diamond;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
