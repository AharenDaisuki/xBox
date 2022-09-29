package data;

public class Client implements Comparable<Client>{
    private String id;
    private String name;
    private String email;
    private int borrowedCount; //we can set max borrowed later on

    public Client(String id, String name, String e) {	
        this.id=id;
        this.name=name;
        this.email=e;
        this.borrowedCount=0;
    }

    @Override
    public String toString() {
        return String.format("%-5s%-9s%7d", id, name, borrowedCount);
    }

    public static String getHeader() {
        return String.format("%-5s%-9s%11s%11s", "ID", "Name", "#Borrowed");
    }

    public int compareTo(Client another) {
        if (this.id.equals(another.id)) return 0;
        else if (this.id.compareTo(another.id)>0) return 1;
        else return -1;
    }


    public boolean equals(Client another){
        return another.getName().equals(name) && another.getId().equals(id);
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public void borrowItem(Rentable i)
    {
        i.lendOut(this);
        borrowedCount++;
    }

    public void returnItem(Rentable i)
    {
        i.getBack(this);
        borrowedCount--;
    }

    public String getIdAndName(){
        return id+" "+name;
    }
}
