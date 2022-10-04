package data;


public class Database {
    private static Database database;
    
    private RentableStorer rentableStorer;
    private RecordStorer recordStorer; 
    private ClientStorer clientStorer;
    private RequestStorer requestStorer;

    private Database(RentableStorer aRentableStorer, RecordStorer aRecordStorer, 
                    ClientStorer aClientStorer, RequestStorer aRequestStorer){
        rentableStorer=aRentableStorer;
        recordStorer=aRecordStorer;
        clientStorer=aClientStorer;
        requestStorer=aRequestStorer;
    }

    public static Database getInstance(){
        return database;
    }

    

    // save in json
}
