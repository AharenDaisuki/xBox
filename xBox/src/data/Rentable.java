package data;


public abstract class Rentable implements Comparable<Rentable>{
    private String id;
    private RentableStatus status;


    public Rentable(String rentableID_, RentableStatus status_){
        id = rentableID_;
        status = status_;
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
    
    //@Override
    //public String toString() {
    //    return String.format("%-5s%-70s", id, status.toString());
    //}
    
    @Override
    public int compareTo(Rentable o) {
        return this.id.compareTo(o.id);
    }
}
