package data;


public abstract class Rentable implements Comparable<Rentable>{
    private String id;
    private RentableStatus status;


    public Rentable(String aId){
        id=aId;
        status=new RentableStatusAvailable();
    }

    public void lendOut(Client aClient)
    {

        Day d=SystemDate.getInstance().clone();
        status=new RentableStatusOccupied(d, aClient);
        RentableManager rm=RentableManager.getInstance();
        rm.lendOutRentable(this);
    }

    public void getBack(Client aClient)
    {
        status=new RentableStatusAvailable();
        RentableManager rm=RentableManager.getInstance();
        rm.getBackRentable(this);
        
    }


    @Override
    public int compareTo(Rentable aRentable) {
        return this.id.compareTo(aRentable.id);
    }


    public String getId(){
        return id;
    }

    public abstract String getType();
    
    @Override
    public String toString() {
        return String.format("%-5s%-9s", id, status.toString());
    }

    public RentableStatus getStatus(){
        return status;
    }

    public void setAvailale(){
        status=new RentableStatusAvailable();
    }

    public void setOccupied(Day d, Client aClient){
        status=new RentableStatusOccupied(d,aClient);
    }

    public void setRequested(Client aClient)
    {
    	status=new RentableStatusRequested(aClient);
    }
}
