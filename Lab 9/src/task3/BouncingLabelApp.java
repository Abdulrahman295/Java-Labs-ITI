package task3;

import javax.swing.*;
import java.awt.*;

public class BouncingLabelApp extends JFrame implements Runnable{
    Thread th;
    JLabel ITILabel;
    boolean movingDown;
    boolean movingRight;

    public BouncingLabelApp(){
        this.setTitle("Bouncing Label App");
        this.setLayout(null);

        ITILabel = new JLabel();
        ITILabel.setSize(20, 10);
        ITILabel.setLocation(250, 150);
        ITILabel.setText("ITI");
        ITILabel.setForeground(Color.RED);
        this.add(ITILabel);

        this.movingRight = true;
        this.movingDown = true;

        th = new Thread(this);
        th.start();

    }

    public static void main(String[] args) {
        BouncingLabelApp app = new BouncingLabelApp();
        app.setBounds(50,50,600,400);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void run() {
        while (true){
            updateMovingStatus();
            moveLabel();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

    }

    private void updateMovingStatus(){
        int currentWindowWidth =  this.getContentPane().getWidth();
        int currentWindowHeight = this.getContentPane().getHeight();

        int labelX = ITILabel.getX();
        int labelY = ITILabel.getY();
        int labelWidth = ITILabel.getWidth();
        int labelHeight = ITILabel.getHeight();


        if(labelY + labelHeight >= currentWindowHeight){
            movingDown = false;
        } else if (labelY <= 0) {
            movingDown = true;
        }

        if(labelX + labelWidth >= currentWindowWidth){
            movingRight = false;
        } else if (labelX <= 0){
            movingRight = true;
        }
    }

    private void moveLabel(){
        int labelX = ITILabel.getX();
        int labelY = ITILabel.getY();

        if(movingDown){
            labelY++;
        } else {
            labelY--;
        }

        if(movingRight){
            labelX++;
        } else {
            labelX--;
        }

        ITILabel.setLocation(labelX, labelY);
    }
}
