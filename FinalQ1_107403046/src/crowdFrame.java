import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;



public class crowdFrame extends JFrame{
	private static final String URL = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private Connection connection;
	private PreparedStatement updatepeople,getAll;
	
	private JLabel title;
	private JPanel buttonPanel = new JPanel();
	public static int buttonselected = 0;
	private JButton classmate,family,friend,undefined;
	
	private static final String[] crowds = {"undefined","classmate","family","friend"};
	private JComboBox<String> crowd;
	
	public crowdFrame(final ResultSetTableModel tableModel) {
		super("群組初始化");
		
		
		try {
	        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			getAll = connection.prepareStatement("SELECT * FROM people");
			updatepeople = connection.prepareStatement("UPDATE member.people.* SET crowd="+crowds[buttonselected]);
	    }
	    catch (SQLException sqlException) {
	         sqlException.printStackTrace();
	         System.exit(1);
	    }
		
		title = new JLabel("請輸入初始化群組");
		
		classmate = new JButton("classmate");
		classmate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setButtonSelected(1);
			}
		});
		
		family = new JButton("family");
		family.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setButtonSelected(2);
			}
		});
		
		friend = new JButton("friend");
		friend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setButtonSelected(3);
			}
		});
		
		undefined = new JButton("undefined");
		undefined.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setButtonSelected(0);
			}
		});
		
		buttonPanel.add(classmate);
		buttonPanel.add(family);
		buttonPanel.add(friend);
		buttonPanel.add(undefined);
		
		setLayout(new BorderLayout());
		add(title,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.SOUTH);
	}
	
	public void setButtonSelected(int i) {
		buttonselected = i;
	}
	
	
}
