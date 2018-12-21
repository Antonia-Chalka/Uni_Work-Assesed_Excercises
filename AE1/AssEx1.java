import java.util.Scanner;

public class AssEx1 {
	
	public static void main(String[] args) {
		//Initialise Scanner
		Scanner myScanner = new Scanner(System.in);
						
		//Get customer name
		System.out.println("Hello. Please enter you name:");	
		String customerName = myScanner.nextLine();
		
		//Get customer balance
		System.out.println("Please enter your balance:");	
		double customerBalance;
		//Check and prevent the user from entering a non-double value
		while (true) {
			if (myScanner.hasNextDouble()) {
				customerBalance = myScanner.nextDouble();
				break;
			} else {
				System.out.println("Could not find number. Please try again:");
				myScanner.next(); //remove token that does not contain double
			}
		}
		
		//Set up customer information
		CustomerAccount myCustomer = new CustomerAccount(customerName, customerBalance);
		
		//Print welcome message
		System.out.println("Welcome " + myCustomer.getName() + ". Your balance is " + myCustomer.getBalanceString());
		
		//Write Customer info to file
		Statement myStatement = new Statement(myCustomer);
		
		//Start the loop where customer makes transactions 						
		while (true) {
			myScanner.nextLine();	//Get rid of any white space that could accidentally end loop
			
			//Get Wine Name
			System.out.println("Please enter the name of the wine: \n(To exit, simply leave the name blank)");
			String wineName = myScanner.nextLine();
			//Break loop if wine name is empty
			if ("".equals(wineName)) {
				System.out.print("Goodbye.");
				break;
				}
						
			//Get wine quantity
			System.out.println("Please enter the number of bottles: (-ve for returns)" );
			int wineBottles;
			//Check and prevent the user from entering a non-integer value
			while (true) {
				if (myScanner.hasNextInt()) {
					wineBottles = myScanner.nextInt();
					break;
				} else {
					System.out.println("Could not find integer. Please try again:");
					myScanner.next(); //remove token that does not contain an integer
				}
			}
						
			//Get wine price
			System.out.println("Please enter price per bottle:");
			double bottlePrice;
			//Check and prevent the user from entering a non-double value
			while (true) {
				if (myScanner.hasNextDouble()) {
					bottlePrice = myScanner.nextDouble();
					break;
				} else {
					System.out.println("Could not find number. Please try again:");
					myScanner.next(); //remove token that does not contain a double
				}
			}	
			
			//Create wine object
			Wine myWine = new Wine(wineName, wineBottles, bottlePrice);
						
			//Calculate transaction price and update customer balance
			myCustomer.setBalance(myWine.getTotalPrice());
						
			//Print summary of transaction and new balance
			System.out.println(myWine.printTransaction(myCustomer));
							
			//Write transaction to statement file
			myStatement.addWine(myWine, myCustomer);
		}
		
		//Close scanner
		myScanner.close();
		
		//Close statement file
		myStatement.completeStatement();	
	}
}
