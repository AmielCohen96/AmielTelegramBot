package org.example;

public class CatBreed {
    private String breed;
    private String country;
    private String origin;
    private String coat;
    private String Ticked;

    @Override
    public String toString() {
        return "CatBreed{" +
                "breed='" + breed + '\'' +
                ", country='" + country + '\'' +
                ", origin='" + origin + '\'' +
                ", coat='" + coat + '\'' +
                ", Ticked='" + Ticked + '\'' +
                '}';
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCoat() {
        return coat;
    }

    public void setCoat(String coat) {
        this.coat = coat;
    }

    public String getTicked() {
        return Ticked;
    }

    public void setTicked(String ticked) {
        Ticked = ticked;
    }
}
