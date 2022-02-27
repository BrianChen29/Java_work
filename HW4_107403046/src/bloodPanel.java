//資管三B 107403046 陳柏澔

import java.awt.*;
import javax.swing.*;

public class bloodPanel extends JPanel{
	int blood = 0;
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(Color.BLUE);
		g2d.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		
		g.drawLine(0, 5, (300-blood), 5);
	}
	
	public void blood(boolean b) {
		if((300-blood) > 0) {
			if(b) {
				blood += 15;
			}
			else {
				blood += 60;
			}
		}
		else {
			System.out.println("GAME OVER");
			System.exit(1);
		}
	}
	
	public int getBlood() {
		return 300-blood;
	}
	
}
