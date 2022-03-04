import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;

public class updateFrame extends JFrame{
	private static final String URL = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private Connection connection;
	private PreparedStatement addpeople,peoplestate;
	
	private static final String[] types = {"cell","company","home"};
	private JLabel typeLabel,phoneLabel,nameLabel,crowdLabel,emailLabel;
	private JComboBox<String> type;
	private JTextField phone,name,email;
	private JButton yes,no;
	private static final String[] crowds = {"undefined","classmate","family","friend"};
	private JComboBox<String> crowd;
	
	public updateFrame(final ResultSetTableModel tableModel,int ID) {
		super("�ק��p���H");
		
		try {
	         connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	         addpeople = connection.prepareStatement("UPDATE people SET type=?,name=?,phone=?,crowd=?,email=? WHERE MemberID='" + ID + "'");
	         peoplestate = connection.prepareStatement("SELECT * FROM people WHERE MemberID ='"+ID+"'");
	         ResultSet resultset = peoplestate.executeQuery();
	         resultset.next();
	         phone = new JTextField(resultset.getString("phone"),10);
	         name = new JTextField(resultset.getString("name"),20);
	         email = new JTextField(resultset.getString("email"),20);
	      }
	      catch (SQLException sqlException){
	         sqlException.printStackTrace();
	         System.exit(1);
	      }
		
		
		typeLabel = new JLabel("����");
		nameLabel = new JLabel("�W�r");
		phoneLabel = new JLabel("�q�ܸ��X");
		type = new JComboBox<String>(types);
		crowdLabel = new JLabel("�s��");
		crowd = new JComboBox<String>(crowds);
		emailLabel = new JLabel("�q�l�l��");
		email = new JTextField(20);
		yes = new JButton("�T�w");
		no = new JButton("����");
		
		setLayout(new GridLayout(6,2,4,4));
		add(typeLabel);
		add(type);
		add(nameLabel);
		add(name);
		add(phoneLabel);
		add(phone);
		add(crowdLabel);
		add(crowd);
		add(emailLabel);
		add(email);
		add(yes);
		add(no);
		
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typestr = (String)type.getSelectedItem();
				String namestr = name.getText();
				String phonestr = phone.getText();
				String crowdstr = (String)crowd.getSelectedItem();
				String emailstr = email.getText();
				
				boolean b = true;
				boolean emailb = true;
				
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
				
				
				//�q�l�l�� ��������
				emailb = isValidEmailAddress(emailstr);
				if(emailb == false) {
					JOptionPane.showMessageDialog(null, "�п�J���T��email�榡", "���~��email", JOptionPane.WARNING_MESSAGE);
				}
				
				if(b && emailb) {
				try
				{
					
					addpeople.setString(1, typestr);
					addpeople.setString(2, namestr);
					addpeople.setString(3, phonestr);
					addpeople.setString(4, crowdstr);
					addpeople.setString(5, emailstr);
					
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
			}
		});
		
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFrame.this.dispose();
			}
		});
	}
	
	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		}
		catch(AddressException ex) {
			result = false;
		}
		return result;
	}
}
