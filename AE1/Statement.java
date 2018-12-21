import java.io.FileWriter;
import java.io.IOException;


public class Statement {
	private String outputDirectory = "statement.txt";  //defaults to creating file in project directory
	private FileWriter statement;
	
	//Constructor + Add Customer information
	public Statement(CustomerAccount myCustomer) {
		try {
			statement = new FileWriter(outputDirectory);  //initialise statement file
		} catch (IOException e) { //Exception catching
			System.out.println("Could not initialise file. Check directory name.");
			e.printStackTrace();
		}
		
		//Write first statement line (customer name and balance)
		try {
			statement.write(
					myCustomer.getName() + ", " + //Write customer name
					"your balance is " + myCustomer.getBalanceString() +  //Write initial customer balance
					String.format("%n") //Add new line
					);
		} catch (IOException e2) { //Exception catching
			System.out.println("Could not write customer information on file.");
			e2.printStackTrace();
		}
	}
	
	//Write subsequent statement lines (Wine name, cost, quantity, transaction cost, new customer balance)
	public void addWine(Wine myWine, CustomerAccount myCustomer) {
		try {
			statement.write(
					myWine.getName() +	//Write wine name
					"(£" + myWine.getBottlePrice() + "), " + //Write wine price
					myWine.getBottleNumber() + " unit(s), " + //Write wine bottles
					"total cost = " +  myWine.getStringPrice() + ", " + //Write total cost of wines
					"new balance = " + myCustomer.getBalanceString() + //Write new Customer balance
					String.format("%n") //Add new line
					);
		} catch (IOException e) { //Exception catching
			System.out.println("Could not write wine information on file.");
			e.printStackTrace();
		}
	}
	
	//finalise statement file
	public void completeStatement() {
		try {
			statement.close(); //close statement file
		} catch (IOException e) { //Exception catching
			System.out.println("Could not close file.");
			e.printStackTrace();
		}
	}

}
