package DBUtils;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import Product_Management.*;
import User_Management.*;

public class DbUtils {

	private static Connection connection;
	private static Statement statement;

	static {

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "samir");
			statement = connection.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static ResultSet selectQuery(String query) throws SQLException {
		ResultSet resultSet = statement.executeQuery(query);
		return resultSet;

	}

	public static void executeQuery(String query) throws SQLException {
		statement.execute(query);

	}

	public static void main(String[] args) throws SQLException, IOException {

		boolean shallIkeepRunningCode = true;
		while (shallIkeepRunningCode) {
			Scanner sc = new Scanner(System.in);
			System.out.println("What would you like to do today");
			System.out.println("1 Product Managemenet");
			System.out.println("2 User Management");
			System.out.println("3 Exit the Appplication");
			int option = sc.nextInt();
			
			switch (option) {
			case 1:
				try {
					ProductManagement.productDB();
				} catch (IOException | SQLException e) {
					
					e.printStackTrace();
				}
				break;

			case 2:
				try {
					UserManagement.userDB();
				} catch (IOException | SQLException e) {
					
					e.printStackTrace();
				}
				break;

			case 3:
				System.out.println("Exiting the Application....");
				shallIkeepRunningCode = false;
				break;
				

			}
			sc.close();
		}
		
	}

}
