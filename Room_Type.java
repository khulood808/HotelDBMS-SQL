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
	}
	
	public static void Printroom_Type(int pri) {
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
	        String sql = "SELECT * FROM Room_Type ORDER by id LIMT"+pri;
	        ResultSet res = st.executeQuery(sql);
	        while (res.next() && coun <= pri) {
				int id = res.getInt("id");
				String room_type_name = res.getString("room_type_name");
				String created_date = res.getString("created_date");
				Date updated_date = res.getDate("updated_date");
				boolean isActive = res.getBoolean("is_Active");
				System.out.println(id + " " + room_type_name + " " + created_date + " " + updated_date + " "+ " " + isActive);
						
				coun++;
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	public static void updateById(){
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
	        System.out.println("Enter Room Type Id to Update data");
	        int upRoomType = scanner.nextInt();
	        
	        System.out.println("Set up a New Room Type");
	        String roomTypeName = scanner.next();
	        
	        System.out.println("Set up a New Room Type Location");
	        String roomTypeLoc = scanner.next();
	        
	        String sql = "UPDATE Room_Type SET hotel_name='" + roomTypeName +"',hotel_location='" + roomTypeLoc  + "' WHERE id ='"+ upRoomType +"'";
	        int resl = st.executeUpdate(sql);
	    }
	    catch (Exception ex) {
	        System.err.println(ex);
	    }
	}
	public static void makeIsActiveFalseById() {
		String url = "jdbc:mysql://localhost:3306/HotelDBMS";
		String user = "root";
		String pass = "root";
		Scanner scanner = new Scanner(System.in);
		Connection conn = null;
		try {
			Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();
			
	        System.out.println("Enter Room Type Id to Get the Activated Room Type");
	        int actRoom_Type = scanner.nextInt();
	       
			String sql = "UPDATE Room_Type SET is_Active=false WHERE id<=" + actRoom_Type;
			int rs = st.executeUpdate(sql);
		      conn.close();
		  
	} catch (Exception ex) {
		System.err.println(ex);
	}
	}
	public static void deleteById() {
		String url = "jdbc:mysql://localhost:3306/HotelDBMS";
		String user = "root";
		String pass = "root";
		Scanner scanner = new Scanner(System.in);
		Connection conn = null;
		try {
			Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			
			conn = DriverManager.getConnection(url, user, pass);
			
			Statement st = conn.createStatement();
			int delid=scanner.nextInt();
			String sql = "select * from Room_Type where id='" + delid + "'";
			
			int rs = st.executeUpdate(sql);
		      conn.close();
		  
	} catch (Exception ex) {
		System.err.println(ex);
	}
}
	public static void inputHotel() {
		 String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		 
		 String user = "sa";
		 String pass = "root";
		
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter ID");
      int id = scanner.nextInt();
      
      System.out.println("Enter Room Type Name");
      String name = scanner.next();

      System.out.println("Enter Room Type Location");
      String loc = scanner.next();
      
      System.out.println("Enter Created Date");
      String crDate = scanner.next();

      System.out.println("Enter Update Date");
      String upDate = scanner.next();

      System.out.println("Enter If Active Or In Active");
      String act = scanner.next();

      String insRoom_Type = "insert into Room_Type values('" + id+ "'," + name + ",'" + loc + "','" +crDate +"','" + upDate +"'," + act +"')";

      Connection con = null;

      try {

          Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
          DriverManager.registerDriver(driver);
          con = DriverManager.getConnection(url, user,pass);
          Statement st = con.createStatement();

          int m = st.executeUpdate(insRoom_Type);
          if (m >=  1)
              System.out.println("inserted successfully : " + insRoom_Type);
          else
              System.out.println("insertion failed");
          con.close();
      }

      catch (Exception ex) {
          System.err.println(ex);
      }
  }
	}

