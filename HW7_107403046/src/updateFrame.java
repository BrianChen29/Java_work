import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class updateFrame extends JFrame{
	private static final String URL = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private Connection connection;
	private PreparedStatement addpeople,peoplestate;
	
	private static final String[] types = {"cell","company","home"};
	private JLabel typeLabel,phoneLabel,nameLabel;
	private JComboBox<String> type;
	private JTextField phone,name;
	private JButton yes,no;
	
	public updateFrame(final ResultSetTableModel tableModel,int ID) {
		super("修改聯絡人");
		
		try {
	         connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	         addpeople = connection.prepareStatement("UPDATE people SET type=?,name=?,phone=? WHERE MemberID='" + ID + "'");
	         peoplestate = connection.prepareStatement("SELECT * FROM people WHERE MemberID ='"+ID+"'");
	         ResultSet resultset = peoplestate.executeQuery();
	         resultset.next();
	         phone = new JTextField(resultset.getString("phone"),10);
	         name = new JTextField(resultset.getString("name"),20);
	      }
	      catch (SQLException sqlException){
	         sqlException.printStackTrace();
	         System.exit(1);
	      }
		
		
		typeLabel = new JLabel("類型");
		nameLabel = new JLabel("名字");
		phoneLabel = new JLabel("電話號碼");
		type = new JComboBox<String>(types);
		yes = new JButton("確定");
		no = new JButton("取消");
		
		setLayout(new GridLayout(4,2,4,4));
		add(typeLabel);
		add(type);
		add(nameLabel);
		add(name);
		add(phoneLabel);
		add(phone);
		add(yes);
		add(no);
		
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typestr = (String)type.getSelectedItem();
				String namestr = name.getText();
				String phonestr = phone.getText();
				
				try
				{
					addpeople.setString(1, typestr);
					addpeople.setString(2, namestr);
					addpeople.setString(3, phonestr);
					
					addpeople.executeUpdate(); 
					
					tableModel.setQuery("SELECT name FROM people");
				}
				catch (SQLException sqlException)
			    {
			       sqlException.printStackTrace();
			    }
				catch (IllegalStateException e1) 
				{
					e1.printStackTrace();
				}
				
				updateFrame.this.dispose();
			}
		});
		
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFrame.this.dispose();
			}
		});
	}
	
	
}
