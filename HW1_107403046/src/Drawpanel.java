//���f�P 107403046 ��ޤTB

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Drawpanel extends JPanel{
	private int x1,y1;
	private int x2,y2;
	private Graphics2D g2d;
	private Color defaultColor = Color.black;
	private int size, tools;
	private boolean fill;
	private BufferedImage image;
	
	public Drawpanel() {
		addMouseMotionListener(new MouseMotionHandler());
		addMouseListener(new MouseHandler());
		size = 2;
		tools = 0;
		fill = false;
	
	}//constructer
	
	//�վ�j�p
	public void setSize(int size) {
		this.size = size;
	}
	
	//�վ�u��
	public void setTool(int tools) {
		this.tools = tools;
	}
	
	//��
	public void setFill(boolean fill) {
		this.fill = fill;
	}

	//�C��
	public void setColor(Color color) {
		this.defaultColor = color;
	}
	
	//�I�s �j�p
	public int getsize() {
		return size;
	}
	
	//�I�s �u��
	public int getTool() {
		return tools;
	}
	
	//�M���e��
	public void clear() {
		image = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_ARGB_PRE);
		repaint();
	}
	
	//�@�e �Q�ηƹ������Ĥ@�U(Pressed)�γ̫�@�U(Released) ����H�~���u�㳡��
	private class MouseHandler extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent event) {
			switch(tools) {
			  case 0: {
				x1 = event.getX();
				y1 = event.getY();
				g2d.setPaint(defaultColor);
				g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
				break;
			  }
			  case 1: {
				x1 = event.getX();
				y1 = event.getY();
				break;
			  }
			  case 2: {
				x1 = event.getX();
				y1 = event.getY();
				break;
			  }
			  case 3: {
				x1 = event.getX();
				y1 = event.getY();
				break;
			  }
			  case 4: {
				x1 = event.getX();
				y1 = event.getY();
				break;
			  }
		
			}
		}
	
		public void mouseReleased(MouseEvent event) {
			switch(tools) {
				case 0: { break; }
				case 1: {
					x2 = event.getX();
					y2 = event.getY();
					g2d.setPaint(defaultColor);
					if(fill) {
						g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
						g2d.drawLine(x1, y1, x2, y2);
					}
					else {
						if(size == 2) {
							g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, new float[] {21f, 5f}, 0f));
						}
						else if(size == 10) {
							g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, new float[] {21f, 15f}, 0f));
							g2d.drawLine(x1, y1, x2, y2);
						}
						else if(size == 30) {
							g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, new float[] {21f, 50f}, 0f));
							g2d.drawLine(x1,y1,x2,y2);
						}
					}
					repaint();
					break;
				}
				case 2: {
					x2 = event.getX();
					y2 = event.getY();
					g2d.setPaint(defaultColor);
					g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
					if(fill) {
						g2d.fill(new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2)));
					}
					else {
						g2d.draw(new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2)));
					}
					repaint();
					break;
				}
				case 3: {
					x2 = event.getX();
					y2 = event.getY();
					g2d.setPaint(defaultColor);
					g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
					if(fill) {
						g2d.fill(new Rectangle.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2)));
					}
					else {
						g2d.draw(new Rectangle.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2)));
					}
					repaint();
					break;
				}
				case 4: {
					x2 = event.getX();
					y2 = event.getY();
					g2d.setPaint(defaultColor);
					g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
					if(fill) {
						g2d.fill(new RoundRectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2),50,50));
					}
					else {
						g2d.draw(new RoundRectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2),50,50));
					}
					repaint();
					break;
				}
			}
		}
	}
	
	//�@�e ���곡��
	private class MouseMotionHandler extends MouseMotionAdapter{
		@Override
		public void mouseDragged(MouseEvent event) {
			switch(tools) {
			case 0: {
				x2 = event.getX();
				y2 = event.getY();
				g2d.drawLine(x1, y1, x2, y2);
				repaint();
				x1 = x2;
				y1 = y2;
				break;
			}
			case 1: { break; }
			case 2: { break; }
			case 3: { break; }
			case 4: { break; }
			}
		}
	}
}
