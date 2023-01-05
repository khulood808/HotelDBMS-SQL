package hotelSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean exit= true;
		do {
			System.out.println("\t"+"\t" + "^^^^ "+"Welcome To Our Hotel DBMS"+" ^^^^"+"\t"+"\t");
			System.out.println("\n"+"^How Can We Helep ....^"+"\n");
			System.out.println("1- Insert 10,000 hotels"+"\n");
			System.out.println("2- Insert 1 hotel"+"\n");
			System.out.println("3- Print 10 hotels"+"\n");
			System.out.println("4- Make first 10 hotels 'is_Active' = false"+"\n");
			System.out.println("5- Print Hotel Information"+"\n");
			System.out.println("6- Exit"+"\n");
			
			int selOption = scanner.nextInt();
			
			switch (selOption) {
			case 1:
				System.out.println("Enter Numbers Of Hotels You Want To Enter: ");
				int number = scanner.nextInt();
				Hotel.inpustUser(number);
				break;
				
			case 2:
				Hotel.inpustUser(1);
				break;
				
			case 3:
				Hotel.Printhotels(10);
				break;
				
			case 4:
				Hotel.makeIsActiveFalseById();
				break;
				
			case 5:
				System.out.println("Enter Numbers Of Hotels You Want To Enter: ");
				int info = scanner.nextInt();
				Hotel.Printhotels(info);
				break;
			}
		}while (exit);
		
		}
}
