package data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import debug.DebugConfig;

public class Database {
    private static Database database = new Database();
    
    private RentableStorer rentableStorer;
    private RecordStorer recordStorer; 
    private ClientStorer clientStorer;
    private RequestStorer requestStorer;

    private Database(){
        rentableStorer=RentableStorer.getInstance();
        recordStorer=RecordStorer.getInstance();
        clientStorer=ClientStorer.getInstance();
        requestStorer=RequestStorer.getInstance();
    }

    public static Database getInstance(){
        return database;
    }

    public void initialize(String[] filePathNames) throws IOException
    {
        rentableStorer.readFromJson(filePathNames[0]);
        recordStorer.readFromJson(filePathNames[1]);
        clientStorer.readFromJson(filePathNames[2]);
        requestStorer.readFromJson(filePathNames[3]);
    }

    public void storeUp(String[] dstPathName) throws IOException
    {
        rentableStorer.writeToJson(dstPathName[0]);
        recordStorer.writeToJson(dstPathName[1]);
        clientStorer.writeToJson(dstPathName[2]);
        requestStorer.writeToJson(dstPathName[3]);
    }
    // save in json
}
