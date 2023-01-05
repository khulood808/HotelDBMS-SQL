package hotelSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Room_Type {
	static String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
	static String user = "sa";
	static String pass = "root";
	
	public static boolean createRoom_TypeTable() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		String sqlDB = "CREATE TABLE Room_Type"
				+ "(id INTEGER, "
				+ " room_type_name VARCHAR(20) not NULL, "
				+ " created_date date, " 
				+ " updated_date date, " 
				+ " is_Active VARCHAR(20) not NULL,"
				+ " PRIMARY KEY ( id ))";	
		Connection conn = null;
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();
			int m = st.executeUpdate(sqlDB);
			if (m >= 0){
				System.out.println("Room_Type table Created...");
				return true;
			} else {
				System.out.println("Room_Type table already Created Choose other name for your table...");
			}
			conn.close();
		}
		catch (Exception ex) {
			System.err.println(ex);
		}
		return false;
	}

	public static void inpustUser(int h) {
		Scanner sc = new Scanner(System.in);

		String room_type_name = "Sweety";
		Date created_date = new Date(System.currentTimeMillis());
		Date updated_date = new Date(System.currentTimeMillis());
		String is_Active="true";
		
		Random rn = new Random();
	    Integer randomNumber = (Integer) rn.nextInt(100);
	    
	    Connection conn = null;
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, user, pass);
			 for (int i = 1; i <= h ;i++) {
				 String ins = "Insert into Room_Type values(" + i + room_type_name + ",'" +
						 created_date + "','" + updated_date + "','" + is_Active + "','" + +1 +"')";
						System.out.println(ins); 
						Statement st = conn.createStatement();
						int m = st.executeUpdate(ins);
						System.out.println("Done...");
			 } 
			 conn.close();
		}
		catch (Exception ex) {
			System.err.println(ex);
		}
	}
	public static void getById() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
	    
	    Connection con = null;
	    try {

	        Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	        DriverManager.registerDriver(driver);
	        con = DriverManager.getConnection(url, user,pass);
	        Statement st = con.createStatement();
	        Scanner scanner = new Scanner(System.in);
	        int inId = scanner.nextInt();
	        int coun = 1;
	        String inpId = "SELECT * FROM Room_Type WHERE id='" + inId +"'";
	        ResultSet res = st.executeQuery(inpId);
	        while (res.next() && coun<= inId) {
	        	int id = res.getInt("id");
	        
	        String room_type_name  = res.getString("room_type_name");
			Date created_date = res.getDate("created_date");
			Date updated_date = res.getDate("updated_date");
			String is_Active= res.getString("is_Active");
			System.out.println(id + " "+ room_type_name +" " +created_date + " "+ updated_date + " "+ is_Active);
			coun++;
			}
	    }
	    catch (Exception ex) {
	        System.err.println(ex);
	    }
	}}
