package hotelSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Employee_Type {
	static String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
	static String user = "sa";
	static String pass = "root";
	
	public static boolean createEmployee_TypeTable() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		String sqlDB = "CREATE TABLE Employee_Type"
				+ "(id INTEGER , "
				+ " employee_type_name VARCHAR(20) not NULL, "
				+ " created_date VARCHAR(20) not NULL, " 
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
				System.out.println("Employee_Type table Created...");
				return true;
			} else {
				System.out.println("Employee_Type table already Created Choose other name for your table...");
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

		String employee_type_name = "Khalid";
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
				 String ins = "Insert into Rooms values(" + i + employee_type_name + ",'" +
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
	        String inpId = "SELECT * FROM Employee_Type WHERE id='" + inId +"'";
	        ResultSet res = st.executeQuery(inpId);
	        while (res.next() && coun<= inId) {
	        	int id = res.getInt("id");
	        
	        	
	        String employee_type_name= res.getString("employee_type_name");
			Date created_date = res.getDate("created_date");
			Date updated_date = res.getDate("updated_date");
			String is_Active= res.getString("is_Active");
			System.out.println(id + " "+ employee_type_name +" " +created_date + " "+ updated_date + " "+ is_Active);
			coun++;
			}
	    }
	    catch (Exception ex) {
	        System.err.println(ex);
	    }
	}
	
	public static void PrintEmployee_Type(int pri) {
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
	        String sql = "SELECT * FROM Employee_Type ORDER by id LIMT"+pri;
	        ResultSet res = st.executeQuery(sql);
	        while (res.next() && coun <= pri) {
				int id = res.getInt("id");
				String employee_type_name = res.getString("employee_type_name");
				Date createddate = res.getDate("created_date");
				Date updateddate = res.getDate("updated_date");
				boolean isActive = res.getBoolean("is_Active");
				System.out.println(id + " " + employee_type_name + " " + createddate + " " + updateddate+ " " + isActive);
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
	        System.out.println("Enter Employee_Type Id to Update data");
	        int upId = scanner.nextInt();
	        
	        System.out.println("Set up a New Employee_Type Name");
	        String employee_TypeName = scanner.next();
	        
	        System.out.println("Set up a New Employee_Type Location");
	        String employee_TypeLoc = scanner.next();
	        
	        String sql = "UPDATE Employee_Type SET hotel_name='" + employee_TypeName +"',hotel_location='" + employee_TypeLoc  + "' WHERE id ='"+ upId +"'";
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
			
	        System.out.println("Enter Employee_Type Id to Get the Activated Employee_Type");
	        int actEmployee_Type = scanner.nextInt();
	       
			String sql = "UPDATE Employee_Type SET is_Active=false WHERE id<=" + actEmployee_Type;
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
			String sql = "SELECT * FROM Employee_Type WHERE id='" + delid + "'";
			
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
      
      System.out.println("Enter Employee_Type Name");
      String name = scanner.next();

      System.out.println("Enter Employee_Type Location");
      String loc = scanner.next();
      
      System.out.println("Enter Created Date");
      String crDate = scanner.next();

      System.out.println("Enter Update Date");
      String upDate = scanner.next();

      System.out.println("Enter If Active Or In Active");
      String act = scanner.next();

      String insEmployee_Type = "insert into Employee_Type values('" + id+ "'," + name + ",'" + loc + "','" +crDate +"','" + upDate +"'," + act +"')";

      Connection con = null;

      try {

          Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
          DriverManager.registerDriver(driver);
          con = DriverManager.getConnection(url, user,pass);
          Statement st = con.createStatement();

          int m = st.executeUpdate(insEmployee_Type);
          if (m >=  1)
              System.out.println("inserted successfully : " + insEmployee_Type);
          else
              System.out.println("insertion failed");
          con.close();
      }

      catch (Exception ex) {
          System.err.println(ex);
      }
  }
	}
