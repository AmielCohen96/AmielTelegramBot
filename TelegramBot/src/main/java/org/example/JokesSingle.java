package org.example;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JokesSingle {
    private String category;
    private String joke;

    public JokesSingle(String category, String joke) {
        this.category = category;
        this.joke = joke;
    }

    public String getJoke() {
        return joke;
    }

    @Override
    public String toString() {
        return "JokesSingle{" +
                "category='" + category + '\'' +
                ", joke='" + joke + '\'' +
                '}';
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
