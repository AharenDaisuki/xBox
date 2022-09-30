package data;

public class ClientStudent extends Client{
	double discount = 0.8;
	private int maxBorrowedCount=3; // ExBorrowedExceed later

	public ClientStudent(String id, String name, String email, String phoneNo, String password) {
		super(id, name, email, phoneNo,password);
	}

	// fees calculation would be done later

}