import java.awt.*;
import java.io.*;
import java.security.SecureRandom;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FishThread implements Runnable {
	private static final SecureRandom generator = new SecureRandom();
	private Fish fish;
	private int moveX = 0, moveY = 0;
	private int Xtimer = 0, Ytimer = 0;
	private boolean swim = true;
	
	public FishThread(Fish f) {
		fish = f;
		moveX = generator.nextInt(2);
		moveY = generator.nextInt(2);
		
	}
	
	public void close() {
		swim = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int x = fish.getX();
		int y = fish.getY();
		while(swim) {
			//計時 過一段時間換方向
			if(Xtimer == 30) {
				if(moveX == 0) {
					moveX = 1;
				}
				else if(moveX == 1) {
					moveX = 0;
				}
				
				Xtimer = 0;
			}
			
			if(Ytimer == 20) {
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
					
					fish.setFlag(0);
					fish.setX(x);
				}
				else if(moveX == 1) {
					x -= generator.nextInt(10);
					
					fish.setFlag(1);
					fish.setX(x);
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
					fish.setY(y);
				}
				else if(moveY == 1) {
					y -= generator.nextInt(5);
					fish.setY(y);
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