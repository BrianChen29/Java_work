//��ޤTB ���f�P 107403046

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;



public class Drawpanel extends JPanel{
	private int x1,y1;
	private int x2,y2;
	private Graphics2D g2d;
	private Color defaultColor = Color.black;
	protected BufferedImage image;
	private int size;
	private List<Point> pointList = new ArrayList<Point>();
	private static ArrayList<BufferedImage> historic = new ArrayList<BufferedImage>();
	private int historicIndex = 0;
	
	
	public Drawpanel() {
		image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
		addMouseListener(new MouseHandler());
		size=5;
	}//constructer
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics G = image.createGraphics();
		g2d = (Graphics2D) G;
		
		g2d.setPaint(defaultColor);
		g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
		
		g.drawImage(image, 0, 0, this);
	}
	
	//�վ�j�p
	public void setSize(int size) {
		this.size = size;
	}
	

	//�C��
	public void setColor(Color color) {
		this.defaultColor = color;
	}
	
	//�]�wXY�y��(���I)
	public void setXY(int x, int y) {
		this.x1 = x;
		this.y1 = y;
	}
	
	//�I�sX�y��
	public int getX() {
		return this.x1;

	}
	
	//�I�sY�y��
	public int getY() {
		return this.y1;
	}
	
	//ø�s�ϧ�
	public void draw(int x1,int y1, int width, int height, int angW, int angH) {
		g2d.draw(new RoundRectangle2D.Double(Math.min(x1, (x1+width)), Math.min(y1, (y1+height)), width, height,angW,angH));
		repaint();
	}
	
	
	//�W�@�B
	public void undo() {
		if(historicIndex > 0) {
			historicIndex -= 1;
			image = historic.get(historicIndex);
			Graphics2D g2d = image.createGraphics();
			g2d.drawImage(image,0,0,2000,2000,this);
		}
		repaint();
	}
	
	//�@�e �Q�ηƹ������Ĥ@�U(Pressed)�γ̫�@�U(Released)
	private class MouseHandler extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent event) {
			x1 = event.getX();
			y1 = event.getY();
		}
	
	
		public void mouseReleased(MouseEvent event) {
			x2 = event.getX();
			y2 = event.getY();
			g2d.setPaint(defaultColor);
			g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
			
			
			g2d.draw(new RoundRectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2),20,20));
			historic.add(historicIndex, image);
			historicIndex ++;
			
			repaint();
		}
		
	}
}
