package test;

import org.junit.Test;
import app.*;
import data.*;
import cmd.*;
public class TestMain {

    @Test
    public void testmain() {
    Main app=new Main();
    Bag bag= new Bag("1",new RentableStatusAvailable());
    }
}
