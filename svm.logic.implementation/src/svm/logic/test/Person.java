package svm.logic.test;

import test.IAddress;
import test.IPerson;

/**
 * Projectteam
 * Date: 24.10.12
 */
public class Person implements IPerson {

    String name="TestName";
    IAddress address=new Address();

    @Override
    public String getName() {
       return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public IAddress getAddress() {
        return address;
    }
}
