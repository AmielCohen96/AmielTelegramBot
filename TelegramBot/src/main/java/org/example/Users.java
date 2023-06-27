package org.example;

public class Users {
    private String userName;
    private int counter;
    private long chatId;

    public Users(String userName, int counter, long chatId) {
        this.userName = userName;
        this.counter = counter;
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
}
