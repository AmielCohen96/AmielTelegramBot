package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Graph extends JPanel{
    private int [] time;
    private int [] requests;
    private int timeCount = 0;

    public Graph(){
        setBorder(BorderFactory.createTitledBorder("Graph"));
        this.setLayout(null);
        this.repaint();
        new Thread(()->{
            while (true){
                Constant.sleep(2000);
                this.timeCount++;
                this.time[this.timeCount-1] = this.timeCount;
                this.requests[this.timeCount-1] = AmielBot.totalRequest;
            }
        }).start();

    }


    public void paintBackground(Graphics graphics) {
        ImageIcon imageIcon = new ImageIcon("https://quickchart.io/chart?c={type:'bar',data:{Time:" + Arrays.toString(this.time) + ",datasets:[{label:'Uses',data:" + Arrays.toString(this.requests) + "}]}}");
        imageIcon.paintIcon(this, graphics, 0, 0);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        paintBackground(graphics);
    }

}
