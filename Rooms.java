package hotelSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Rooms {
	static String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
	static String user = "sa";
	static String pass = "root";
	
	public static boolean createRoomsTable() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		String sqlDB = "CREATE TABLE Rooms"
				+ "(id INTEGER, "
				+ " room_type_id INTEGER FOREIGN KEY REFERENCES Room_Type(id),"
				+ " hotel_id INTEGER FOREIGN KEY REFERENCES Hotels(id),"
				+ " created_date date not NULL,"
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
				System.out.println("Rooms table Created...");
				return true;
			} else {
				System.out.println("Rooms table already Created Choose other name for your table...");
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

	int room_type_id = 1;
	int hotel_id = 01;
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
			 String ins = "Insert into Rooms values(" + i + room_type_id + ",'" +
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
        String inpId = "SELECT * FROM Rooms WHERE id='" + inId +"'";
        ResultSet res = st.executeQuery(inpId);
        while (res.next() && coun<= inId) {
        	int id = res.getInt("id");
        
        	
        int room_type_id = res.getInt("room_type_id");
        int hotel_id  = res.getInt("hotel_id");
		Date created_date = res.getDate("created_date");
		Date updated_date = res.getDate("updated_date");
		String is_Active= res.getString("is_Active");
		System.out.println(id + " "+ room_type_id +" " +" "+hotel_id+" "  +created_date + " "+ updated_date + " "+ is_Active);
		coun++;
		}
    }
    catch (Exception ex) {
        System.err.println(ex);
    }
}}
