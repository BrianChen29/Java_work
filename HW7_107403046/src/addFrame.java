import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class addFrame extends JFrame{
	private static final String URL = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private List<String> namelist;
	private Connection connection;
	private PreparedStatement addpeople,getAll;
	
	private static final String[] types = {"cell","company","home"};
	private JLabel typeLabel,phoneLabel,nameLabel;
	private JComboBox<String> type;
	private JTextField phone,name;
	private JButton yes,no;
	
	public addFrame(final ResultSetTableModel tableModel) {
		super("�W�[�p���H");
		
		try {
	         connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	         addpeople = connection.prepareStatement("INSERT INTO people (type,name,phone) VALUES(?,?,?)");
	         getAll = connection.prepareStatement("SELECT * FROM people");
	         namelist = getAllname();
	    }
	    catch (SQLException sqlException) {
	         sqlException.printStackTrace();
	         System.exit(1);
	    }
		
		typeLabel = new JLabel("����");
		nameLabel = new JLabel("�W�r");
		phoneLabel = new JLabel("�q�ܸ��X");
		phone = new JTextField(10);
		name = new JTextField(20);
		type = new JComboBox<String>(types);
		yes = new JButton("����");
		no = new JButton("����");
		
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
				
				boolean b = true;
				
				//�q�ܸ��X��������
				if(type.getSelectedIndex()==0)
				{
					if(phonestr.charAt(0)!='0' || phonestr.charAt(1)!='9' || phonestr.length()!=10) {
						JOptionPane.showMessageDialog(null, "��J���ŦX�W�w!�п�J09�}�Y �B 10�ӼƦr", "�榡����", JOptionPane.WARNING_MESSAGE);
						b = false;
					}
					else
						b = true;
				}
				else
				{
					if(phonestr.charAt(0)!='0' || phonestr.length()>10 || phonestr.length()<9) {
						JOptionPane.showMessageDialog(null, "��J���ŦX�W�w!�п�J0�}�Y �B 10��9�ӼƦr", "�榡����", JOptionPane.WARNING_MESSAGE);
						b = false;
					}
					else
						b = true;
				}
				
				if(namelist.contains(namestr))
				{
					JOptionPane.showMessageDialog(null, "�Фſ�J���ƦW�١A�i��Τp�W�ΰΦW", "�W�٭���", JOptionPane.WARNING_MESSAGE);
					b = false;
				}
				
				if(b)
				{
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
					
					addFrame.this.dispose();
				}
			}
		});
		
		
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFrame.this.dispose();
			}
		});
	}
	
	public List<String> getAllname()
	   {
	      List<String> results = null;
	      ResultSet resultSet = null;
	      
	      try 
	      {
	         // executeQuery returns ResultSet containing matching entries
	         resultSet = getAll.executeQuery(); 
	         results = new ArrayList<String>();
	         
	         while (resultSet.next())
	         {
	            results.add(resultSet.getString("name"));
	         } 
	      } 
	      catch (SQLException sqlException)
	      {
	         sqlException.printStackTrace();         
	      }
	      
	      return results;
	   }
}
