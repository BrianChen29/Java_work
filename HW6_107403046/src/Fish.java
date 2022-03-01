import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class Fish {
	int x,y;
	Image fish;
	Image otherfish;
	int flag = 0;
	static int size;
	static int choice, newchoice;
	boolean doDraw = true;
	
	public Fish(int x,int y) {
		this.x = x;
		this.y = y;
		fish = getFish();
		otherfish = getOtherFish();
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setFlag(int f) {
		flag = f;
	}
	
	public void closedoDraw() {
		doDraw = false;
	}
	public boolean getdoDraw() {
		return doDraw;
	}
	
	public void draw(Graphics g) {
		if(flag == 0) {
			g.drawImage(fish, x, y, null);
		}
		else if(flag == 1) {
			g.drawImage(otherfish,x,y,null);
		}
	}
	
	public static Image getFish() {
		try {
			Random r = new Random();
			size = r.nextInt(60)+25;
			choice = 2*r.nextInt(3)+1;
			newchoice = choice + 1;
			String str = "src\\"+choice+".png";
			
			ImageIcon fish = new ImageIcon(ImageIO.read(new File(str)));
			Image image = fish.getImage();
			Image img = image.getScaledInstance(size,size,java.awt.Image.SCALE_AREA_AVERAGING);
			
			return img;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Image getOtherFish() {
		try {

			String str = "src\\"+newchoice+".png";
			
			ImageIcon fish = new ImageIcon(ImageIO.read(new File(str)));
			Image image = fish.getImage();
			Image img = image.getScaledInstance(size,size,java.awt.Image.SCALE_AREA_AVERAGING);
			
			return img;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
