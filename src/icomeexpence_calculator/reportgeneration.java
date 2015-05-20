package icomeexpence_calculator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.toedter.calendar.JDateChooser;
import dbconnection.dbconnection;

public class reportgeneration {
	
	
		JFrame jf;
		JLabel from_date,to_date,income,expense;
		JButton view,report,close;
		JComboBox c_scheme;
		JPanel report_panel;
		JRadioButton r_income,r_expense;
		String v_scheme[]=new String[20];
		String vv_category;
		int rcount;
		Date mm,mm2;
		Date [] mydate;
		String [] myloanno;
		String [] calloan;
		String [] tto;
		JasperPrint jp;
		 JasperReport jr;
		 JRDesignQuery newQuery;
		 String sql;
		 JasperViewer jv;
		 JasperDesign jd;
		private JDateChooser dateChooserSearch1,dateChooserSearch2;
		public reportgeneration()
		{
			ui();
			event();
		}
		public void ui()
		{
			jf=new javax.swing.JFrame("Income - Expences Report");
			jf.setContentPane(new JLabel(new ImageIcon("course_7.jpg")));
			jf.setLayout(new BorderLayout());
			report_panel=new javax.swing.JPanel();
			report_panel.setOpaque(false);
			jf.add(report_panel);
			report_panel.setBackground(new Color(191,239,255));
			report_panel.setLayout(null);
			jf.setSize(700,600);
			jf.setVisible(true);
			

			r_income=new javax.swing.JRadioButton();
			r_income.setFont(new Font("Dialog", Font.BOLD, 16));
			r_income.setBounds(95,95,20,30);
			r_income.setOpaque(false);
			report_panel.add(r_income);
			
			income=new javax.swing.JLabel("Income");
			income.setFont(new Font("Dialog", Font.BOLD, 16));
			income.setForeground(Color.black);
			income.setBounds(120,95,150,20);
			report_panel.add(income);
			
			r_expense=new javax.swing.JRadioButton();
			r_expense.setFont(new Font("Dialog", Font.BOLD, 16));
			r_expense.setBounds(230,95,20,30);
			r_expense.setOpaque(false);
			report_panel.add(r_expense);
			
			expense=new javax.swing.JLabel("Expense");
			expense.setFont(new Font("Dialog", Font.BOLD, 16));
			expense.setForeground(Color.black);
			expense.setBounds(255,95,150,20);
			report_panel.add(expense);
			
			ButtonGroup jb = new ButtonGroup();
			jb.add(r_expense);
			jb.add(r_income);
			

			from_date=new javax.swing.JLabel("From Date");
			from_date.setFont(new Font("Dialog", Font.BOLD, 16));
			from_date.setForeground(Color.black);
			from_date.setBounds(90,140,150,20);
			report_panel.add(from_date);
			
			dateChooserSearch1 = new JDateChooser();
			dateChooserSearch1.setDateFormatString("dd-MM-yyyy");
			dateChooserSearch1.setBounds(230,140,150,20);
			report_panel.add(dateChooserSearch1);
			
			to_date=new javax.swing.JLabel("To Date");
			to_date.setFont(new Font("Dialog", Font.BOLD, 16));
			to_date.setForeground(Color.black);
			to_date.setBounds(90,190,150,20);
			report_panel.add(to_date);
			
			dateChooserSearch2 = new JDateChooser();
			dateChooserSearch2.setDateFormatString("dd-MM-yyyy");
			dateChooserSearch2.setBounds(230,190,150,20);
			report_panel.add(dateChooserSearch2);
			
			report = new javax.swing.JButton("Report");
			report.setFont(new Font("Dialog", Font.BOLD, 16));
			report.setBounds(100,270, 100, 40);
			report_panel.add(report);
			
			close = new javax.swing.JButton("Close");
			close.setFont(new Font("Dialog", Font.BOLD, 16));
			close.setBounds(250,270, 100, 40);
			report_panel.add(close);
			jf.setResizable(false);
			
		}
public void event() 
		{
	r_income.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			vv_category="income";
		}
	});

	r_expense.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			vv_category="expense";
		}
	});
	
	report.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent event)
			{
			if(vv_category.equalsIgnoreCase("income"))
			{
				try {
					i_dateget();
					i_copydata();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 Connection con;
					
					
					dbconnection db = new dbconnection();
					con = db.getCon();
				
				 try{ 
					
					  jd=JRXmlLoader.load("incomereport.jrxml"); 
					  sql="select * from temp_table";
					  newQuery= new JRDesignQuery(); 
					  newQuery.setText(sql); 
					  jd.setQuery(newQuery); 
					  JasperReport jr=JasperCompileManager.compileReport(jd); 
					  jp=JasperFillManager.fillReport(jr, null, con);  
					  jv = new JasperViewer(jp,false);
					  jv.setVisible(true);
					  delmas();
					 }
				 
				 catch(Exception e){        
				 }
			}
			else
			{
				try {
					e_dateget();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e_copydata();
				 Connection con;
					ResultSet rs;
					PreparedStatement ps;
					
					dbconnection db = new dbconnection();
					con = db.getCon();
				
				 try{ 
					
					  jd=JRXmlLoader.load("expense report.jrxml"); 
					  sql="select * from temp_table";
					  newQuery= new JRDesignQuery(); 
					  newQuery.setText(sql); 
					  jd.setQuery(newQuery); 
					  JasperReport jr=JasperCompileManager.compileReport(jd); 
					  jp=JasperFillManager.fillReport(jr, null, con);  
					  jv = new JasperViewer(jp,false);
					  jv.setVisible(true);
					  delmas();
					 }
				 
				 catch(Exception e){        
				 }	
			}
			}
				
				
				

			
	});
	close.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent event)
			{

			
			jf.dispose();
				

			}
	});
	
	}
public void i_dateget() throws ParseException
{
Calendar calendar = Calendar.getInstance();
java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
Date mm= dateChooserSearch1.getDate();
//String datestring = dateFormat.format(ourJavaDateObject); 
String datestring = sdf.format(mm); 
Date mm2= dateChooserSearch2.getDate();
String datestring2 = sdf.format(mm2); 
Connection con;
ResultSet rs,rs2;
PreparedStatement ps,ps2;

dbconnection db = new dbconnection();
con = db.getCon();

try {
	ps2=con.prepareStatement("SELECT COUNT(*) AS rowcount FROM income_table");
	rs2=ps2.executeQuery();
	 while(rs2.next())
	  {
 rcount = rs2.getInt("rowcount") ;
	  }

	
	ps = con.prepareStatement( "select i_s_date,i_id from income_table");
	rs=ps.executeQuery();
	if(rs.next())
	{
		
		myloanno=new String[rcount];
	mydate=new Date[rcount];
	calloan=new String[rcount];
	tto=new String[rcount];
	
	for(int x=0;x<rcount;x++){
		tto[x]=rs.getString(1);
		mydate[x]=sdf.parse(rs.getString(1));
		myloanno[x]=rs.getString(2);
		rs.next();
	}
	}

for(int x=0;x<rcount;x++){
	
	if(tto[x].equalsIgnoreCase(datestring) || mydate[x].compareTo(mm)>=0 && tto[x].equalsIgnoreCase(datestring2) || mydate[x].compareTo(mm2)<=0) {
		
	
		calloan[x]=myloanno[x];
		
		
		}
}


}
catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	
}
public void i_copydata()
{
	Connection con;
	ResultSet rs;
	PreparedStatement ps;
		dbconnection db = new dbconnection();
		con = db.getCon();
	
	try {
		for(int x=0;x<rcount;x++){
		
		ps = con.prepareStatement( "insert into temp_table(t_id,t_s_date,t_category,t_description,t_amount,t_narration) select i_id,i_s_date,i_category,i_description,i_amount,i_narration from income_table where i_id=?");
		ps.setString(1,calloan[x]);
		ps.executeUpdate();
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void e_dateget() throws ParseException
{
	Calendar calendar = Calendar.getInstance();
	java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	Date mm= dateChooserSearch1.getDate();
	String datestring = sdf.format(mm);
	Date mm2= dateChooserSearch2.getDate();
	String datestring2 = sdf.format(mm2);
	Connection con;
	ResultSet rs,rs2;
	PreparedStatement ps,ps2;

	dbconnection db = new dbconnection();
	con = db.getCon();

	try {
		ps2=con.prepareStatement("SELECT COUNT(*) AS rowcount FROM expense_table");
		rs2=ps2.executeQuery();
		 while(rs2.next())
		  {
	 rcount = rs2.getInt("rowcount") ;
		  }
		ps = con.prepareStatement( "select e_s_date,e_id from expense_table");
		rs=ps.executeQuery();
		if(rs.next())
		{
			tto=new String[rcount];
			myloanno=new String[rcount];
		mydate=new Date[rcount];
		calloan=new String[rcount];
		for(int x=0;x<rcount;x++){
			tto[x]=rs.getString(1);
			mydate[x]=sdf.parse(rs.getString(1));
			myloanno[x]=rs.getString(2);
			rs.next();
		}
	}

	for(int x=0;x<rcount;x++){
		
		if(tto[x].equalsIgnoreCase(datestring) || mydate[x].compareTo(mm)>=0 && tto[x].equalsIgnoreCase(datestring2) || mydate[x].compareTo(mm2)<=0) {
			
			calloan[x]=myloanno[x];
			
			}
	}

	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void e_copydata()
{
	Connection con;
	ResultSet rs;
	PreparedStatement ps;
		dbconnection db = new dbconnection();
		con = db.getCon();
	
	try {
		for(int x=0;x<rcount;x++){
		
		ps = con.prepareStatement( "insert into temp_table(t_id,t_s_date,t_category,t_description,t_amount,t_narration) select e_id,e_s_date,e_category,e_description,e_amount,e_narration from expense_table where e_id=?");
		ps.setString(1,calloan[x]);
		ps.executeUpdate();
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void delmas()
{
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	dbconnection db = new dbconnection();
con = db.getCon();
	
	try {

		ps = con.prepareStatement( "delete from temp_table");
		ps.executeUpdate();
}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}

}

}	

