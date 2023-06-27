package org.example;
import javax.swing.*;
import java.awt.*;

public class Stats extends JPanel{
    public static JLabel bestActive = new JLabel("The most used activity is: ");
    public static JLabel totalRequests = new JLabel("Total requests: 0");
    public static JLabel usersNumber = new JLabel("Number of users: 0");
    public static JLabel mostActive = new JLabel("Most active user is: ");
    public Stats() {
        setBorder(BorderFactory.createTitledBorder("Stats"));
        setLayout(null);
        add(bestActive);
        bestActive.setBounds(10, 50, 500, 30);
        bestActive.setFont(new Font("David", Font.PLAIN, 30));
        add(totalRequests);
        totalRequests.setBounds(10, bestActive.getY() + 50, 500, 30);
        totalRequests.setFont(new Font("David", Font.PLAIN, 30));
        add(usersNumber);
        usersNumber.setBounds(10, totalRequests.getY() + 50, 500, 30);
        usersNumber.setFont(new Font("David", Font.PLAIN, 30));
        add(mostActive);
        mostActive.setBounds(10, usersNumber.getY() + 50, 500, 30);
        mostActive.setFont(new Font("David", Font.PLAIN, 30));

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.repaint();
    }

}
