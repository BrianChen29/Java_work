import java.awt.*;
import java.io.*;
import java.security.SecureRandom;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SharkThread implements Runnable {
	private static final SecureRandom generator = new SecureRandom();
	private Shark shark;
	private int moveX = 0, moveY = 0;
	private int Xtimer = 0, Ytimer = 0;
	private boolean swim = true;
	
	public SharkThread(Shark s) {
		shark = s;
		moveX = generator.nextInt(2);
		moveY = generator.nextInt(2);
		
	}
	
	public void close() {
		swim = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int x = shark.getX();
		int y = shark.getY();
		while(swim) {
			//計時 過一段時間換方向
			if(Xtimer == 20) {
				if(moveX == 0) {
					moveX = 1;
				}
				else if(moveX == 1) {
					moveX = 0;
				}
				
				Xtimer = 0;
			}
			
			if(Ytimer == 10) {
				if(moveY == 0) {
					moveY = 1;
				}
				else if(moveY ==1) {
					moveY = 0;
				}
				
				Ytimer = 0;
			}
			
			try {
				Thread.sleep(generator.nextInt(500));
				
				if(x >= 750) {
					moveX = 1;
					Xtimer = 0;
				}
				else if(x <= 0) {
					moveX = 0;
					Xtimer = 0;
				}
				
				if(moveX == 0) {
					x += generator.nextInt(10);
					
					shark.setFlag(0);
					shark.setX(x);
				}
				else if(moveX == 1) {
					x -= generator.nextInt(10);
					
					shark.setFlag(1);
					shark.setX(x);
				}
				
				//y座標
				if(y>=450) {
					moveY = 1;
					Ytimer = 0;
				}
				else if(y<=0) {
					moveY = 1;
					Ytimer = 0;
				}
				
				if(moveY == 0) {
					y += generator.nextInt(5);
					shark.setY(y);
				}
				else if(moveY == 1) {
					y -= generator.nextInt(5);
					shark.setY(y);
				}
				
				Xtimer++;
				Ytimer++;
			}
			catch(InterruptedException exception) {
				Thread.currentThread().interrupt();
			}
		}
		
	}

}