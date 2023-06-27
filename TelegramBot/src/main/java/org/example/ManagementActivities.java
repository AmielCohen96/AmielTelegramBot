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
        this.title.setBounds(Constant.X_START - 5, Constant.Y_START, Constant.CHECKBOX_WIDTH, Constant.HEIGHT);
        this.title.setFont(new Font("David", Font.PLAIN, Constant.FONT_SIZE));
        this.title.setVisible(true);
        this.jokesBox = new JCheckBox("  jokes");
        this.jokesBox.setFont(new Font("David", Font.PLAIN, Constant.CHECKBOX_FONT_SIZE));
        add(jokesBox);
        jokesBox.setBounds(Constant.X_START, title.getY() + 60, Constant.CHECKBOX_WIDTH, Constant.HEIGHT);
        this.jokesBox.setVisible(true);
        this.catBox = new JCheckBox("  cats");
        this.catBox.setFont(new Font("David", Font.PLAIN, Constant.CHECKBOX_FONT_SIZE));
        add(catBox);
        catBox.setBounds(Constant.X_START, jokesBox.getY() + 40, Constant.CHECKBOX_WIDTH, Constant.HEIGHT);
        this.catBox.setVisible(true);
        this.countriesBox = new JCheckBox("  countries");
        this.countriesBox.setFont(new Font("David", Font.PLAIN, Constant.CHECKBOX_FONT_SIZE));
        add(countriesBox);
        countriesBox.setBounds(Constant.X_START, catBox.getY() + 40, Constant.CHECKBOX_WIDTH, Constant.HEIGHT);
        this.countriesBox.setVisible(true);
        this.numbersBox = new JCheckBox("  numbers");
        this.numbersBox.setFont(new Font("David", Font.PLAIN, Constant.CHECKBOX_FONT_SIZE));
        add(numbersBox);
        numbersBox.setBounds(Constant.X_START, countriesBox.getY() + 40, Constant.CHECKBOX_WIDTH, Constant.HEIGHT);
        this.numbersBox.setVisible(true);
        this.covidBox = new JCheckBox("  covid");
        this.covidBox.setFont(new Font("David", Font.PLAIN, Constant.CHECKBOX_FONT_SIZE));
        add(covidBox);
        covidBox.setBounds(Constant.X_START, numbersBox.getY() + 40, Constant.CHECKBOX_WIDTH, Constant.HEIGHT);
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



