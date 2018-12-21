public class CustomerAccount {
	private String name;
	private int balancePence;

	//Constructor for Customer Account
	public CustomerAccount(String name, double balance) {
		
		//Set name
		this.name = name;
		
		//Convert customer balance from pounds to pence and set as customer balance
		balancePence = this.convertToPence(balance);
	}
	
	//Getter for name
	public String getName() {
		return name;
	}

	//Getter for balance (UNUSED)
	public int getBalancePence() {
		return balancePence;
	}
	
	
	
	//Convert money from pounds to pence (double to int) (eg 1.23->123)
	private int convertToPence(double poundsBalance) {
		
		//convert pounds to pence
		double newBalance = poundsBalance*100;
		
		//cast double into integer
		int penceBalance = (int) newBalance;
		
		return penceBalance;
	}
	
	//Convert money from pence to pounds(int to double)
	private double convertToPounds(int penceBalance) {
		
		//cast int to double and convert pounds to pence
		double poundBalance = (double) penceBalance/100;
		return poundBalance;
	}
	
	
	//Getter for balance in pounds and in string format
	public String getBalanceString() {
		
		//Convert balance from pence to pounds
		double balance =  this.convertToPounds(balancePence);
		
		//Check if balance is positive/negative and format string accordingly
		if (balance>=0) { //if balance is positive or 0
			return 
				"£" + //add pound symbol
				String.format("%.2f", balance);  //format balance to 2 decimal points eg 1.2334 -> £1.23
		
		
		}else { //if balance is negative, 
			return 
				"-£" +  //make sure - symbol is before £ sign
				String.format("%.2f",(Math.abs(balance))); //get positive balance and format into 2 decimal points, eg -1.234 -> -£1.23
		}
	
	}
	
	
	//Update balance function
	public void setBalance(double poundsCost) {
		//convert the cost from pounds to pence (double->int)
		int penceCost = this.convertToPence(poundsCost);
		balancePence += penceCost;
		//When cost is positive (buying wine), customer balance increases (customer gets money from store)
		//When cost is negative (returning wine), customer balance decreases (customer owes money to store)
	}
	

	
	
}
