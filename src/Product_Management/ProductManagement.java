package Product_Management;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DBUtils.DbUtils;

public class ProductManagement {

	public static void productDB () throws IOException, SQLException {
		boolean productContinue = true;
		Product product = new Product();

		while (productContinue) {

			System.out.println("########## Welcome to Product Management System ##########");
			System.out.println("1. Add Product");
			System.out.println("2. Update Product");
			System.out.println("3. Search Product");
			System.out.println("4. Delete Product");
			System.out.println("5. Print Product");
			System.out.println("6. Exit Product Management");

			Scanner scanner = new Scanner(System.in);
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				System.out.println("#### Add Product ####");

				System.out.println("enter the product name");
				product.name = scanner.next();

				System.out.println("enter the product quantity");
				product.qty = scanner.nextInt();

				System.out.println("enter the product price");
				product.prc = scanner.nextInt();

				String query = "insert into product(product_name , product_qty, product_prc)values('" + product.name + "', '"
						+ product.qty + "', '" + product.prc + "')";

				DbUtils.executeQuery(query);
				break;

			case 2:
				System.out.println("#### Update Product ####");

				System.out.println("enter the product name to update");
				String productToUpdate = scanner.next();

				String queryToUpdate = "select count(*) from product where product_name ='" + productToUpdate + "'";

				ResultSet rSet = DbUtils.selectQuery(queryToUpdate);
				rSet.next();
				int cntProduct = rSet.getInt(1);

				if (cntProduct == 1) {
					System.out.println("enter new product name");
					String newName = scanner.next();

					System.out.println("enter new quantity");
					int newQty = scanner.nextInt();

					System.out.println("enter new price");
					int newprice = scanner.nextInt();

					String Updatequery = "update product set product_name = '" + newName + "', product_qty = '" + newQty
							+ "', product_prc = '" + newprice + "' where product_name='" + productToUpdate + "'";
					
					DbUtils.executeQuery(Updatequery);
					

				} else {
					System.out.println("no product found");
				}

				break;

			case 3:
				System.out.println("#### Search Product ####");

				System.out.println("enter the product name to search");
				String productToSearch = scanner.next();

				String queryToSearch = "select * from product where product_name ='" + productToSearch + "'";

				ResultSet searchRs = DbUtils.selectQuery(queryToSearch);

				while (searchRs.next()) {
					System.out.println(searchRs.getString(1) + " " + searchRs.getString(2) + " " + searchRs.getString(3));
				}

				break;

			case 4:
				System.out.println("#### Delete Product ####");
				System.out.println("enter the product name to Delete");
				String productToDelte = scanner.next();

				String deleteQuery = "delete from product where product_name='" + productToDelte + "'";
				DbUtils.executeQuery(deleteQuery);

				break;

			case 5:
				System.out.println("#### Print Product ####");

				String queryToPrint = "select * from product";

				ResultSet printRs = DbUtils.selectQuery(queryToPrint);

				while (printRs.next()) {
					System.out.println(printRs.getString(1) + " " + printRs.getString(2) + " " + printRs.getString(3));
				}
				
				break;

			case 6:

				productContinue = false;
				System.out.println("#### Exiting the Product ####");
				break;

			default:
				System.out.println("Wrong Choice");

			}
			scanner.close();

		}
		

	}
}
