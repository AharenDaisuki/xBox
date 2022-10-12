package data;

import utils.XBoxDate;

@Deprecated
public interface Target {
	public void changeRentableStatus(Rentable rentable);
	public String toString();
	public XBoxDate getDueDate();
}
