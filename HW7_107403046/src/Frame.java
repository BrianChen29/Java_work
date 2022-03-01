import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTabbedPane;

public class Frame extends JFrame {

	private JPanel contentPane;
	private JTextField search;

	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";
	private static final String USERNAME = "java";
	private static final String PASSWORD = "java";
	private static final String DEFAULT_QUERY = "SELECT name FROM people";  
	private Connection connection;
	private PreparedStatement deletepeople,getInformation;
	private static ResultSetTableModel tableModel;
	private JTable peopletable;
	
	/**
	 * Create the frame.
	 */
	public Frame() {
		super("Contacts");
		
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		try 
		{
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			deletepeople = connection.prepareStatement("DELETE FROM people WHERE MemberID=?");
			getInformation = connection.prepareStatement("SELECT * FROM people WHERE name=?");
			tableModel = new ResultSetTableModel(DATABASE_URL, USERNAME, PASSWORD, DEFAULT_QUERY);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		peopletable = new JTable(tableModel);
		peopletable.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		peopletable.setRowHeight(30);
		
		//雙擊cell事件
		peopletable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					//先取得點擊欄位中的名字
					int row = peopletable.rowAtPoint(e.getPoint());
					int col = peopletable.columnAtPoint(e.getPoint());
					String s = (String)peopletable.getValueAt(row, col);
					int ID = 0;
					String name="";
					String type="";
					String phone="";
							
					//取得資料
					try {
						getInformation.setString(1, s);
						ResultSet resultSet = getInformation.executeQuery();
						resultSet.next();
						ID = resultSet.getInt("MemberID");
						name = resultSet.getString("name");
						type = resultSet.getString("type");
						phone = resultSet.getString("phone");
					}
					catch (SQLException sqlException){
						   sqlException.printStackTrace();
					} 
							
					//詢問要刪除還是修改
					String[] option = {"Update","Detete","Cancel"};
					String information = "用戶名稱:"+name+"\n用戶類型:"+type+"\n用戶電話號碼:"+phone+"\n\n要修改還是刪除?";
					int response = JOptionPane.showOptionDialog(null, information, "詳細資訊", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
					if(response==0){
						updateFrame uf = new updateFrame(tableModel,ID);
						uf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						uf.setLocationRelativeTo(null);
						uf.setSize(400, 300);
						uf.setVisible(true);
					}
							else if(response==1)
							{
								try 
								{
									deletepeople.setInt(1, ID);
									deletepeople.executeUpdate();
									tableModel.setQuery("SELECT name FROM people");
								} 
								catch (SQLException e1) 
								{
									e1.printStackTrace();
								}
							}
						}
					}
				});
		
		//上方panel
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[] {290, 50, 0};
		gbl_topPanel.rowHeights = new int[]{42, 42, 0};
		gbl_topPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		//Contacts label
		JLabel titleLabel = new JLabel("Contacts");
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 36));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.fill = GridBagConstraints.BOTH;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		topPanel.add(titleLabel, gbc_titleLabel);
		
		//+按鈕Panel
		JPanel addPanel = new JPanel();
		GridBagConstraints gbc_addPanel = new GridBagConstraints();
		gbc_addPanel.fill = GridBagConstraints.BOTH;
		gbc_addPanel.insets = new Insets(0, 0, 5, 0);
		gbc_addPanel.gridx = 1;
		gbc_addPanel.gridy = 0;
		topPanel.add(addPanel, gbc_addPanel);
		
		//+按鈕
		JButton addButton = new JButton(getaddIcon());
		addButton.setContentAreaFilled(false);
		addButton.setFocusPainted(false);
		addButton.setBorderPainted(false);	
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFrame af = new addFrame(tableModel);
				af.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				af.setLocationRelativeTo(null);
				af.setSize(400, 300);
				af.setVisible(true);
			}
		});
		addPanel.add(addButton);
		
		//search textfield
		search = new JTextField(10);
		search.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		search.setColumns(10);
		GridBagConstraints gbc_searchText = new GridBagConstraints();
		gbc_searchText.fill = GridBagConstraints.BOTH;
		gbc_searchText.insets = new Insets(0, 0, 0, 5);
		gbc_searchText.gridx = 0;
		gbc_searchText.gridy = 1;
		topPanel.add(search, gbc_searchText);
		
		//search按鈕Panel
		JPanel searchPanel = new JPanel();
		GridBagConstraints gbc_searchPanel = new GridBagConstraints();
		gbc_searchPanel.fill = GridBagConstraints.BOTH;
		gbc_searchPanel.gridx = 1;
		gbc_searchPanel.gridy = 1;
		topPanel.add(searchPanel, gbc_searchPanel);
		
		//search按鈕
		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Arial", Font.PLAIN, 12));
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
	    peopletable.setRowSorter(sorter);
	    searchButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String searchtext = "SELECT name FROM people WHERE phone LIKE '%" + search.getText() + "%' OR name LIKE '%" + search.getText() + "%'";
	            	  
	    		try {
	    			tableModel.setQuery(searchtext);
	            } 
	            catch (SQLException sqlException) {
	                     sqlException.printStackTrace();
	            }
	        } 
	    }); 
		searchPanel.add(searchButton);
		
		//中間通訊錄Panel
		JPanel peoplePanel = new JPanel();
		contentPane.add(peoplePanel, BorderLayout.CENTER);
		peoplePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblName = new JLabel("name");
		lblName.setFont(new Font("Arial", Font.PLAIN, 12));
		peoplePanel.add(lblName, BorderLayout.NORTH);
		
		JScrollPane nameScrollPane = new JScrollPane(peopletable);
		nameScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		peoplePanel.add(nameScrollPane);
		
		
	}
	
	public static ImageIcon getaddIcon() {
		try
		{
			ImageIcon add = new ImageIcon(ImageIO.read(new File("src\\1.jpg")));  
			Image image = add.getImage(); 
			Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);  
			add = new ImageIcon(newimg);
			
			return add;
		}
		catch (IOException e)
		{
		      e.printStackTrace();
		}
		return null;
	}
	
	

}
