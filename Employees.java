package hotelSQL;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Employees {
	public static boolean createEmployeesTable() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		String sqlDB = "CREATE TABLE Employees"
				+ "(id INTEGER, "
				+ " employee_type_id INTEGER FOREIGN KEY REFERENCES Employee_Type(id),"
				+ " room_id INTEGER FOREIGN KEY REFERENCES Hotelmain(id), " 
				+ " created_date date not NULL, " 
				+ " updated_date date , " 
				+ " is_Active Boolean not NULL)"
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
}
