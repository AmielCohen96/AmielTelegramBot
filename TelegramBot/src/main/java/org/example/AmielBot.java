package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import javax.swing.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


public class AmielBot extends TelegramLongPollingBot{
    private final List<Users> users;
    private boolean explore = false;

    private String country;

    public static int totalRequest;
    public static int usersNumber;
    public static String mostActive;
    public static String popularActivity = "Null";
    private int jokesCounter=0;
    private int catCounter=0;
    private int countriesCounter=0;
    private int numbersCounter=0;
    private int covidCounter=0;
    private boolean oldUser = false;
    public static LastActivityManager recentActivities = new LastActivityManager();

    Map<String, Integer> activitiesCounter = new HashMap<>();

    public AmielBot()
    {
        this.users = new ArrayList<>();
        this.activitiesCounter.put("Jokes", this.jokesCounter);
        this.activitiesCounter.put("Cats", this.catCounter);
        this.activitiesCounter.put("Countries", this.countriesCounter);
        this.activitiesCounter.put("Numbers", this.numbersCounter);
        this.activitiesCounter.put("covid", this.covidCounter);
    }
    @Override
    public String getBotUsername() {
        return "AmielcBot";
    }

    @Override
    public String getBotToken() {
        return "6217670316:AAE3v9ThbOm8eQg-p0YRODzTMz7lOsApFE4";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Action action=null;
        String textMessage;
        long chatId;
        String callbackData;
        String userName;
        chatId = this.getChatId(update);
        for(Users users1: users){
            if(users1.getChatId() == chatId){
                users1.setCounter(users1.getCounter()+1);
                this.oldUser = true;
            }
        }
        if(!update.hasCallbackQuery()){
        userName = update.getMessage().getFrom().getFirstName();
            action= new Action();
            action.setUserName(userName);
            action.setDate(update.getMessage().getDate()+"");
            action.setActivityName("message");
        if(!this.oldUser){


            Users newUser = new Users(userName, 0, chatId);
            users.add(newUser);
            this.oldUser = false;
        }}
        if (update.hasMessage() && update.getMessage().hasText()){
            textMessage = update.getMessage().getText();
            if(!this.explore){
                switch (textMessage) {
                    case "cats" -> catFact(chatId);
                    case "jokes" -> jokesMenu(chatId);
                    case "countries" -> countriesMenu(chatId);
                    case "one" -> jokesOneMenu(chatId);
                    case "two" -> jokesTwoMenu(chatId);
                    case "countryExplore" -> countryExplore(chatId);
                    case "capital", "population" -> countryInfo(chatId, textMessage);
                    default -> mainMenu(chatId);
                }
            }if(this.explore){
                this.country = textMessage;
                this.countryExplore(chatId);
            }

        }
        else if (update.hasCallbackQuery()) {
            callbackData = update.getCallbackQuery().getData();
            action=new Action();
                userName=update.getCallbackQuery().getFrom().getUserName();
            action.setUserName(userName);
            action.setDate(update.getCallbackQuery().getMessage().getDate()+"");
            action.setActivityName(callbackData);
                recentActivities.addAction(action);

            new Thread(()-> {
                switch (callbackData) {
                    case "cats" -> catFact(chatId);
                    case "jokes" -> jokesMenu(chatId);
                    case "countries" -> {
                        this.explore = true;
                        countriesMenu(chatId);
                    }
                    case "one" -> jokesOneMenu(chatId);
                    case "two" -> jokesTwoMenu(chatId);
                    case "countryExplore" -> countryExplore(chatId);
                    case "capital", "population" -> countryInfo(chatId, callbackData);
                    case "numbers" -> numbersMenu(chatId);
                    case "Date fact" -> numbersFacts(chatId, "date");
                    case "Math fact" -> numbersFacts(chatId, "math");
                    case "covid" -> covidMenu(chatId);
                    case "ny", "oh", "gu", "tx" -> covidInfo(chatId, callbackData);
                    default -> mainMenu(chatId);
                }
                statUpdate();
            }).start();
        }
    }

    private void mainMenu (long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        LinkedList <String> strings = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            JCheckBox current = ManagementActivities.jCheckBoxes[i];
            if(current.isSelected()){
                strings.add(current.getText());
            }
        }
        message.setText("What kind of activity are you interested in?");
        LinkedList <InlineKeyboardButton> buttons = new LinkedList<>();
        for (String string : strings) {
            InlineKeyboardButton button = new InlineKeyboardButton(string);
            button.setCallbackData(string);
            buttons.add(button);
        }
        List<List<InlineKeyboardButton>> keyboard = List.of(buttons);
        InlineKeyboardMarkup inlineKeyboardMarkup=new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(inlineKeyboardMarkup);
        send(message);
    }

    private void jokesMenu (long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("One part or Tow parts?");
        InlineKeyboardButton one = new InlineKeyboardButton("One");
        one.setCallbackData("one");
        InlineKeyboardButton two =new InlineKeyboardButton("Two");
        two.setCallbackData("two");
        List<InlineKeyboardButton>topRow= Arrays.asList(one, two);
        List<List<InlineKeyboardButton>> keyboard = List.of(topRow);
        InlineKeyboardMarkup inlineKeyboardMarkup=new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(inlineKeyboardMarkup);
        send(message);
        this.jokesCounter++;
        this.activitiesCounter.put("Jokes", this.jokesCounter);
        maxActivities();
    }

    private void jokesOneMenu(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        try {
            GetRequest getRequest = Unirest.get("https://v2.jokeapi.dev/joke/Any?type=single");
            HttpResponse<String> response = getRequest.asString();
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JokesSingle jokesSingles = objectMapper.readValue(json, JokesSingle.class);
            message.setText(jokesSingles.getJoke());
            send(message);
        }catch (UnirestException | JsonProcessingException e){
            throw new RuntimeException(e);
        }

    }

    private void jokesTwoMenu(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        try {
            GetRequest getRequest = Unirest.get("https://v2.jokeapi.dev/joke/Any?type=twopart");
            HttpResponse<String> response = getRequest.asString();
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JokesTowParts jokesTowParts = objectMapper.readValue(json, JokesTowParts.class);
            message.setText(jokesTowParts.getSetup() + "\n" + jokesTowParts.getDelivery());
        }catch (UnirestException | JsonProcessingException e){
            throw new RuntimeException(e);
        }
        send(message);
    }

    private void catFact(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        try {
            GetRequest getRequest = Unirest.get("https://catfact.ninja/fact?max_length=140");
            HttpResponse<String> response = getRequest.asString();
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            CatFact catFact = objectMapper.readValue(json, CatFact.class);
            message.setText(catFact.getFact());
        } catch (UnirestException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        send(message);
        this.catCounter++;
        this.activitiesCounter.put("Cats", this.catCounter);
        maxActivities();
    }
    private void countriesMenu (long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Which country do you want to explore?");
        send(message);
        this.countriesCounter++;
        this.activitiesCounter.put("Countries", this.countriesCounter);
        maxActivities();
    }
    private void countryExplore(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Choose one category?");
        InlineKeyboardButton capital = new InlineKeyboardButton("capital");
        capital.setCallbackData("capital");
        InlineKeyboardButton population =new InlineKeyboardButton("population");
        population.setCallbackData("population");
        List<InlineKeyboardButton>topRow= Arrays.asList(capital, population);
        List<List<InlineKeyboardButton>> keyboard = List.of(topRow);
        InlineKeyboardMarkup inlineKeyboardMarkup=new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(inlineKeyboardMarkup);
        this.explore = false;
        send(message);
    }

    private void countryInfo(long chatId, String callbackData) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        try {
            GetRequest getRequest = Unirest.get("https://restcountries.com/v2/name/"+this.country);
            HttpResponse<String> response = getRequest.asString();
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            List<Countries> countries  = objectMapper.readValue(json, new TypeReference<>(){});
            if(callbackData.equals("capital")){
                message.setText(countries.get(0).getCapital());
            }else{
                message.setText(countries.get(0).getPopulation() + "");
            }

        }catch (UnirestException | JsonProcessingException e){
            throw new RuntimeException(e);
        }
        send(message);
    }

    private void numbersMenu(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("what are you interested in?");
        InlineKeyboardButton date = new InlineKeyboardButton("Date fact");
        date.setCallbackData("Date fact");
        InlineKeyboardButton math =new InlineKeyboardButton("Math fact");
        math.setCallbackData("Math fact");
        List<InlineKeyboardButton>topRow= Arrays.asList(date, math);
        List<List<InlineKeyboardButton>> keyboard = List.of(topRow);
        InlineKeyboardMarkup inlineKeyboardMarkup=new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(inlineKeyboardMarkup);
        send(message);
        this.numbersCounter++;
        this.activitiesCounter.put("Numbers", this.numbersCounter);
        maxActivities();
    }

    private void numbersFacts(long chatId, String text){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        try {
            GetRequest getRequest = Unirest.get("http://numbersapi.com/random/" + text);
            HttpResponse<String> response = getRequest.asString();
            String json = response.getBody();
            message.setText(json);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        send(message);
    }

    private void covidMenu(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Choose country?");
        InlineKeyboardButton guam = new InlineKeyboardButton("Guam");
        guam.setCallbackData("gu");
        InlineKeyboardButton ny =new InlineKeyboardButton("New York");
        ny.setCallbackData("ny");
        InlineKeyboardButton ohio = new InlineKeyboardButton("Ohio");
        ohio.setCallbackData("oh");
        InlineKeyboardButton texas =new InlineKeyboardButton("Texas");
        texas.setCallbackData("tx");
        List<InlineKeyboardButton>topRow= Arrays.asList(guam, ny, ohio, texas);
        List<List<InlineKeyboardButton>> keyboard = List.of(topRow);
        InlineKeyboardMarkup inlineKeyboardMarkup=new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(inlineKeyboardMarkup);
        this.covidCounter++;
        this.activitiesCounter.put("covid", this.covidCounter);
        send(message);
    }

    private void covidInfo(long chatId, String state){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        try {
            GetRequest getRequest = Unirest.get("https://api.covidtracking.com/v1/states/" + state + "/current.json");
            HttpResponse<String> response = getRequest.asString();
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            Covid covid  = objectMapper.readValue(json, Covid.class);
            message.setText("Positive: " + covid.getPositive() +
                    "\nHospitalized currently: " + covid.getHospitalizedCurrently() +
                    "\nDeaths: " + covid.getDeath());
        } catch (UnirestException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        send(message);
        maxActivities();
    }

    private long getChatId (Update update) {
        long chatId = 0;
        if (update.getMessage() != null){
            chatId = update.getMessage().getChatId();
        } else {
            chatId =update.getCallbackQuery().getMessage().getChatId();
        }
        return chatId;
    }

    private void send (SendMessage message){
        try {
            execute(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void maxActivities(){
        int max = 0;
        for (Map.Entry<String, Integer> entry : this.activitiesCounter.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if (value > max) {
                max = value;
                popularActivity  = key;
            }
        }
    }

    private void mostUses () {
        int max = 0;
        for (Users users1 : users){
            if(users1.getCounter() > max){
                max = users1.getCounter();
                mostActive = users1.getUserName();
            }
        }
    }

    public void statUpdate () {
        totalRequest++;
        usersNumber = this.users.size();
        mostUses();
        updateFields();
        RecentActivities.updateActivities();
    }

    public void updateFields(){
        Stats.bestActive.setText("The most used activity is: " + popularActivity);
        Stats.totalRequests.setText("Total requests: " + totalRequest);
        Stats.usersNumber.setText("Number of users: " + usersNumber);
        Stats.mostActive.setText("Most active user is: " + mostActive);
    }

    public LastActivityManager getRecentActivities() {
        return recentActivities;
    }
}
