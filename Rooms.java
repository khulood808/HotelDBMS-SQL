package hotelSQL;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Rooms {
	public static boolean createRoomsTable() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		String sqlDB = "CREATE TABLE Rooms"
				+ "(id INTEGER, "
				+ " room_type_id INTEGER FOREIGN KEY REFERENCES Room_Type(id),"
				+ " hotel_id INTEGER FOREIGN KEY REFERENCES Hotelmain(id),"
				+ " created_date date not NULL,"
				+ " updated_date date, " 
				+ " is_Active Boolean not NULL"
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
}
