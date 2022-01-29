package model;

public class Address {
    private String street;
    private String streetNumber;
    private String city;
    private String country;

    public Address(String country, String city, String street, String streetNumber) {
        super();
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return country + ":" + city + ":" + street + ":" + streetNumber;
    }
}
