//陳柏澔 107403046 資管三B

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
	
	//調整大小
	public void setSize(int size) {
		this.size = size;
	}
	
	//調整工具
	public void setTool(int tools) {
		this.tools = tools;
	}
	
	//填滿
	public void setFill(boolean fill) {
		this.fill = fill;
	}

	//顏色
	public void setColor(Color color) {
		this.defaultColor = color;
	}
	
	//呼叫 大小
	public int getsize() {
		return size;
	}
	
	//呼叫 工具
	public int getTool() {
		return tools;
	}
	
	//清除畫面
	public void clear() {
		image = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_ARGB_PRE);
		repaint();
	}
	
	//作畫 利用滑鼠按的第一下(Pressed)及最後一下(Released) 筆刷以外的工具部分
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
	
	//作畫 筆刷部分
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
