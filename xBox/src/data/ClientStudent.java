package data;

public class ClientStudent extends Client{
	double discount = 0.8;
	private int maxBorrowedCount=3; // ExBorrowedExceed later

	public ClientStudent(String email, String password) {
		super(email, password);
	}

	// fees calculation would be done later

}