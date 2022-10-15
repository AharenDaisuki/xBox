package data;


public abstract class Rentable{
    private String id;
    private RentableStatus status;

    public Rentable(String rentableID_, RentableStatus status_){
        id = rentableID_;
        status = status_;
    }

    public String getId(){
        return id;
    }

    public abstract String getType();
    
    public abstract double getPrice();

    public String getStatusStr(){
        return status.getStatus();
    }
    
    public RentableStatus getStatus() {
        return this.status;
    }

    public void setStatus(RentableStatus status_) {
    	this.status = status_;
    }
}
