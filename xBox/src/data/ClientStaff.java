package data;

public class ClientStaff extends Client{
    private int maxBorrowedCount=2; // ExBorrowedExceed later

    public ClientStaff(String id, String name, String email, String phoneNo, String password) {
		super(id, name, email, phoneNo, password);
	}


    // fees calculation would be done later
}
