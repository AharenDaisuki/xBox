package data;


public abstract class Rentable implements Comparable<Rentable>{
    private String id,name;
    private RentableStatus status;


    public Rentable(String i, String n){
        id=i;
        name=n;
        status=new RentableStatusAvailable();
    }

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
        
    }


    @Override
    public int compareTo(Rentable r) {
        return this.id.compareTo(r.id);
    }


    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public abstract String getType();
    


    @Override
    public String toString() {
        return String.format("%-5s%-20s%-9s", id, name, status.toString());
    }

    public String getIdAndName() {
        return id+" "+name;
    }

    public RentableStatus getStatus(){
        return status;
    }

    public void setAvailale(){
        status=new RentableStatusAvailable();
    }

    public void setOccupied(Day d, Client c){
        status=new RentableStatusOccupied(d,c);
    }
}
