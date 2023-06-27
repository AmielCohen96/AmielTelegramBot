package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatFact {
    private String fact;

    @Override
    public String toString() {
        return "CatFact{" +
                "fact='" + fact + '\'' +
                '}';
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }
}
