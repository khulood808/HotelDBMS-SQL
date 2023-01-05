package hotelSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Hotel {
	static String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
	static String user = "sa";
	static String pass = "root";
	
	
	public static boolean createHotelsTable() {
		String sqlDB = "CREATE TABLE Hotels"
				+ "(id INTEGER, "
				+ " hotel_name VARCHAR(20) not NULL, "
				+ " hotel_location VARCHAR(20), " 
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
				System.out.println("Created table in given database...");
				return true;
			} else {
				System.out.println(" table already Created in given database...");
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

	String hotel_name = "Grand Hayat";
	String hotel_location = "Qurum";
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
			 String ins = "Insert into Hotels values(" + i + randomNumber + ",'" +
					  hotel_name + "','" + hotel_location + "','" + created_date + "','" +
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
        String inpId = "SELECT * FROM Hotels WHERE id='" + inId +"'";
        ResultSet res = st.executeQuery(inpId);
        while (res.next() && coun<= inId) {
        	int id = res.getInt("id");
        
        String hotel_name = res.getString("hotel_name");
		String hotel_location = res.getString("hotel_location");
		Date created_date = res.getDate("created_date");
		Date updated_date = res.getDate("updated_date");
		String is_Active= res.getString("is_Active");
		System.out.println(id + " "+ hotel_name +" " + hotel_location + " " +created_date + " "+ updated_date + " "+ is_Active);
		coun++;
		}
    }
    catch (Exception ex) {
        System.err.println(ex);
    }
}
public static void Printhotels(int pri) {
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
        String sql = "SELECT * FROM Hotels ORDER by id LIMT"+pri;
        ResultSet res = st.executeQuery(sql);
        while (res.next() && coun <= pri) {
			int id = res.getInt("id");
			String hotelname = res.getString("hotel_name");
			String hotellocation = res.getString("hotel_location");
			Date createddate = res.getDate("created_date");
			Date updateddate = res.getDate("updated_date");
			boolean isActive = res.getBoolean("is_Active");
			System.out.println(id + " " + hotelname + " " + hotellocation + " " + createddate + " " + updateddate
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
        System.out.println("Enter Hotel Id to Update data");
        int upId = scanner.nextInt();
        
        System.out.println("Set up a New Hotel Name");
        String hotelName = scanner.next();
        
        System.out.println("Set up a New Hotel Location");
        String hotelLoc = scanner.next();
        
        String sql = "UPDATE Hotels SET hotel_name='" + hotelName +"',hotel_location='" + hotelLoc  + "' WHERE id ='"+ upId +"'";
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
		
        System.out.println("Enter Hotel Id to Get the Activated Hotels");
        int actHotel = scanner.nextInt();
       
		String sql = "UPDATE Hotels SET is_Active=false WHERE id<=" + actHotel;
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
			String sql = "select * from hotel where id='" + delid + "'";
			
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
       
       System.out.println("Enter Hotel Name");
       String name = scanner.next();

       System.out.println("Enter Hotel Location");
       String loc = scanner.next();
       
       System.out.println("Enter Created Date");
       String crDate = scanner.next();

       System.out.println("Enter Update Date");
       String upDate = scanner.next();

       System.out.println("Enter If Active Or In Active");
       String act = scanner.next();

       String insHotel = "insert into Hotels values('" + id+ "'," + name + ",'" + loc + "','" +crDate +"','" + upDate +"'," + act +"')";

       Connection con = null;

       try {

           Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
           DriverManager.registerDriver(driver);
           con = DriverManager.getConnection(url, user,pass);
           Statement st = con.createStatement();

           int m = st.executeUpdate(insHotel);
           if (m >=  1)
               System.out.println("inserted successfully : " + insHotel);
           else
               System.out.println("insertion failed");
           con.close();
       }

       catch (Exception ex) {
           System.err.println(ex);
       }
   }
	}





