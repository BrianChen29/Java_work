import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;

public class mousePanel extends JPanel{
	private int buttonselected = 1;
	private Fish[] totalfish = new Fish[100];
	private Turtle[] totalturtle = new Turtle[50];
	private int fishCount = 0;
	private int turtleCount = 0;
	private ExecutorService executorService = Executors.newCachedThreadPool();
	private JLabel statusLabel;
	
	private int fishCounting = 0;
	private int turtleCounting = 0;
	
	private FishThread[] fishThread = new FishThread[100];
	private TurtleThread[] turtleThread = new TurtleThread[50];
	
	public mousePanel(JLabel status) {
		MouseHandler mouseHandler = new MouseHandler();
		addMouseListener(mouseHandler);
		statusLabel = status;
		statusLabel.setForeground(Color.blue);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0;i< fishCount;i++) {
			if(totalfish[i].getdoDraw()) {
				totalfish[i].draw(g);
			}
		}
		
		for(int i=0;i<turtleCount;i++) {
			if(totalturtle[i].getdoDraw()) {
				totalturtle[i].draw(g);
			}
		}
		
		repaint();
	}
	
	public void setButtonSelected(int i) {
		buttonselected = i;
	}
	
	public int getTotalFish() {
		return fishCounting;
	}
	public int getTotalTurtle() {
		return turtleCounting;
	}
	
	public void clear() {
		fishCount = 0;
		turtleCount =0;
		fishCounting = 0;
		turtleCounting = 0;
		repaint();
	}
	
	public class MouseHandler extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			switch(buttonselected) {
			case 1:
				if(fishCount<totalfish.length) {
					totalfish[fishCount] = new Fish(e.getX(),e.getY());
					fishThread[fishCount] = new FishThread(totalfish[fishCount]);
					executorService.execute(fishThread[fishCount]);
					fishCount++;
					fishCounting++;
					statusLabel.setText(String.format("目前功能:新增魚        魚數量:%d  烏龜數量:%d",fishCounting,turtleCounting));
				}
				break;
			case 2:
				if(turtleCount<totalturtle.length) {
					totalturtle[turtleCount] = new Turtle(e.getX(),e.getY());
					turtleThread[turtleCount] = new TurtleThread(totalturtle[turtleCount]);
					executorService.execute(turtleThread[turtleCount]);
					turtleCount++;
					turtleCounting++;
					statusLabel.setText(String.format("目前功能:新增烏龜        魚數量:%d  烏龜數量:%d",fishCounting,turtleCounting));
				}
				break;
			case 3:
				statusLabel.setText(String.format("目前功能:移除選取        魚數量:%d  烏龜數量:%d",fishCounting,turtleCounting));
				int clickX = e.getX();
				int clickY = e.getY();
				for(int i=0;i<fishCount;i++){
					int fishX = totalfish[i].getX();
					int fishY = totalfish[i].getY();
					
					if(totalfish[i].getdoDraw())
					{
						if(fishX<=clickX && clickX<=fishX+totalfish[i].getSize())
						{
							if(fishY<=clickY && clickY<=fishY+totalfish[i].getSize())
							{
								if(totalfish[i].getdoDraw()) {
									fishCounting--;
								}
								statusLabel.setText(String.format("目前功能:移除選取        魚數量:%d  烏龜數量:%d",fishCounting,turtleCounting));
								fishThread[i].close();
								totalfish[i].closedoDraw();
								repaint();
								break;
							}
						}
					}
				}
				
				for(int i=0;i<turtleCount;i++) {
					int turtleX = totalturtle[i].getX();
					int turtleY = totalturtle[i].getY();
					
					if(totalturtle[i].getdoDraw())
					{
						if(turtleX<=clickX && clickX<=turtleX+totalturtle[i].getSize())
						{
							if(turtleY<=clickY && clickY<=turtleY+totalturtle[i].getSize())
							{
								if(totalturtle[i].getdoDraw()) {
									turtleCounting--;
								}
								statusLabel.setText(String.format("目前功能:移除選取        魚數量:%d  烏龜數量:%d",fishCounting,turtleCounting));
								turtleThread[i].close();
								totalturtle[i].closedoDraw();
								repaint();
								break;
							}
						}
					}
				}
				
				break;
				
			}
		}
	}
}
