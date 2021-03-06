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
		super("增加聯絡人");
		
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
		
		typeLabel = new JLabel("類型");
		nameLabel = new JLabel("名字");
		phoneLabel = new JLabel("電話號碼");
		phone = new JTextField(10);
		name = new JTextField(20);
		type = new JComboBox<String>(types);
		yes = new JButton("完成");
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
				
				boolean b = true;
				
				//電話號碼相關限制
				if(type.getSelectedIndex()==0)
				{
					if(phonestr.charAt(0)!='0' || phonestr.charAt(1)!='9' || phonestr.length()!=10) {
						JOptionPane.showMessageDialog(null, "輸入不符合規定!請輸入09開頭 且 10個數字", "格式不符", JOptionPane.WARNING_MESSAGE);
						b = false;
					}
					else
						b = true;
				}
				else
				{
					if(phonestr.charAt(0)!='0' || phonestr.length()>10 || phonestr.length()<9) {
						JOptionPane.showMessageDialog(null, "輸入不符合規定!請輸入0開頭 且 10或9個數字", "格式不符", JOptionPane.WARNING_MESSAGE);
						b = false;
					}
					else
						b = true;
				}
				
				if(namelist.contains(namestr))
				{
					JOptionPane.showMessageDialog(null, "請勿輸入重複名稱，可改用小名或匿名", "名稱重複", JOptionPane.WARNING_MESSAGE);
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
