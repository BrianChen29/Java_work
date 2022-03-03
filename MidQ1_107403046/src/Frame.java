//資管三B 陳柏澔 107403046

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;


public class Frame extends JFrame{
	private final BorderLayout layout;
	private final JPanel controlPanel;
	private final Drawpanel drawPanel = new Drawpanel();
	private final JLabel statusBar;
	//排列方式 上方工具區 畫布區 下方狀態列
	
	private final JPanel inputPanel;
	//左半邊inputPanel
	private final JLabel lefttopTitle;
	private final JTextField x,y;
	private final JLabel borderTitle;
	private final JTextField width,height;
	private final JLabel angleTitle;
	private final JTextField angWid,angHei;
	
	private final JPanel getDotPanel;
	private final JButton getDot;
	
	private final JPanel doPanel;
	
	private final JLabel colorTitle;
	private final JButton colorButton;
	private Color color;
	private Color bgColor = Color.WHITE;
	
	private final JButton drawButton;
	private final JButton undo;
	

	
	public Frame() {
		super("小畫家");
		
		//排列方式
		setLayout(layout = new BorderLayout());
		
		//上方控制區
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		
		//左1輸入區
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(3,3));
		
		//第一行 左上座標
		lefttopTitle = new JLabel("左上座標:");
		x = new JTextField(10);
		y = new JTextField(10);
		inputPanel.add(lefttopTitle);
		inputPanel.add(x);
		inputPanel.add(y);
		
		//第二行 圖形長寬
		borderTitle = new JLabel("圖形長寬");
		width = new JTextField(10);
		height = new JTextField(10);
		inputPanel.add(borderTitle);
		inputPanel.add(width);
		inputPanel.add(height);
		
		//第三行 圖角長寬
		angleTitle = new JLabel("圖角長寬");
		angWid = new JTextField(10);
		angHei = new JTextField(10);
		inputPanel.add(angleTitle);
		inputPanel.add(angWid);
		inputPanel.add(angHei);
		
		controlPanel.add(inputPanel);
		
		getDotPanel = new JPanel();
		getDotPanel.setLayout(new GridLayout(3,1));
		//取點按鈕
		getDot = new JButton("取點");
		getDotPanel.add(getDot);
		getDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("點選 取點");
				JOptionPane.showMessageDialog(null,"取點開始","訊息",JOptionPane.PLAIN_MESSAGE);
				
				MouseHandler mouseXY = new MouseHandler();
				drawPanel.addMouseMotionListener(mouseXY);
				
				int x1 = drawPanel.getX();
				int y1 = drawPanel.getY();
				int w = Integer.valueOf(width.getText());
				int h = Integer.valueOf(height.getText());
				int angW = Integer.valueOf(angWid.getText());
				int angH = Integer.valueOf(angHei.getText());
				JOptionPane.showMessageDialog(null,"取點成功","訊息",JOptionPane.PLAIN_MESSAGE);
				drawPanel.draw(x1, y1, w, h, angW, angH);

			}
		});
		
		controlPanel.add(getDotPanel);
		
		//右區
		doPanel = new JPanel();
		doPanel.setLayout(new GridLayout(2,2));
		
		
		colorTitle = new JLabel("顏色:");
		doPanel.add(colorTitle);
		//顏色  button
		colorButton = new JButton("筆刷顏色");
		doPanel.add(colorButton);
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("點選 筆刷顏色");
				//顯示調色盤
				color = JColorChooser.showDialog(Frame.this, "選擇顏色", color);
				if(color == null) {
					color = Color.black;
				}
				drawPanel.setColor(color);
			}
		});
		
		
		//繪製圖形 button
		drawButton = new JButton("繪製圖形");
		doPanel.add(drawButton);
		drawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("點選 繪製圖形");
				
				if(x.notifyAction.isEmpty()) {
					JOptionPane.showMessageDialog(null,"請確定所有欄位都有填入數字","訊息",JOptionPane.PLAIN_MESSAGE);
				}
				else {
					int x1 = Integer.valueOf(x.getText());
					int y1 = Integer.valueOf(y.getText());
					int w = Integer.valueOf(width.getText());
					int h = Integer.valueOf(height.getText());
					int angW = Integer.valueOf(angWid.getText());
					int angH = Integer.valueOf(angHei.getText());
					drawPanel.draw(x1,y1,w,h,angW,angH);}
			}
			
		});
		
		
		//上一步 button
		undo = new JButton("上一步");
		doPanel.add(undo);
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("點選 上一步");
				
				drawPanel.undo();
			}
		});
		
		
		controlPanel.add(doPanel);
		
		
		drawPanel.setBackground(Color.WHITE);//畫布顏色:白
		
		statusBar = new JLabel("指標位置");//狀態列
		
		MouseTracker tracker = new MouseTracker();
		drawPanel.addMouseMotionListener(tracker);
		//游標位置偵測
		
		
		add(controlPanel, BorderLayout.NORTH);//上方控制區
		add(drawPanel, BorderLayout.CENTER);//中間畫布區
		add(statusBar, BorderLayout.SOUTH);//下方狀態列
		
		
	}//constucter
	
	//取點
	private class MouseHandler extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent event) {
			int x1 = event.getX();
			int y1 = event.getY();
			drawPanel.setXY(x1, y1);
		}
		
	}
	
    //游標位置偵測
    private class MouseTracker extends MouseMotionAdapter{
    	public void mouseMoved(MouseEvent event) {
    		statusBar.setText(String.format("指標位置 : (%d, %d)", event.getX(), event.getY()));
    	}
    	
    	public void mouseExited(MouseEvent event) {
    		statusBar.setText("指標位置 : 畫布外");
    	}
    }
	
}
