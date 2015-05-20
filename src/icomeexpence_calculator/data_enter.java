package icomeexpence_calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dbconnection.dbconnection;



public class data_enter {
	JFrame jf;
	JPanel panel;
	JLabel description,icategory,ecategory,amount,narration,insert_category,income,expense,c_date;
	JTextField t_description,t_amt,t_narration,t_insert_category,t_date;
	JComboBox cb_icategory,cb_ecategory;
	String v_category[]=new String[50];
	int i=100;
	String selectedcategory;
	JRadioButton r_income,r_expense;
	JButton insert,save,clear,report;
	String vv_category,i_selected_cat,e_selected_cat;
	int index,index3,index4;
	String d_date,d_description,d_amount,d_narration;
	public data_enter() throws ClassNotFoundException
	{
		init();
		event();
		
	}
	public void init() throws ClassNotFoundException
	{
		
	
	jf=new javax.swing.JFrame("Income - Expences");
	jf.setContentPane(new JLabel(new ImageIcon("course_7.jpg")));
	jf.setLayout(new BorderLayout());
	panel=new javax.swing.JPanel();
	panel.setOpaque(false);
	jf.add(panel);
	panel.setBackground(new Color(191,239,255));
	panel.setLayout(null);
	jf.setSize(700,600);
	jf.setVisible(true);
	
	Calendar calendar = Calendar.getInstance();
    java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
  
 
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
   
    String datestring = dateFormat.format(ourJavaDateObject); 
	
	r_income=new javax.swing.JRadioButton();
	r_income.setFont(new Font("Dialog", Font.BOLD, 16));
	r_income.setBounds(95,95,20,30);
	r_income.setOpaque(false);
	panel.add(r_income);
	
	income=new javax.swing.JLabel("Income");
	income.setFont(new Font("Dialog", Font.BOLD, 16));
	income.setForeground(Color.black);
	income.setBounds(120,95,150,20);
	panel.add(income);
	
	r_expense=new javax.swing.JRadioButton();
	r_expense.setFont(new Font("Dialog", Font.BOLD, 16));
	r_expense.setBounds(230,95,20,30);
	r_expense.setOpaque(false);
	panel.add(r_expense);
	
	expense=new javax.swing.JLabel("Expense");
	expense.setFont(new Font("Dialog", Font.BOLD, 16));
	expense.setForeground(Color.black);
	expense.setBounds(255,95,150,20);
	panel.add(expense);
	
	ButtonGroup jb = new ButtonGroup();
	jb.add(r_expense);
	jb.add(r_income);
	
	c_date=new javax.swing.JLabel("Date");
	c_date.setFont(new Font("Dialog", Font.BOLD, 16));
	c_date.setForeground(Color.black);
	c_date.setBounds(550,95,150,20);
	panel.add(c_date);
	
	t_date=new javax.swing.JTextField();
	t_date.setFont(new Font("Dialog", Font.BOLD, 16));
	t_date.setBounds(600,95,100,25);
	panel.add(t_date);
	
	
	
	icategory=new javax.swing.JLabel("Category");
	icategory.setFont(new Font("Dialog", Font.BOLD, 16));
	icategory.setForeground(Color.black);
	icategory.setBounds(95,75+i,150,25);
	panel.add(icategory);
	
	cb_icategory = new JComboBox();
	cb_icategory.setBounds(200,75+i,250,25);
	cb_icategory.setFont(new Font("Dialog",Font.BOLD,16));
	panel.add(cb_icategory);
	
	ecategory=new javax.swing.JLabel("Category");
	ecategory.setFont(new Font("Dialog", Font.BOLD, 16));
	ecategory.setForeground(Color.black);
	ecategory.setBounds(95,95+i,150,25);
	panel.add(ecategory);
	
	cb_ecategory = new JComboBox();
	cb_ecategory.setBounds(200,95+i,250,25);
	cb_ecategory.setFont(new Font("Dialog",Font.BOLD,16));
	panel.add(cb_ecategory);

	
	
	
	description=new javax.swing.JLabel("Description");
	description.setFont(new Font("Dialog", Font.BOLD, 16));
	description.setForeground(Color.black);
	description.setBounds(95,150+i,150,25);
	panel.add(description);
	
	t_description=new javax.swing.JTextField();
	t_description.setFont(new Font("Dialog", Font.BOLD, 16));
	t_description.setBounds(200,150+i,425,25);
	panel.add(t_description);
	
	amount=new javax.swing.JLabel("Amount");
	amount.setFont(new Font("Dialog", Font.BOLD, 16));
	amount.setForeground(Color.black);
	amount.setBounds(95,205+i,150,25);
	panel.add(amount);
	
	t_amt=new javax.swing.JTextField();
	t_amt.setFont(new Font("Dialog", Font.BOLD, 16));
	t_amt.setBounds(200,205+i,150,25);
	panel.add(t_amt);
	
	narration=new javax.swing.JLabel("Narration");
	narration.setFont(new Font("Dialog", Font.BOLD, 16));
	narration.setForeground(Color.black);
	narration.setBounds(95,260+i,150,25);
	panel.add(narration);
	
	t_narration=new javax.swing.JTextField();
	t_narration.setFont(new Font("Dialog", Font.BOLD, 16));
	t_narration.setBounds(200,260+i,425,25);
	panel.add(t_narration);
	
	insert_category=new javax.swing.JLabel("Insert Category");
	insert_category.setFont(new Font("Dialog", Font.BOLD, 16));
	insert_category.setForeground(Color.black);
	insert_category.setBounds(95,315+i,150,25);
	panel.add(insert_category);
	
	t_insert_category=new javax.swing.JTextField();
	t_insert_category.setFont(new Font("Dialog", Font.BOLD, 16));
	t_insert_category.setBounds(230,315+i,255,25);
	panel.add(t_insert_category);
	
	insert = new javax.swing.JButton("Insert");
	insert.setFont(new Font("Dialog", Font.BOLD, 16));
	insert.setBounds(500,315+i, 100, 25);
	panel.add(insert);
	
	save = new javax.swing.JButton("Save");
	save.setFont(new Font("Dialog", Font.BOLD, 16));
	save.setBounds(180,400+i, 100, 25);
	panel.add(save);
	
	
	clear = new javax.swing.JButton("Clear");
	clear.setFont(new Font("Dialog", Font.BOLD, 16));
	clear.setBounds(350,400+i, 100, 25);
	panel.add(clear);
	
	report = new javax.swing.JButton("View Report");
	report.setFont(new Font("Dialog", Font.BOLD, 16));
	report.setBounds(520,400+i, 150, 25);
	panel.add(report);
	

	t_date.setText(datestring);
	
	icategory.setVisible(false);
	
	cb_icategory.setVisible(false);
	
	cb_ecategory.setVisible(false);
	ecategory.setVisible(false);
	incombo();
	excombo();
	jf.setSize(750,600);
	jf.setResizable(false);
	
}
	public void event() throws ClassNotFoundException
	{
		i_selected_cat="Fund Raising Income";
		e_selected_cat="Direct Salaries";

		
		r_income.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cb_icategory.setVisible(true);
				icategory.setVisible(true);
				cb_ecategory.setVisible(false);
				ecategory.setVisible(false);
				vv_category="income";
			}
		});

		r_expense.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cb_icategory.setVisible(false);
				icategory.setVisible(false);
				cb_ecategory.setVisible(true);
				ecategory.setVisible(true);
				vv_category="expense";
			}
		});
		cb_icategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event)
			{
				
				Object selectedtypeobj = cb_icategory.getSelectedItem();
				i_selected_cat = String.valueOf(selectedtypeobj);
				index3 = cb_icategory.getSelectedIndex();
				
			}
		});
		cb_ecategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event)
			{
				
				Object selectedtypeobj = cb_ecategory.getSelectedItem();
				e_selected_cat = String.valueOf(selectedtypeobj);
				index4 = cb_ecategory.getSelectedIndex();
			}
		});
		save.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
				{

				if(vv_category.equalsIgnoreCase("income"))
				{
					getinformation();
					Connection con;
					ResultSet rs;
					PreparedStatement ps;
					
					dbconnection db = new dbconnection();
					con = db.getCon();
					try
					{
						ps = con.prepareStatement("insert into income_table (i_s_date,i_category,i_description,i_amount,i_narration) values(?,?,?,?,?)");
						ps.setString(1,d_date);
						ps.setString(2,i_selected_cat);
						ps.setString(3,d_description);
						ps.setString(4,d_amount);
						ps.setString(5,d_narration);
						 ps.executeUpdate();
						 JOptionPane.showMessageDialog(null,"Data are Saved Successfully");
					}
					 catch(SQLException e)
						{
						  // if the error message is "out of memory", 
						  // it probably means no database file is found
						  System.err.println(e.getMessage());
						}
				}
					
				else
				{
					getinformation();
					Connection con;
					ResultSet rs;
					PreparedStatement ps;
					
					dbconnection db = new dbconnection();
					con = db.getCon();
					try
					{
						ps = con.prepareStatement("insert into expense_table (e_s_date,e_category,e_description,e_amount,e_narration) values(?,?,?,?,?)");
						ps.setString(1,d_date);
						ps.setString(2,e_selected_cat);
						ps.setString(3,d_description);
						ps.setString(4,d_amount);
						ps.setString(5,d_narration);
						 ps.executeUpdate();
						 JOptionPane.showMessageDialog(null,"Data are Saved Successfully");
					}
					 catch(SQLException e)
						{
						  // if the error message is "out of memory", 
						  // it probably means no database file is found
						  System.err.println(e.getMessage());
						}
				}

				}
		});
		insert.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
				{
				String tempString=t_insert_category.getText();
				if(vv_category.equalsIgnoreCase("income"))
				{
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					PreparedStatement ps;
				      Connection connection = null;
				      try
				      {
				  // create a database connection
				  connection = DriverManager.getConnection("jdbc:sqlite:incomeexpense.db");				  
				  ps = connection.prepareStatement( "insert into mycomboincome values(?)");
				  ps.setString(1, tempString);
				  ps.executeUpdate();
				      }
				  catch(SQLException e)
					{
					  // if the error message is "out of memory", 
					  // it probably means no database file is found
					  System.err.println(e.getMessage());
					}
					finally
					{
					  try
					  {
					    if(connection != null)
					      connection.close();
					  }
					  catch(SQLException e)
					  {
					    // connection close failed.
					    System.err.println(e);
					  }
					}
				
				
				try {
					incombo();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				t_insert_category.setText("");
				}
				else
				{
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					PreparedStatement ps;
				      Connection connection = null;
				      try
				      {
				  // create a database connection
				  connection = DriverManager.getConnection("jdbc:sqlite:incomeexpense.db");

				  ps = connection.prepareStatement( "insert into mycombexpense values(?)");
				  ps.setString(1, tempString);
				  ps.executeUpdate();
				      }
				  catch(SQLException e)
					{
					  // if the error message is "out of memory", 
					  // it probably means no database file is found
					  System.err.println(e.getMessage());
					}
					finally
					{
					  try
					  {
					    if(connection != null)
					      connection.close();
					  }
					  catch(SQLException e)
					  {
					    // connection close failed.
					    System.err.println(e);
					  }
					}
				
				
				excombo();
				}
				t_insert_category.setText("");
				}
			
		});
		clear.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
				{

					textclear();
					
					

				}
		});
		report.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
				{

				 reportgeneration rg=new  reportgeneration();
					
					

				}
		});
	
	
}
	public void incombo() throws ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		

	      Connection connection = null;
	      try
	      {
	  // create a database connection
	  connection = DriverManager.getConnection("jdbc:sqlite:incomeexpense.db");
	  Statement statement = connection.createStatement();
	  statement.setQueryTimeout(30); 
	  ResultSet rs = statement.executeQuery("select * from mycomboincome");
	  while(rs.next())
	  {
		cb_icategory.addItem(rs.getString(1));
	}
	      }
	  catch(SQLException e)
		{
		  // if the error message is "out of memory", 
		  // it probably means no database file is found
		  System.err.println(e.getMessage());
		}
		finally
		{
		  try
		  {
		    if(connection != null)
		      connection.close();
		  }
		  catch(SQLException e)
		  {
		    // connection close failed.
		    System.err.println(e);
		  }
		}
		  
}

	public void textclear()
	{
		t_description.setText("");
		t_amt.setText("");
		t_narration.setText("");
		
	}
	public void excombo()
	{
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	      Connection connection = null;
	      try
	      {
	  // create a database connection
	  connection = DriverManager.getConnection("jdbc:sqlite:incomeexpense.db");
	  Statement statement = connection.createStatement();
	  statement.setQueryTimeout(30); 
	  ResultSet rs = statement.executeQuery("select * from mycombexpense");
	  while(rs.next())
	  {
		cb_ecategory.addItem(rs.getString(1));
	}
	      }
	  catch(SQLException e)
		{
		  // if the error message is "out of memory", 
		  // it probably means no database file is found
		  System.err.println(e.getMessage());
		}
		finally
		{
		  try
		  {
		    if(connection != null)
		      connection.close();
		  }
		  catch(SQLException e)
		  {
		    // connection close failed.
		    System.err.println(e);
		  }
		}
	}
	public void getinformation()
	{
	d_date=t_date.getText();
	d_description=t_description.getText();
	d_amount=t_amt.getText();
	d_narration=t_narration.getText();
	}
}
class master{
	public static void main(String args[]) throws ClassNotFoundException
	{
		data_enter dt=new data_enter();
		dt.jf.setVisible(true);
	}
}
