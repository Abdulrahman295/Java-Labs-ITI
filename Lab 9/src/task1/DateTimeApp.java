package task1;

import javax.swing.*;
import java.awt.*;
import java.util.Date;


public class DateTimeApp extends JFrame implements Runnable {
    Thread th;
    Date d;
    JLabel timeLabel;

    public DateTimeApp(){
        this.setTitle("Date & Time Frame Application");

        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setText(d.toString());
        this.add(timeLabel, BorderLayout.CENTER);

        d = new Date();

        th = new Thread(this);
        th.start();
    }

    public static void main(String[] args) {
        DateTimeApp app = new DateTimeApp();
        app.setBounds(50,50,600,400);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        while(true){
            d= new Date();
            timeLabel.setText(d.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}