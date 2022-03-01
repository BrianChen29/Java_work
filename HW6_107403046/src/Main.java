import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Aquarium aqua = new Aquarium();
		aqua.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//aqua.setLocationRelativeTo(null);
		aqua.setSize(800,500);
		aqua.setVisible(true);
	}
}
