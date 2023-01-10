package hotelSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Employees {
	static String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
	static String user = "sa";
	static String pass = "root";
	
	public static boolean createEmployeesTable() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		String sqlDB = "CREATE TABLE Employees"
				+ "(id INTEGER, "
				+ " employee_type_id INTEGER FOREIGN KEY REFERENCES Employee_Type(id),"
				+ " room_id INTEGER FOREIGN KEY REFERENCES Hotels(id), " 
				+ " created_date date not NULL, " 
				+ " updated_date date , " 
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
				System.out.println("Employees table Created...");
				return true;
			} else {
				System.out.println("Employees table already Created Choose other name for your table...");
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

		Integer employee_type_id = 111;
		Integer room_id =100;
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
				 String ins = "Insert into Employees values(" + i + randomNumber + ",'" +
						 employee_type_id + "','" + room_id + "','" + created_date + "','" +
						 updated_date + "'," +1 +"')";
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
	        String inpId = "SELECT * FROM Employees WHERE id='" + inId +"'";
	        ResultSet res = st.executeQuery(inpId);
	        while (res.next() && coun<= inId) {
	        	int id = res.getInt("id");
	        
	        Integer employee_type_id = res.getInt(111);
	        Integer room_id  = res.getInt(100);
			Date created_date = res.getDate("created_date");
			Date updated_date = res.getDate("updated_date");
			String is_Active= res.getString("is_Active");
			System.out.println(id + " "+ employee_type_id +" " + room_id + " " +created_date + " "+ updated_date + " "+ is_Active);
			coun++;
			}
	    }
	    catch (Exception ex) {
	        System.err.println(ex);
	    }
	}
	public static void PrintEmployees(int pri) {
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
	        String sql = "SELECT * FROM Employees ORDER by id LIMT"+pri;
	        ResultSet res = st.executeQuery(sql);
	        while (res.next() && coun <= pri) {
				int id = res.getInt("id");
				Integer employee_type_id = res.getInt(111);
				Integer room_id  = res.getInt(100);
				Date createddate = res.getDate("created_date");
				Date updateddate = res.getDate("updated_date");
				boolean isActive = res.getBoolean("is_Active");
				System.out.println(id + " " + employee_type_id + " " + room_id + " " + createddate + " " + updateddate
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
	        System.out.println("Enter Employees Id to Update data");
	        int upId = scanner.nextInt();
	        
	        System.out.println("Set up a New Employees Name");
	        String hotelName = scanner.next();
	        
	        System.out.println("Set up a New Employees Location");
	        String hotelLoc = scanner.next();
	        
	        String sql = "UPDATE Employees SET hotel_name='" + hotelName +"',hotel_location='" + hotelLoc  + "' WHERE id ='"+ upId +"'";
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
			
	        System.out.println("Enter Employees Id to Get the Activated Hotels");
	        int actEmployees = scanner.nextInt();
	       
			String sql = "UPDATE Employees SET is_Active=false WHERE id<=" + actEmployees;
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
			String sql = "SELECT * FROM Employees WHERE id='" + delid + "'";
			
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
      
      System.out.println("Enter Employees Name");
      String name = scanner.next();

      System.out.println("Enter Employees Location");
      String loc = scanner.next();
      
      System.out.println("Enter Created Date");
      String crDate = scanner.next();

      System.out.println("Enter Update Date");
      String upDate = scanner.next();

      System.out.println("Enter If Active Or In Active");
      String act = scanner.next();

      String insEmployees = "insert into Hotels values('" + id+ "'," + name + ",'" + loc + "','" +crDate +"','" + upDate +"'," + act +"')";

      Connection con = null;

      try {

          Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
          DriverManager.registerDriver(driver);
          con = DriverManager.getConnection(url, user,pass);
          Statement st = con.createStatement();

          int m = st.executeUpdate(insEmployees);
          if (m >=  1)
              System.out.println("inserted successfully : " + insEmployees);
          else
              System.out.println("insertion failed");
          con.close();
      }

      catch (Exception ex) {
          System.err.println(ex);
      }
  }
	}

