package task2;

import javax.swing.*;
import java.util.Date;

public class MarqueeStringApp extends JFrame implements Runnable{
    Thread th;
    Date d;
    JLabel marqueeLabel;

    public MarqueeStringApp(){
        this.setTitle("Marquee String Application");
        this.setLayout(null);

        marqueeLabel = new JLabel();
        marqueeLabel.setSize(100, 50);
        marqueeLabel.setLocation(250, 150);
        marqueeLabel.setText("Java World");
        this.add(marqueeLabel);

        d = new Date();

        th = new Thread(this);
        th.start();
    }

    public static void main(String[] args) {
        MarqueeStringApp app = new MarqueeStringApp();
        app.setBounds(50,50,600,400);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
         while (true){
                int currentWindowWidth =  this.getWidth();
                int labelX = marqueeLabel.getX();
                int labelY = marqueeLabel.getY();

                labelX += 1;

                if(labelX > currentWindowWidth){
                    labelX = -marqueeLabel.getWidth();
                }

                marqueeLabel.setLocation(labelX, labelY);

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
         }
    }
}

