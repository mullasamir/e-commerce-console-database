package User_Management;

import java.beans.Statement;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DBUtils.DbUtils;


public class UserManagement {
	public static void userDB() throws IOException, SQLException {
		boolean userContinue = true;
		
		User user = new User();
		

		while (userContinue) {

			System.out.println("########## Welcome to Product Management System ##########");
			System.out.println("1. Add User");
			System.out.println("2. Update User");
			System.out.println("3. Search User");
			System.out.println("4. Delete User");
			System.out.println("5. Print User");
			System.out.println("6. Exit User Management");

			Scanner scanner = new Scanner(System.in);
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				System.out.println("#### Add User ####");

				System.out.println("enter the first name");
				user.fName = scanner.next();

				System.out.println("enter the last name");
				user.lName = scanner.next();

				System.out.println("enter the age");
				user.age = scanner.nextInt();

				System.out.println("enter the gender");
				user.gender = scanner.next();

				System.out.println("enter the email");
				user.email = scanner.next();

				System.out.println("enter the password");
				user.password = scanner.next();

				String query = "insert into e_user(fname , lname, age, gender, email, pass)values('" + user.fName
						+ "', '" + user.lName + "', '" + user.age + "', '" + user.gender + "', '" + user.email + "', '"
						+ user.password + "')";

				DbUtils.executeQuery(query);

				break;

			case 2:
				System.out.println("#### Update Product ####");

				System.out.println("enter the user name to update");
				String userToUpdate = scanner.next();

				String queryToUpdate = "select count(*) from e_user where fname ='" + userToUpdate + "'";

				ResultSet rSet = DbUtils.selectQuery(queryToUpdate);
				rSet.next();
				int cntUser = rSet.getInt(1);

				if (cntUser >= 1) {
					System.out.println("enter the first name");
					String fnm = scanner.next();

					System.out.println("enter the last name");
					String lnm = scanner.next();

					System.out.println("enter the age");
					int age = scanner.nextInt();

					System.out.println("enter the gender");
					String gender = scanner.next();

					System.out.println("enter the email");
					String email = scanner.next();

					System.out.println("enter the password");
					String pass = scanner.next();

					String Updatequery = "update e_user set fname = '" + fnm + "',  lname= '" + lnm + "', age= '" + age + "', gender = '"
							+ gender + "' , email = '" + email + "' , pass = '" + pass + "' where fname ='"
							+ userToUpdate + "'";

					DbUtils.executeQuery(Updatequery);

				} else {
					System.out.println("no user found");
				}

				break;

			case 3:
				System.out.println("#### Search Product ####");

				System.out.println("enter the product name to search");
				String userToSearch = scanner.next();

				String queryToSearch = "select * from e_user where fname ='" + userToSearch + "'";

				ResultSet searchRs = DbUtils.selectQuery(queryToSearch);
				
				
				while(searchRs.next()) {
					System.out.println(searchRs.getString(1) + " " + searchRs.getString(2) + " " + searchRs.getString(3)
							+ " " + searchRs.getString(4) + " " + searchRs.getString(5) + " " + searchRs.getString(6));
				}

				break;

			case 4:
				System.out.println("#### Delete Product ####");
				System.out.println("enter the product name to Delete");
				String userToDelte = scanner.next();

				String deleteQuery = "delete from e_user where fname='" + userToDelte + "'";
				DbUtils.executeQuery(deleteQuery);

				break;

			case 5:
				System.out.println("#### Print Product ####");

				String queryPrint = "select * from e_user";
				ResultSet rsPrint = DbUtils.selectQuery(queryPrint);
				
				while (rsPrint.next()) {
					System.out.println(rsPrint.getString(1) + " " + rsPrint.getString(2) + " " + rsPrint.getString(3)
							+ " " + rsPrint.getString(4) + " " + rsPrint.getString(5) + " " + rsPrint.getString(6));
				}
				
				break;

			case 6:
				
				userContinue = false;
				System.out.println("#### Exiting the User ####");
				break;

			default:
				System.out.println("Wrong Choice");

			}

			scanner.close();
		}

	}

}
