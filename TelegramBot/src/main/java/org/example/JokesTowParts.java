package org.example;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JokesTowParts {
    private String category;
    private String setup;
    private String delivery;

    public JokesTowParts(String category, String setup, String delivery) {
        this.category = category;
        this.setup = setup;
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return "JokesTowParts{" +
                "category='" + category + '\'' +
                ", setup='" + setup + '\'' +
                ", delivery='" + delivery + '\'' +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
