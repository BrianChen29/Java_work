import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Fishing {
	int x,y;
	Image fishing;
	int flag=0;
	static int i,j,size;
	boolean doDraw = true;
	
	public Fishing(int x,int y) {
		this.x=x;
		this.y=y;
		fishing=getStick();
	}
	
	public void closedoDraw() {
		doDraw = false;
	}
	public boolean getdoDraw() {
		return doDraw;
	}
	
	public void setX(int x) {this.x=x;}	
	public int getX() {return x;}
	
	public void setY(int y) {this.y=y;}
	public int getY() {return y;}
	public int getSize() {return size;}
	
	public void setFlag(int f) {flag = f;}
	
	public void draw(Graphics g) {
		g.drawImage(fishing, x, y, null);
	}
	
	//¹Ï¤ù
		public static Image getStick() {
			try
			{
				String s = "src\\stick.png";
				
				ImageIcon cookie = new ImageIcon(ImageIO.read(new File(s)));  
				Image image = cookie.getImage(); 
				Image newimg = image.getScaledInstance(40, 40, java.awt.Image.SCALE_AREA_AVERAGING);
				
				return newimg;
			}
			catch (IOException e)
			{
			      e.printStackTrace();
			}
			
			return null;
		}
}