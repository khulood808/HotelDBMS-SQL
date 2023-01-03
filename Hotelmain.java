package hotelSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Hotelmain {
	static String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
	static String user = "sa";
	static String pass = "root";
	
	public static void menue() {
		System.out.println("\t"+"\t" + "^^^^ "+"Welcome To Our Hotel DBMS"+" ^^^^"+"\t"+"\t");
		System.out.println("\n"+"^How Can We Helep ....^"+"\n");
		System.out.println("1- Insert 10,000 hotels"+"\n");
		System.out.println("2- Insert 1 hotel"+"\n");
		System.out.println("3- Print 10 hotels"+"\n");
		System.out.println("4- Make first 10 hotels 'is_Active' = false"+"\n");
		System.out.println("5- Print Hotel Information"+"\n");
		System.out.println("8- Exit"+"\n");
	}
	
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
	public static void inpustUser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Numbers Of Hotels You Want To Enter: ");
		int userInput = sc.nextInt();
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
			 for (int i = 1; i <= 100; i++) {
				 String ins = "Insert into Hotels values(" + i + randomNumber + ",'" +
						  hotel_name + "','" + hotel_location + "','" + created_date + "','" +
						 updated_date + "'," +1 +")";
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
	

	public static void main(String[] args) {
		
		createHotelsTable();
		Room_Type.createRoom_TypeTable();
		Rooms.createRoomsTable();
		Guests.createGuestsTable();
		Employee_Type.createEmployee_TypeTable();
		Employees.createEmployeesTable();
		menue();
		inpustUser();		
		
}
}
