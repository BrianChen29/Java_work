import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class Frame extends JFrame {

	private JPanel contentPane;
	private JTextField sx;
	private JTextField sy;
	private JTextField gx;
	private JTextField gy;
	private TileMap tileMap;
	private Map<Point, Integer> map;

	private final Point startPoint = new Point(1, 1);
	private final int WALL = 0;
	private final int ROAD = 1;
	private final int START = 2;
	private final int END = 3;
	private final int STEP = 4;

	private Frame thiss = this;
	private int mapWidth;
	private int mapHeight;

	public Frame() {
		ReadMap.openFile("src\\map.txt");
		ReadMap.readMap();
		ReadMap.closeFile();
		map = new HashMap<Point, Integer>(ReadMap.getRecordMap());

		mapWidth = ReadMap.getMapWidth();
		mapHeight = ReadMap.getMapHeight();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		
		JLabel start = new JLabel("選擇起點(x,y): ");
		panel.add(start);
		sx = new JTextField();
		sx.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(sx);
		sx.setColumns(2);

		sy = new JTextField();
		sy.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(sy);
		sy.setColumns(2);
		
		
		JLabel goal = new JLabel("選擇終點(x,y): ");
		panel.add(goal);

		gx = new JTextField();
		gx.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(gx);
		gx.setColumns(2);

		gy = new JTextField();
		gy.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(gy);
		gy.setColumns(2);

		JButton runButton = new JButton("run");
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int pointX = Integer.parseInt(gx.getText());
					int pointY = Integer.parseInt(gy.getText());
					if (!startPoint.equals(new Point(pointX, pointY)) && pointX >= 1 && pointX <= mapWidth
							&& pointY >= 1 && pointY <= mapHeight) {
						setMap(new Point(Integer.parseInt(gx.getText()), Integer.parseInt(gy.getText())));
						backtracking();
					}else {
						JOptionPane.showMessageDialog(thiss, "請輸入有效座標");
						gx.requestFocus();
						gx.setText("");
						gy.setText("");
					}
				} 
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(thiss, "請輸入正整數");
					gx.requestFocus();
					gx.setText("");
					gy.setText("");
					e.printStackTrace();
				}
			}
		});
		contentPane.add(runButton, BorderLayout.SOUTH);

		tileMap = new TileMap(map, ReadMap.getMapWidth(), ReadMap.getMapHeight());
		contentPane.add(tileMap, BorderLayout.CENTER);
	}

	private void setMap(Point end) {
		tileMap.map = new HashMap<Point, Integer>(map);
		tileMap.map.put(startPoint, START);
		tileMap.map.put(end, END);
		tileMap.repaint();
	}

	private void backtracking() {
		Map<Point, Integer> mapForRun = new HashMap<Point, Integer>(tileMap.map);
		Stack<Point> path = new Stack<Point>();
		path.push(startPoint);
		boolean isNotFound = true;

		Point lastStep = new Point(0, 0);
		while (isNotFound) {
			int x = path.peek().x;
			int y = path.peek().y;
			Point[] ways = { new Point(x, y - 1), new Point(x + 1, y), new Point(x, y + 1), new Point(x - 1, y) };
			boolean noWay = true;
			for (Point way : ways) {
				if (mapForRun.containsKey(way) && !lastStep.equals(way)) {
					if (mapForRun.get(way) == END) {
						path.push(way);
						isNotFound = false;
						break;
					}
				}
			}
			if (isNotFound) {
				for (Point way : ways) {
					if (mapForRun.containsKey(way) && !lastStep.equals(way)) {
						if (mapForRun.get(way) == ROAD) {
							lastStep = path.peek();
							path.push(way);
							noWay = false;
							break;
						}
					}
				}
			}
			if (noWay) {
				mapForRun.put(path.pop(), WALL);
			}
		}
		ArrayList<Point> donePath = new ArrayList<Point>();
		while (!path.isEmpty()) {
			donePath.add(path.pop());
		}
		for (int i = 0; i < donePath.size(); i++) {
			int lastIndex = donePath.lastIndexOf(donePath.get(i));
			if (lastIndex >= 0) {
				donePath.subList(i, lastIndex).clear();
			}
		}

		Thread thread = new Thread(() -> {
			try {
				for (int i = donePath.size() - 2; i >= 0; i--) {
					tileMap.map.put(donePath.get(i), STEP);
					tileMap.repaint();
					Thread.sleep(50);
				}
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}
}
