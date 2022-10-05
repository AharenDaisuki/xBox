package data;


public abstract class Rentable{
    private String id;
    private RentableStatus status;


    public Rentable(String rentableID_){
        id = rentableID_;
    }
    
    /*
    public void lendOut(Client c)
    {

        Day d=SystemDate.getInstance().clone();
        status=new RentableStatusOccupied(d, c);
        RentableManager rm=RentableManager.getInstance();
        rm.lendOutRentable(this);
    }

    public void getBack(Client c)
    {
        status=new RentableStatusAvailable();
        RentableManager rm=RentableManager.getInstance();
        rm.getBackRentable(this);
        
    }*/

    public String getId(){
        return id;
    }

    public abstract String getType();
    

    public RentableStatus getStatus(){
        return status;
    }

    public void setStatus(RentableStatus status_) {
    	this.status = status_;
    }
}
