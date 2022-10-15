/**
 * 
 */
package data;

/**
 * @author lixiaoyang
 *
 */
public class RentableStatusPending implements RentableStatus {
    public static final String statusName = "Pending";
    private Client client;
    
    public RentableStatusPending(Client aClient) { this.client = aClient; }
    
    @Override
    public String toString(){
        return String.format("held by %s", client.getEmail());
    }
    
    @Override
    public String getStatus() {
        return "Pending";
    }
}
