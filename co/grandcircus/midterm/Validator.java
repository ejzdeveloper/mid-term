package co.grandcircus.midterm;

public class Validator {
	public static String getCategory(int userChoice) {
		if(userChoice >= 1 && userChoice <= 8) {
			return "drink";
		}
		if(userChoice >= 9 && userChoice <= 12) {
			return "food";
		}
		if(userChoice >= 13 && userChoice <= 16) {
			return "bakery";
		}
		return "not valid";
	}//end getCategory
	
	public static boolean validateCheck(int checkNumber) {
		int length = String.valueOf(checkNumber).length();
		if(length!=4) {
			return false;
		}
		return true;
	}//validateCheck
	
	public static boolean validateCard(long cardNumber) {
		int length = String.valueOf(cardNumber).length();
		if(length!=12) {
			return false;
		}
		return true;
	}//validateCard
	
	public static boolean validateCVV(int CVV) {
		int length = String.valueOf(CVV).length();
		if(length!=3) {
			return false;
		}
		return true;
	}//validateCVV
}
