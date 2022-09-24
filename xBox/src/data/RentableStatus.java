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
	AVAILABLE(0), // * is available in hall
	RENTED_NO_PAY(1), // * is rented without payment
	TO_BE_CHECKOUT(2), // * is waiting for checkout
	TO_BE_CHECKIN(3), // * is waiting for check in
	TO_BE_PICKUP(4), // * is waiting for pickup by client
	RENTED_PAID(5), // * is held by client with payment
	RETURNED(6); // * is held by company & job is done 
	
	private int status;
	
	private RentableStatus(int status_) {
		this.status = status_;
	}
	
	public static RentableStatus statusIntToEnum(int status) {
		switch(status) {
		case 0:
			return AVAILABLE;
		case 1:
			return RENTED_NO_PAY;
		case 2: 
			return TO_BE_CHECKOUT;
		case 3:
			return TO_BE_CHECKIN;
		case 4:
			return TO_BE_PICKUP;
		case 5: 
			return RENTED_PAID;
		case 6: 
			return RETURNED;
		default:
			return INVALID;
		}
	}
	
	public static String statusIntToString(int status) {
		switch(status) {
		case 0:
			return "[status: held by person in charge, available]";
		case 1:
			return "[status: held by the client, with payment]";
		case 2: 
			return "[status: held by person in charge, to be checkout]";
		case 3:
			return "[status: held by company, to be checkin]";
		case 4:
			return "[status: held by person in charge, to be pick up by client]";
		case 5: 
			return "[status: held by client, without payment]";
		case 6: 
			return "[status: held by company, charged by company]";
		default:
			return "[status: unknown status, error]";
		}
	}
}
