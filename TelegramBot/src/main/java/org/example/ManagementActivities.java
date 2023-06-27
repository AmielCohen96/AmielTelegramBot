package org.example;

import javax.swing.*;
import java.awt.*;

public class ManagementActivities extends JPanel {
    private JLabel title;
    private JCheckBox jokesBox;
    private JCheckBox catBox;
    private JCheckBox countriesBox;
    private JCheckBox numbersBox;
    private JCheckBox covidBox;
    int counter = 0;
    public static JCheckBox[] jCheckBoxes;

    public ManagementActivities() {
        setBorder(BorderFactory.createTitledBorder("Management Activities"));
        this.setLayout(null);
        this.title = new JLabel();
        this.title.setText("Select Activities:");
        add(this.title);
        this.title.setBounds(10, 30, 200, 30);
        this.title.setFont(new Font("David", Font.PLAIN, 20));
        this.title.setVisible(true);
        this.jokesBox = new JCheckBox("jokes");
        add(jokesBox);
        jokesBox.setBounds(10, title.getY() + 60, 400, 30);
        this.jokesBox.setVisible(true);
        this.catBox = new JCheckBox("cats");
        add(catBox);
        catBox.setBounds(10, jokesBox.getY() + 40, 400, 30);
        this.catBox.setVisible(true);
        this.countriesBox = new JCheckBox("countries");
        add(countriesBox);
        countriesBox.setBounds(10, catBox.getY() + 40, 400, 30);
        this.countriesBox.setVisible(true);
        this.numbersBox = new JCheckBox("numbers");
        add(numbersBox);
        numbersBox.setBounds(10, countriesBox.getY() + 40, 400, 30);
        this.numbersBox.setVisible(true);
        this.covidBox = new JCheckBox("covid");
        add(covidBox);
        covidBox.setBounds(10, numbersBox.getY() + 40, 400, 30);
        this.covidBox.setVisible(true);
        jCheckBoxes = new JCheckBox[]{jokesBox, catBox, countriesBox, numbersBox, covidBox};
        for (JCheckBox checkBox : jCheckBoxes) {
            checkBox.addActionListener((e) -> {
                checkSelected();
                if (counter >= 3) {
                    for (JCheckBox jCheckBox : jCheckBoxes) {
                        if (!jCheckBox.isSelected()) {
                            jCheckBox.setEnabled(false);
                        }
                    }
                } else {
                    for (JCheckBox jCheckBox : jCheckBoxes) {
                        jCheckBox.setEnabled(true);
                    }
                }
            });
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.repaint();
    }

    private void checkSelected() {
        counter = 0;
        if (jokesBox.isSelected()) {
            counter++;
        }
        if (catBox.isSelected()) {
            counter++;
        }
        if (countriesBox.isSelected()) {
            counter++;
        }
        if (numbersBox.isSelected()) {
            counter++;
        }
        if (covidBox.isSelected()) {
            counter++;
        }
    }
}



