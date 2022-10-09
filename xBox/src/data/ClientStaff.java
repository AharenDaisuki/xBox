package data;

public class ClientStaff extends Client{
    private int maxBorrowedCount=2; // ExBorrowedExceed later

    public ClientStaff(String email,  String password) {
		super(email, password);
	}


    // fees calculation would be done later
}
