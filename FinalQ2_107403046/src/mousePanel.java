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
	private Shark[] totalshark = new Shark[50];
	private Fishing[] totalfishing = new Fishing[10];
	private int fishCount = 0;
	private int turtleCount = 0;
	private int sharkCount = 0;
	private int stickCount = 0;
	private ExecutorService executorService = Executors.newCachedThreadPool();
	private JLabel statusLabel;
	private String statustring = "目前功能:%s            魚數量:%d  烏龜數量:%d 鯊魚數量:%d 釣竿數量:%d 已釣到魚數量: 已釣到烏龜數量:";
	private String buttonselectstr = "新增魚";
	
	private int fishCounting = 0;
	private int turtleCounting = 0;
	private int sharkCounting = 0;
	private int stickCounting = 0;
	
	private FishThread[] fishThread = new FishThread[100];
	private TurtleThread[] turtleThread = new TurtleThread[50];
	private SharkThread[] sharkThread = new SharkThread[50];
	private FishingThread[] fishingThread = new FishingThread[10];
	
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
		
		for(int i=0;i< sharkCount;i++) {
			if(totalshark[i].getdoDraw()) {
				totalshark[i].draw(g);
			}
		}
		
		for(int i = 0;i<stickCount; i++)
		{
			if(totalfishing[i].getdoDraw())
				totalfishing[i].draw(g);
		}
		
		int sharkX[] = new int[50];
		int sharkY[] = new int[50];
		int sharksize[] = new int[50];

		for(int i=0;i<fishCount;i++) {
			fishThread[i].setsharkX(sharkX);
			fishThread[i].setsharkY(sharkY);
			fishThread[i].setsharksize(sharksize);
			fishThread[i].setsharkcount(sharkCount);
			if(fishThread[i].sharkhaveeat)
				totalshark[fishThread[i].getsharkeat()].setSize(150);
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
	public int getTotalShark() {
		return sharkCounting;
	}
	public int getTotalFishing() {
		return stickCounting;
	}
	
	public void clear() {
		fishCount = 0;
		turtleCount =0;
		fishCounting = 0;
		turtleCounting = 0;
		stickCount=0;
		stickCounting=0;
		sharkCount=0;
		sharkCounting=0;
		repaint();
	}
	
	public class MouseHandler extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			switch(buttonselected) {
			case 1:
				if(fishCount<totalfish.length) {
					buttonselectstr = "新增魚";
					totalfish[fishCount] = new Fish(e.getX(),e.getY());
					fishThread[fishCount] = new FishThread(totalfish[fishCount]);
					executorService.execute(fishThread[fishCount]);
					fishCount++;
					fishCounting++;
					statusLabel.setText(String.format(statustring,buttonselectstr,fishCounting,turtleCounting,sharkCounting,stickCounting));
				}
				break;
			case 2:
				if(turtleCount<totalturtle.length) {
					buttonselectstr = "新增烏龜";
					totalturtle[turtleCount] = new Turtle(e.getX(),e.getY());
					turtleThread[turtleCount] = new TurtleThread(totalturtle[turtleCount]);
					executorService.execute(turtleThread[turtleCount]);
					turtleCount++;
					turtleCounting++;
					statusLabel.setText(String.format(statustring,buttonselectstr,fishCounting,turtleCounting,sharkCounting,stickCounting));
				}
				break;
			case 3:
				buttonselectstr = "移除選取";
				statusLabel.setText(String.format(statustring,buttonselectstr,fishCounting,turtleCounting,sharkCounting,stickCounting));
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
								statusLabel.setText(String.format(statustring,buttonselectstr,fishCounting,turtleCounting,sharkCounting,stickCounting));
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
								statusLabel.setText(String.format(statustring,buttonselectstr,fishCounting,turtleCounting,sharkCounting,stickCounting));
								turtleThread[i].close();
								totalturtle[i].closedoDraw();
								repaint();
								break;
							}
						}
					}
				}
				
				for(int i=0;i<sharkCount;i++){
					int fishX = totalshark[i].getX();
					int fishY = totalshark[i].getY();
					
					if(totalshark[i].getdoDraw())
					{
						if(fishX<=clickX && clickX<=fishX+totalshark[i].getSize())
						{
							if(fishY<=clickY && clickY<=fishY+totalshark[i].getSize())
							{
								if(totalfish[i].getdoDraw()) {
									sharkCounting--;
								}
								statusLabel.setText(String.format(statustring,buttonselectstr,fishCounting,turtleCounting,sharkCounting,stickCounting));
								sharkThread[i].close();
								totalshark[i].closedoDraw();
								repaint();
								break;
							}
						}
					}
				}
				
				break;
			case 4:
				if(sharkCount<totalshark.length) {
					buttonselectstr = "放鯊魚";
					totalshark[sharkCount] = new Shark(e.getX(),e.getY());
					sharkThread[sharkCount] = new SharkThread(totalshark[sharkCount]);
					executorService.execute(sharkThread[sharkCount]);
					sharkCount++;
					sharkCounting++;
					statusLabel.setText(String.format(statustring,buttonselectstr,fishCounting,turtleCounting,sharkCounting,stickCounting));
				}
				break;
			case 5:
				if(stickCount<totalfishing.length) {
					buttonselectstr = "放釣竿";
					totalfishing[stickCount] = new Fishing(e.getX(),e.getY());
					fishingThread[stickCount] = new FishingThread(totalfishing[stickCount]);
					executorService.execute(fishingThread[stickCount]);
					stickCount++;
					stickCounting++;
					statusLabel.setText(String.format(statustring,buttonselectstr,fishCounting,turtleCounting,sharkCounting,stickCounting));
					}
				break;
			case 6:
				if(stickCount<totalfishing.length) {
					buttonselectstr = "收釣竿";
					int clicksX = e.getX();
					int clicksY = e.getY();
					for(int i=0;i<stickCount;i++){
						int fishingX = totalfishing[i].getX();
						int fishingY = totalfishing[i].getY();
						
						if(totalfishing[i].getdoDraw())
						{
							if(fishingX<=clicksX)
							{
								if(fishingY<=clicksY)
								{
									if(totalfishing[i].getdoDraw()) {
										stickCounting--;
									}
									statusLabel.setText(String.format(statustring,buttonselectstr,fishCounting,turtleCounting,sharkCounting,stickCounting));
									fishingThread[i].close();
									totalfishing[i].closedoDraw();
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
}
