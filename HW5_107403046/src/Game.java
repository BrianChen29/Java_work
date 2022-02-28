//��ޤTB 107403046 ���f�P

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JFrame{
	private JLabel[] Item = new JLabel[100];
	int mapArr[] = new int[100];
	String mapString = "";
	int total = 0;
	JPanel gamePanel = new JPanel();
	
	//���
	bloodPanel HP = new bloodPanel();
	
	public void draw(Graphics g) {
		g.drawLine(10,10,330,10);
	}
	
	public Game() {
		super("�q�y����");
		
		//Ū��
		try {
			File f = new File("src\\map.txt");
			Scanner s = new Scanner(f);
			
			//functional programming
			Arrays.stream(mapArr)
				  .map(mapArr -> Integer.parseInt(s.next()))
				  .forEach(mapArr -> decideIcon(mapArr));
			
			s.close();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
				

		gamePanel.setLayout(new GridLayout(10,10));
				
		Scanner s = new Scanner(mapString);
		
		//�P�_�n��J�ƻ�Ϥ��A�p�G�O�����random��1/5�����v�|�X�{�R�� �令functional programming
		/*while(s.hasNext()) {
			int num = Integer.parseInt(s.next());
			
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
					Item[total].addMouseListener(new MouseAdapter() {
						public void mouseEntered(MouseEvent event) {
							HP.gameover();
							repaint();
						}
					});
				}
				else {
					Item[total] = new JLabel("",getBrickwall(),SwingConstants.LEFT);
					//����
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
		}*/
		
		s.close();
		add(gamePanel, BorderLayout.CENTER);
		add(HP,BorderLayout.SOUTH);
	}
	
	
	//�R�߹Ϯ�
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
	
	//���Ϯ�
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
	
	//����Ϯ�
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
	
	//�p�۹Ϯ�
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
	
	public void decideIcon(int num) {
		
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
			int i = 0;
			
			//Functional Programing �����H���Ʀr�A�ͦ�����ηR��
			IntStream.of(i)
					 .map(value -> value = r.nextInt(6))
					 .forEach(value -> randomSelect(value));
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
			total ++;
	}
	
	public void randomSelect(int i) {
		if(i == 4) { 
			Item[total] = new JLabel("",getHeart(),SwingConstants.LEFT);
			
		}
		else if(i == 3) {
			Item[total] = new JLabel("",getGhost(),SwingConstants.LEFT);
			Item[total].addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent event) {
					HP.gameover();
					repaint();
				}
			});
		}
		else {
			Item[total] = new JLabel("",getBrickwall(),SwingConstants.LEFT);
			//��@����
			Item[total].addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					HP.blood(false);
					repaint();
				}
			});
		}
	}
}
