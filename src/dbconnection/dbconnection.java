package dbconnection;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
public class dbconnection {
Connection con;
	
	public Connection getCon(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:sqlite:incomeexpense.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}

