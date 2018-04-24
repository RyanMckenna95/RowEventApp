package models;

/**
 * Created by mcken on 24/04/2018.
 */

public class Entries {
    private String BoatType;
    private  String Catagory;
    private  String price;

    public Entries(String boatType, String catagory, String price) {
        BoatType = boatType;
        Catagory = catagory;
        this.price = price;
    }

    public Entries(){

    }

    public String getBoatType() {
        return BoatType;
    }

    public void setBoatType(String boatType) {
        BoatType = boatType;
    }

    public String getCatagory() {
        return Catagory;
    }

    public void setCatagory(String catagory) {
        Catagory = catagory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Entries{" +
                "BoatType='" + BoatType + '\'' +
                ", Catagory='" + Catagory + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
