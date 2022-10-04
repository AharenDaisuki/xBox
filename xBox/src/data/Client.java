package data;

public abstract class Client implements Comparable<Client>{
    private String email;
    private String phoneNo;
    private String password;
    private int borrowedCount;


    public Client(String email, String phoneNo, String password) {	
        this.email=email;
        this.phoneNo=phoneNo;
        this.password=password;
        this.borrowedCount=0;
    }

    @Override
    public String toString() {
        return String.format("%-30s%-7d", email, borrowedCount);
    }

    public static String getHeader() {
        return String.format("%-30s%-7d", "Email", "#Borrowed");
    }

    public int compareTo(Client another) {
        if (this.id.equals(another.id)) return 0;
        else if (this.id.compareTo(another.id)>0) return 1;
        else return -1;
    }


    public boolean equals(Client another){
        return another.getName().equals(name) && another.getId().equals(id);
    }

    public String getPhoneNo(){
        return phoneNo;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void borrowItem(Rentable aRentable)
    {
        aRentable.lendOut(this);
        borrowedCount++;
    }

    public void returnItem(Rentable aRentable)
    {
        aRentable.getBack(this);
        borrowedCount--;
    }

}
