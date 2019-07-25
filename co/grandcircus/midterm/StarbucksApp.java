package co.grandcircus.midterm;

import java.util.*;

public class StarbucksApp {

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		int userChoice = 0;
		boolean breakOut = false;
		int quantityOfItems = 0;
		int checkNumber;
		long cardNumber;
		int CVV;
		double total = 0;
		double costOfItem = 0;
		double cash = 0;
		double price = 0;
		String expiration;
		String userContinue = "y";
		String choice = "";
		String userPay = "";
		String milkChoice = "no milk";
		String flavorChoice = "no flavors";
		String tempChoice = null;
		String newOrder = "";
		double subTotal = 0;
		String toastedChoice = "not toasted";
		
		List<String> order = new ArrayList<>();
		
		List<String> drinks = new ArrayList<>();
		drinks.add("Espresso");
		drinks.add("White Chocolate Mocha Frappucino Blended Coffee");
		drinks.add("Caffe Latte");
		drinks.add("Pike Place Roast");
		drinks.add("Mint Majesty Herbal Tea");
		drinks.add("Matcha Green Tea Latte");
		drinks.add("Earl Grey Black Tea");
		drinks.add("Chai Latte");

		List<String> sandwiches = new ArrayList<>();
		sandwiches.add("Double Smoked-Bacon Cheddar & Egg Breakfast Sandwich");
		sandwiches.add("Crispy Grilled Cheese Sandwich");
		sandwiches.add("Baja Black Bean Veggie Wrap");
		sandwiches.add("Chicken Caprese Sandwich");

		List<String> bakery = new ArrayList<>();
		bakery.add("Blueberry Muffin");
		bakery.add("Birthday Cake Pop");
		bakery.add("Iced Lemon Loaf Cake");
		bakery.add("Banana Nut Bread");
		
		do { //for new orders
			System.out.println("Welcome to the Grand Circus Starbucks!\n");

			do { //to add to current order

				System.out.println("- - - Menu - - -\n ");
				List<Menu> menu = StarbucksFileUtil.readFile();
				for (int i = 0; i < menu.size(); i++) {
					String item = menu.get(i).toString();
					System.out.println((i + 1) + ") " + item);
				}

				System.out.println("\nChoose an item (1-16).");
				userChoice = scan.nextInt();
				if (Validator.getCategory(userChoice).equals("drink")) {
					costOfItem = 0; //reset value
					choice = menu.get(userChoice - 1).getName();
					price = menu.get(userChoice - 1).getPrice();
					System.out.println(choice);
					costOfItem += price;
					System.out.printf("Original Price: $%.2f\n", price);
					scan.nextLine();
					System.out.println("Hot or iced? ");
					tempChoice = scan.nextLine();

					System.out.println("Would you like milk in that? y/n");
					milkChoice = scan.nextLine();
					if (milkChoice.equalsIgnoreCase("y")) {
						milkChoice = getMilkChoice(milkChoice);
						costOfItem += 0.60;

					} else if (milkChoice.equalsIgnoreCase("n")) {
						milkChoice = "no milk";
						costOfItem += 0;

					}
					
					System.out.printf("Price Now: $%.2f\n", costOfItem);
					
					System.out.println("Would you like to add any flavors to your drink? y/n");
					String flavorOption = scan.nextLine();
					
					if (flavorOption.equalsIgnoreCase("y")) {
						flavorChoice = getFlavorChoice(flavorChoice);
						costOfItem += 0.60;

					} else if (flavorOption.equalsIgnoreCase("n")){
						flavorChoice = "no flavors";
						costOfItem += 0;

					}
					System.out.printf("Price Now: $%.2f\n", costOfItem);

				} else if (Validator.getCategory(userChoice).equals("food")) {
					costOfItem = 0;
					choice = menu.get(userChoice - 1).getName();
					price = menu.get(userChoice - 1).getPrice();
					System.out.println(choice);
					costOfItem = price;
					System.out.printf("Price: $%.2f\n",costOfItem);
					scan.nextLine();
					System.out.println("Would you like that toasted? y/n ");
					String toastedOption = scan.nextLine();
					if (toastedOption.equalsIgnoreCase("y")) {
						toastedChoice = "toasted";

					} else if (toastedOption.equalsIgnoreCase("n")) {
						toastedChoice = "not toasted";

					}

				} else if (Validator.getCategory(userChoice).equals("bakery")) {
					costOfItem = 0;
					choice = menu.get(userChoice - 1).getName();
					price = menu.get(userChoice - 1).getPrice();
					System.out.println(choice);
					costOfItem = price;
					System.out.printf("Price: $%.2f\n", costOfItem);
					scan.nextLine();
					System.out.println("Delicious choice!");

				}
				
				else { //if didn't choose a valid menu option 
					System.out.println("Sorry! That's not a valid option, please select again from the menu. ");
					breakOut = true;
				}
				
				if(breakOut == false) { //if didn't hit else above
					boolean validated = false;
					do {
						try {
							System.out.println("\nHow many of these items would you like? ");
							quantityOfItems = scan.nextInt();
							validated = true;
						}
						catch(InputMismatchException e) {
							scan.nextLine(); 
							validated = false;
						}
					}
					while(!validated);
					subTotal += (costOfItem*quantityOfItems);
					int counter = 0;
					do {
						if (drinks.contains(choice)) {
							String orderDrinks = (choice + ", " + tempChoice + ", " + milkChoice + ", " + flavorChoice
									+ ", " + "$" + costOfItem);
							order.add(orderDrinks);
		
						} else if (sandwiches.contains(choice)) {
							String orderFood = (choice + ", " + toastedChoice + ", " + "$" + costOfItem);
							order.add(orderFood);
						} else if (bakery.contains(choice)) {
							String orderBakedGoods = (choice + ", " + "$" + costOfItem);
							order.add(orderBakedGoods);
						}
						counter++;
					}
					while(counter<quantityOfItems);
					System.out.println("Here's your order: ");
					for (String o : order) {
						System.out.println(o.toString());
					}
					System.out.printf("\nSubtotal: $%.2f \n", subTotal);
					System.out.printf("Sales Tax: $%.2f \n", subTotal * .06);
					total = subTotal + (subTotal * .06);
					System.out.printf("Total: $%.2f \n", (total));
					scan.nextLine();
					System.out.println("\nDoes this complete your order? Select (y/n): ");
					userContinue = scan.nextLine();
					
					if (userContinue.equalsIgnoreCase("y")) {
						
						System.out.println("\nAre you paying with cash, check, or card?");
						userPay = scan.nextLine();
	
						if (userPay.equalsIgnoreCase("cash")) {
							System.out.println("How much cash are you paying with? ");
							cash = scan.nextDouble();
							double balance = cash-total;
							
							if ((balance) < 0 ) {
								System.out.printf("Your balance is: $%.2f\n", Math.abs(balance));
							} else if ((balance) > 0) {
								System.out.printf("Great, and here is your change: $%.2f\n", (balance));
							}
							
							System.out.println("\nThank you for visiting Starbucks, have a nice day!");
	
							break;
	
						} else if (userPay.equalsIgnoreCase("check")) {
							do {
								System.out.println("Enter your four-digit check number: ");
								checkNumber = scan.nextInt();
								break;
							}
							while(!Validator.validateCheck(checkNumber));
							System.out.println("\nThank you for visiting Starbucks, have a nice day!");
	
							break;
	
						} else if (userPay.equalsIgnoreCase("card")) {
							do {
								System.out.println("Enter your twelve-digit card number: ");
								cardNumber = scan.nextLong();
								break;
							}
							while(!Validator.validateCard(cardNumber));
							System.out.println("Enter the card's four-digit expiration date, leaving out the '/': ");
							expiration = scan.next();
							do {
								System.out.println("Enter the CVV: ");
								CVV = scan.nextInt();
								break;
							}
							while(!Validator.validateCVV(CVV));
							System.out.println("\nThank you for visiting Starbucks, have a nice day!");
	
						}
					} else {
					}
				}
				else {
					userContinue = "n";
				}
			} while (userContinue.equalsIgnoreCase("n")); // end do 1/2
			
			System.out.println("\nStart a new order? (y/n)");
			 newOrder = scan.next();
			 userChoice = 0;
			 breakOut = false;
			 quantityOfItems = 0;
			 checkNumber = 0;
			 cardNumber = 0;
			 CVV = 000;
			 costOfItem = 0;
			 cash = 0;
			 price = 0;
			 expiration = "00/00";
			 userContinue = "y";
			 choice = "";
			 userPay = "";
			 milkChoice = "no milk";
			 flavorChoice = "no flavors";
			 tempChoice = null;
			 toastedChoice = "not toasted";
			 subTotal = 0;
			 total = 0;
			 order.clear();
		} while (newOrder.equalsIgnoreCase("y")); // end do 2/2
	}// end main method

	public static String getMilkChoice(String milkChoice) {

		System.out.println("Choose which kind from the list below: \n ");
		List<String> milk = new ArrayList<>();
		milk.add("Reduced fat");
		milk.add("Fat free");
		milk.add("Whole milk");
		milk.add("Almond");
		milk.add("Coconut");
		milk.add("Soy");

		for (int i = 0; i < milk.size(); i++) {
			System.out.println(milk.get(i).toString());
		}
		return milkChoice = scan.nextLine();
	}

	public static String getFlavorChoice(String flavorChoice) {

		System.out.println("Choose which kind from the list below: \n ");
		List<String> milk = new ArrayList<>();
		milk.add("Vanilla");
		milk.add("Peppermint");
		milk.add("Raspberry");
		milk.add("Caramel");
		milk.add("Toffee Nut");
		milk.add("Hazelnut");

		for (int i = 0; i < milk.size(); i++) {
			System.out.println(milk.get(i).toString());
		}
		return flavorChoice = scan.nextLine();

	}

	public static Menu getItem(int userChoice) {
		List<Menu> menu = StarbucksFileUtil.readFile();
		Menu item = menu.get(menu.size() - 1);
		try {
			item = menu.get(userChoice);

		} // end try
		catch (IndexOutOfBoundsException e) {
		}
		return item;
	}// end getItem

}// end StarbucksApp
