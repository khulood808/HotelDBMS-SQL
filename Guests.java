package hotelSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Guests {
	static String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
	static String user = "sa";
	static String pass = "root";
	
	public static boolean createGuestsTable() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		String sqlDB = "CREATE TABLE Guests"
				+ "(id INTEGER,"
				+ " guest_name VARCHAR(20) not NULL, "
				+ " guest_phone VARCHAR(20) not NULL,"
				+ " guest_accompanying_members INTEGER not NULL, " 
				+ " guest_payment_amount INTEGER not NULL,"
				+ "room_id INTEGER FOREIGN KEY REFERENCES Rooms(id) , "
				+ " hotel_id INTEGER FOREIGN KEY REFERENCES Hotels(id) , "
				+ " created_date VARCHAR(20) not NULL,"
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
				System.out.println("Guests table Created...");
				return true;
			} else {
				System.out.println("Guests table already Created Choose other name for your table...");
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

		String guest_name = "Khalid";
		String guest_phone = "91938263 ";
		Integer guest_accompanying_members = 1234;
		Integer guest_payment_amount = 100;
		//Integer room_id = 101;
		//Integer hotel_id =010;
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
				 String ins = "Insert into Guests values(" + i + randomNumber + ",'" +
						 guest_name + "','" + guest_phone + "','" + guest_accompanying_members + "','" +
						 guest_payment_amount + "','" + created_date + "','" + updated_date + "','" +
						 is_Active + "'," +1 +"')";
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
	        String inpId = "SELECT * FROM Guests WHERE id='" + inId +"'";
	        ResultSet res = st.executeQuery(inpId);
	        while (res.next() && coun<= inId) {
	        	int id = res.getInt("id");
	        
	        String guest_name = res.getString("Khalid");
			String guest_phone = res.getString("91938263");
			Integer guest_accompanying_members = 1234;
			Integer guest_payment_amount = 100;
			//Integer room_id = 101;
			//Integer hotel_id =010;
			Date created_date = res.getDate("created_date");
			Date updated_date = res.getDate("updated_date");
			String is_Active= res.getString("is_Active");
			System.out.println(id + " "+ guest_name +" " + guest_phone + " " +guest_accompanying_members + " "+ guest_payment_amount + " "+ created_date+ " "+ updated_date + " "+ is_Active);
			coun++;
			}
	    }
	    catch (Exception ex) {
	        System.err.println(ex);
	    }
	}
	public static void PrintGuests(int pri) {
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
	        String sql = "SELECT * FROM Guests ORDER by id LIMT"+pri;
	        ResultSet res = st.executeQuery(sql);
	        while (res.next() && coun <= pri) {
				int id = res.getInt("id");
				String guest_name = res.getString("guest_name");
				String guest_phone = res.getString("guest_phone   guest_accompanying_members");
				Integer guest_accompanying_members = res.getInt("= 100");
				Date createddate = res.getDate("created_date");
				Date updateddate = res.getDate("updated_date");
				boolean isActive = res.getBoolean("is_Active");
				System.out.println(id + " " + guest_name + " " + guest_phone + " "+ " " + guest_accompanying_members + " " + createddate + " " + updateddate
						+ " " + isActive);
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
	        System.out.println("Enter Guests Id to Update data");
	        int upId = scanner.nextInt();
	        
	        System.out.println("Set up a New Guests Name");
	        String guestslName = scanner.next();
	        
	        System.out.println("Set up a New Guests Location");
	        String guestsLoc = scanner.next();
	        
	        String sql = "UPDATE Guests SET hotel_name='" + guestslName +"',hotel_location='" + guestsLoc  + "' WHERE id ='"+ upId +"'";
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
			
	        System.out.println("Enter Guests Id to Get the Activated Guests");
	        int actHotel = scanner.nextInt();
	       
			String sql = "UPDATE Guests SET is_Active=false WHERE id<=" + actHotel;
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
			String sql = "select * from Guests where id='" + delid + "'";
			
			int rs = st.executeUpdate(sql);
		      conn.close();
		  
	} catch (Exception ex) {
		System.err.println(ex);
	}
	}
	public static void inputGuests() {
		 String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		 
		 String user = "sa";
		 String pass = "root";
		
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter ID");
      int id = scanner.nextInt();
      
      System.out.println("Enter Guests Name");
      String name = scanner.next();

      System.out.println("Enter Guests Location");
      String loc = scanner.next();
      
      System.out.println("Enter Created Date");
      String crDate = scanner.next();

      System.out.println("Enter Update Date");
      String upDate = scanner.next();

      System.out.println("Enter If Active Or In Active");
      String act = scanner.next();

      String insGuests = "insert into Guests values('" + id+ "'," + name + ",'" + loc + "','" +crDate +"','" + upDate +"'," + act +"')";

      Connection con = null;

      try {

          Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
          DriverManager.registerDriver(driver);
          con = DriverManager.getConnection(url, user,pass);
          Statement st = con.createStatement();

          int m = st.executeUpdate(insGuests);
          if (m >=  1)
              System.out.println("inserted successfully : " + insGuests);
          else
              System.out.println("insertion failed");
          con.close();
      }

      catch (Exception ex) {
          System.err.println(ex);
      }
  }
	}

