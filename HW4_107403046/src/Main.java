//107403046 資管三B 陳柏澔
import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		Multiples multiples = new Multiples();
		multiples.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		multiples.setLocationRelativeTo(null);
		multiples.setSize(320,180);
		multiples.setVisible(true);
		
		Game game = new Game();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setSize(330,350);
		game.setVisible(true);
		
	}
}
