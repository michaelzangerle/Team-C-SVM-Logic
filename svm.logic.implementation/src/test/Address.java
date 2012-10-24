package test;

/**
 * Projectteam
 * Date: 24.10.12
 */
public class Address implements IAddress {

    String plz="6884";


    @Override
    public String getPlz() {
        return plz;
    }

    @Override
    public void setPlz(String str) {
        this.plz=str;

    }
}
