package hotelSQL;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Room_Type {
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

}
