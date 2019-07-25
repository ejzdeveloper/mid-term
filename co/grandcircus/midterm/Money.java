package co.grandcircus.midterm;

public class Money extends Menu{

	private double costOfItem;
	
	public Money() {
		
	}
	public Money(double costOfItem) {
		this.costOfItem = costOfItem;
	}
	public double getCostOfItem() {
		return costOfItem;
	}
	public void setCostOfItem(double costOfItem) {
		this.costOfItem = costOfItem;
	}
	
	
}
