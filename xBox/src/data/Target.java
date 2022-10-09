package data;

import utils.XBoxDate;

public interface Target {
	public void changeRentableStatus(Rentable rentable);
	public String toString();
	public XBoxDate getDueDate();
}
