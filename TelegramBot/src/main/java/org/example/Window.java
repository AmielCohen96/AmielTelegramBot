package org.example;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JFrame frame;
    public Window() {
        TelegramBotsApi botsApi = null;
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new AmielBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        frame = new JFrame("Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
//        this.setSize(1200, 800);
//        this.setLayout(new BorderLayout());
//        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(new GridLayout(2, 2));
        this.mainPanel.add(new Stats());
        this.mainPanel.add(new ManagementActivities());
        this.mainPanel.add(new RecentActivities());
        this.mainPanel.add(new Graph());
//        this.mainPanel.setLayout(this.cardLayout);
//        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
//        this.setVisible(true);
    }

}
