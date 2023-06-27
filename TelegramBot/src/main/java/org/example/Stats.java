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
        bestActive.setBounds(Constant.X_START, 100, Constant.WIDTH, Constant.HEIGHT);
        bestActive.setFont(new Font("David", Font.PLAIN, Constant.FONT_SIZE));
        add(totalRequests);
        totalRequests.setBounds(Constant.X_START, bestActive.getY() + 50, Constant.WIDTH, Constant.HEIGHT);
        totalRequests.setFont(new Font("David", Font.PLAIN, Constant.FONT_SIZE));
        add(usersNumber);
        usersNumber.setBounds(Constant.X_START, totalRequests.getY() + 50, Constant.WIDTH, Constant.HEIGHT);
        usersNumber.setFont(new Font("David", Font.PLAIN, Constant.FONT_SIZE));
        add(mostActive);
        mostActive.setBounds(Constant.X_START, usersNumber.getY() + 50, Constant.WIDTH, Constant.HEIGHT);
        mostActive.setFont(new Font("David", Font.PLAIN, Constant.FONT_SIZE));

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.repaint();
    }

}
