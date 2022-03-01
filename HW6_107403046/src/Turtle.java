import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Turtle {
	int x,y;
	Image turtle;
	Image otherturtle;
	int flag = 0;
	boolean doDraw = true;
	
	public Turtle(int x,int y) {
		this.x = x;
		this.y = y;
		turtle = getTurtle();
		otherturtle = getOtherTurtle();
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
		return 80;
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
			g.drawImage(turtle, x, y, null);
		}
		else if(flag == 1) {
			g.drawImage(otherturtle,x,y,null);
		}
	}
	
	public static Image getTurtle() {
		try {
			
			ImageIcon turtle = new ImageIcon(ImageIO.read(new File("src\\turtle.png")));
			Image image = turtle.getImage();
			Image img = image.getScaledInstance(80,80,java.awt.Image.SCALE_AREA_AVERAGING);
			
			return img;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Image getOtherTurtle() {
		try {

			ImageIcon turtle = new ImageIcon(ImageIO.read(new File("src\\turtle2.png")));
			Image image = turtle.getImage();
			Image img = image.getScaledInstance(80,80,java.awt.Image.SCALE_AREA_AVERAGING);
			
			return img;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
