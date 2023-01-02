package hotelSQL;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Hotelmain {
	public static boolean createHotelsTable() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		String sqlDB = "CREATE TABLE Hotels"
				+ "(id INTEGER, "
				+ " hotel_name VARCHAR(20) not NULL, "
				+ " hotel_location VARCHAR(20), " 
				+ " created_date date not NULL, " 
				+ " updated_date date , " 
				+ " is_Active VARCHAR(8) not NULL,"
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
	

	public static void main(String[] args) {
		
		createHotelsTable();
		Room_Type.createRoom_TypeTable();
		Rooms.createRoomsTable();
		Guests.createGuestsTable();
		Employee_Type.createEmployee_TypeTable();
		Employees.createEmployeesTable();
}
}
