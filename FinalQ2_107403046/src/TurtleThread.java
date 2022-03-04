import java.security.SecureRandom;

public class TurtleThread implements Runnable {
	private static final SecureRandom generator = new SecureRandom();
	private Turtle turtle;
	private int moveX = 0;
	private int Xtimer = 0;
	private boolean crawl = true;
	
	public TurtleThread(Turtle t) {
		turtle = t;
	}
	
	public void close() {
		crawl = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int x = turtle.getX();
		int y = turtle.getY();
		while(crawl) {
			//計時 過一段時間換方向
			if(Xtimer == 90) {
				if(moveX == 0) {
					moveX = 1;
				}
				else if(moveX == 1) {
					moveX = 0;
				}
				
				Xtimer = 0;
			}

			
			try {
				Thread.sleep(generator.nextInt(500));
				
				if(y < 320) {
					y += 7;
					turtle.setY(y);
				}
				else {
					if(x >= 750) {
						moveX = 1;
						Xtimer = 0;
					}
					else if(x <=0) {
						moveX = 1;
						Xtimer = 0;
					}
					
					if(moveX == 0) {
						x += generator.nextInt(7);
						turtle.setX(x);
						turtle.setFlag(0);
					}
					else if(moveX == 1) {
						x -= generator.nextInt(7);
						turtle.setX(x);
						turtle.setFlag(1);
					}
				}
				
				
				Xtimer++;
			}
			catch(InterruptedException exception) {
				Thread.currentThread().interrupt();
			}
		}
		
	}
}
