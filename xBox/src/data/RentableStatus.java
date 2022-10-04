/**
 * 
 */
package data;

/**
 * @author xyli45
 *
 * Description: rentable status enumeration class
 */
public enum RentableStatus {
	INVALID(-1), // invalid status
	AVAILABLE(0), // * is available
	PENDING_PAYMENT(1), // * is rented without payment
	BORROWED(2), // * is NOT available
	PENDING_RETURN(3); // * to be returned 
	
	private final int status;
	
	private RentableStatus(int status_) {
		this.status = status_;
	}
	
	// TODO: modify
	@Override
	public String toString() {
		switch(this.status) {
		case 0:
			return "[status: Available]";
		case 1:
			return "[status: Pending]";
		case 2: 
			return "[status: Borrowed]";
		case 3:
			return "[status: Returned]";
		default:
			return "[status: unknown]";
		}
	}
}
