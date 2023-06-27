package org.example;
import javax.swing.*;
import java.awt.*;

public class RecentActivities extends JPanel {
    private static JLabel activity = new JLabel(AmielBot.recentActivities.toString());

    public RecentActivities(){
        setBorder(BorderFactory.createTitledBorder("RecentActivities"));
        setLayout(null);
        activity.setBounds(Constant.X_START, 0, 600, 400);
        activity.setFont(new Font("David", Font.PLAIN, Constant.FONT_SIZE - 10));
        add(activity);
    }

    public static void updateActivities(){
        activity.setText(AmielBot.recentActivities.toString());
    }

}
