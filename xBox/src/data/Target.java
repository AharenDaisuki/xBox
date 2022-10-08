package data;

public interface Target {
	public void changeRentableStatus(Rentable rentable);
	public String toString();
	public Day getDueDate();
}
