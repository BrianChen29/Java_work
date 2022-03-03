//��ޤTB ���f�P 107403046

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
	//�ƦC�覡 �W��u��� �e���� �U�説�A�C
	
	private final JPanel inputPanel;
	//���b��inputPanel
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
		super("�p�e�a");
		
		//�ƦC�覡
		setLayout(layout = new BorderLayout());
		
		//�W�豱���
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		
		//��1��J��
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(3,3));
		
		//�Ĥ@�� ���W�y��
		lefttopTitle = new JLabel("���W�y��:");
		x = new JTextField(10);
		y = new JTextField(10);
		inputPanel.add(lefttopTitle);
		inputPanel.add(x);
		inputPanel.add(y);
		
		//�ĤG�� �ϧΪ��e
		borderTitle = new JLabel("�ϧΪ��e");
		width = new JTextField(10);
		height = new JTextField(10);
		inputPanel.add(borderTitle);
		inputPanel.add(width);
		inputPanel.add(height);
		
		//�ĤT�� �Ϩ����e
		angleTitle = new JLabel("�Ϩ����e");
		angWid = new JTextField(10);
		angHei = new JTextField(10);
		inputPanel.add(angleTitle);
		inputPanel.add(angWid);
		inputPanel.add(angHei);
		
		controlPanel.add(inputPanel);
		
		getDotPanel = new JPanel();
		getDotPanel.setLayout(new GridLayout(3,1));
		//���I���s
		getDot = new JButton("���I");
		getDotPanel.add(getDot);
		getDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("�I�� ���I");
				JOptionPane.showMessageDialog(null,"���I�}�l","�T��",JOptionPane.PLAIN_MESSAGE);
				
				MouseHandler mouseXY = new MouseHandler();
				drawPanel.addMouseMotionListener(mouseXY);
				
				int x1 = drawPanel.getX();
				int y1 = drawPanel.getY();
				int w = Integer.valueOf(width.getText());
				int h = Integer.valueOf(height.getText());
				int angW = Integer.valueOf(angWid.getText());
				int angH = Integer.valueOf(angHei.getText());
				JOptionPane.showMessageDialog(null,"���I���\","�T��",JOptionPane.PLAIN_MESSAGE);
				drawPanel.draw(x1, y1, w, h, angW, angH);

			}
		});
		
		controlPanel.add(getDotPanel);
		
		//�k��
		doPanel = new JPanel();
		doPanel.setLayout(new GridLayout(2,2));
		
		
		colorTitle = new JLabel("�C��:");
		doPanel.add(colorTitle);
		//�C��  button
		colorButton = new JButton("�����C��");
		doPanel.add(colorButton);
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("�I�� �����C��");
				//��ܽզ�L
				color = JColorChooser.showDialog(Frame.this, "����C��", color);
				if(color == null) {
					color = Color.black;
				}
				drawPanel.setColor(color);
			}
		});
		
		
		//ø�s�ϧ� button
		drawButton = new JButton("ø�s�ϧ�");
		doPanel.add(drawButton);
		drawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("�I�� ø�s�ϧ�");
				
				if(x.notifyAction.isEmpty()) {
					JOptionPane.showMessageDialog(null,"�нT�w�Ҧ���쳣����J�Ʀr","�T��",JOptionPane.PLAIN_MESSAGE);
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
		
		
		//�W�@�B button
		undo = new JButton("�W�@�B");
		doPanel.add(undo);
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("�I�� �W�@�B");
				
				drawPanel.undo();
			}
		});
		
		
		controlPanel.add(doPanel);
		
		
		drawPanel.setBackground(Color.WHITE);//�e���C��:��
		
		statusBar = new JLabel("���Ц�m");//���A�C
		
		MouseTracker tracker = new MouseTracker();
		drawPanel.addMouseMotionListener(tracker);
		//��Ц�m����
		
		
		add(controlPanel, BorderLayout.NORTH);//�W�豱���
		add(drawPanel, BorderLayout.CENTER);//�����e����
		add(statusBar, BorderLayout.SOUTH);//�U�説�A�C
		
		
	}//constucter
	
	//���I
	private class MouseHandler extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent event) {
			int x1 = event.getX();
			int y1 = event.getY();
			drawPanel.setXY(x1, y1);
		}
		
	}
	
    //��Ц�m����
    private class MouseTracker extends MouseMotionAdapter{
    	public void mouseMoved(MouseEvent event) {
    		statusBar.setText(String.format("���Ц�m : (%d, %d)", event.getX(), event.getY()));
    	}
    	
    	public void mouseExited(MouseEvent event) {
    		statusBar.setText("���Ц�m : �e���~");
    	}
    }
	
}
