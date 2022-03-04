import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class Shark {
	int x,y;
	Image shark;
	Image othershark;
	int flag = 0;
	static int size=150;
	int size2;
	static int choice, newchoice;
	boolean doDraw = true;
	
	public Shark(int x,int y) {
		this.x = x;
		this.y = y;
		shark = getShark();
		othershark = getOtherShark();
		size2=size;
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
	
	public void setSize(int s) {
		if(size2<=500)
			size2+=s;
		if(size2==500)
			size2-=s;
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
			g.drawImage(shark, x, y,size2,size2, null);
		}
		else if(flag == 1) {
			g.drawImage(othershark,x,y,size2,size2,null);
		}
	}
	
	public static Image getShark() {
		try {
			ImageIcon shark = new ImageIcon(ImageIO.read(new File("src\\shark1.png")));
			Image image = shark.getImage();
			Image img = image.getScaledInstance(size,size,java.awt.Image.SCALE_AREA_AVERAGING);
			
			return img;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Image getOtherShark() {
		try {

			ImageIcon shark = new ImageIcon(ImageIO.read(new File("src\\shark2.png")));
			Image image = shark.getImage();
			Image img = image.getScaledInstance(size,size,java.awt.Image.SCALE_AREA_AVERAGING);
			
			return img;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}