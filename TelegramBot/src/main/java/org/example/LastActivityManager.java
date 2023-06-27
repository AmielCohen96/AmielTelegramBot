package org.example;

import java.util.LinkedList;

public class LastActivityManager {
    private LinkedList<Action> recent;

    public LastActivityManager() {
        recent = new LinkedList<>();
    }

    public void addAction(Action action){
        if(recent.size() >= 10) {
            recent.remove(0);
        }
        recent.add(action);
    }


    public String toString(){
        String text = "<html> Actions: <br>";
        for (Action a: recent) {
            text += a + "<br>" ;
        }
        text += "</html>";
        return text;
    }
}
