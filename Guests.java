package hotelSQL;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Guests {
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
}
