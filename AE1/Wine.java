
public class Wine {
	private String name;
	private int bottles;
	private double bottlePrice;
	
	//Constructor
	public Wine(String name, int bottles, double bottlePrice) {
		this.name = name;
		this.bottles = bottles;
		this.bottlePrice = bottlePrice;
	}
	
	//Getter for name
	public String getName() {
		return name;
	}

	//Getter for number of bottles
	public int getBottleNumber() {
		return bottles;
	}

	//Getter for bottle price
	public double getBottlePrice() {
		return bottlePrice;
	}

	//Return total price of wine order. Always returns positive values
	public double getTotalPrice() {
		double totalPrice = bottles*bottlePrice;
		
		//if returning wine
		if (bottles<=0){
			//calculate 80%
			double refundPrice = (totalPrice*80)/100;
			return refundPrice;
		} else {
			return totalPrice;
		}
	}
	
	public String getStringPrice() {
		//gets price as an int and converts in into a string with the £ symbol
		double price = getTotalPrice();
		
		//if buying a bottle
		if (price>=0) {
			return "£" + String.format("%.2f", price);
		//otherwise, if returning a bottle is negative, make sure - is before £ sign
		}else {
			//turn balance into positive and print only 2 decimal points
			return "-£" +  String.format("%.2f",(Math.abs(price)));
		}
		
	}
	
	//returning a comprehensive string of buying/returning wine
	public String printTransaction(CustomerAccount myCustomer) {
		return "Transaction Complete." +	//print wine name
			"\n Wine Name: " + name +		//print wine quantity
			"\n Wine Bottles: " + bottles +		//print bottle number 
			"\n Cost per Bottle: " + "£" + String.format("%.2f", bottlePrice) + //print cost and present in £ format
			"\n Total Cost: " + getStringPrice() + //print total price
			"\n " + myCustomer.getName() + ", your new balance is: " + myCustomer.getBalanceString() //print customer information
			;
	}	
}
