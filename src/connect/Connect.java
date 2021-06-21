package connect;
import java.sql.*;
import java.sql.Connection;

public class Connect {

	public static Connection con;
	public Statement stat;
	public ResultSet rs;
	public ResultSetMetaData rsm;
	public PreparedStatement ps;
	
	public static Connection getInstance() {
		if(con == null) {
			new Connect();
		}
		return con;
	}
	
	
	
	
	public Connect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2", "root", "");
			System.out.println("Connect Success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connect Fail");
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			
			stat = con.createStatement();
			
			System.out.println("Connect success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connect fail");
		}
		try {
			rs = stat.executeQuery("SELECT * FROM product");
			rsm = rs.getMetaData();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(rs);
	}
	
	public ResultSet execQuery(String query) {
		try {
			rs = stat.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Fail to get data");
		}
		
		return rs;
	}
	
	public boolean execUpdate(String query) {
		try {
			stat.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public PreparedStatement prepareStatement(String query) {
    	PreparedStatement ps = null;
    	try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ps;
    }

}

