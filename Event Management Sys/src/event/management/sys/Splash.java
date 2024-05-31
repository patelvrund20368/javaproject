package event.management.sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash extends JFrame implements Runnable {
    
    Thread t;

    Splash() {
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/event.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setLayout(new BorderLayout());
        add(image);

        JLabel text = new JLabel();
        String htmlText = "<html><body style='text-align: center;'>" +
                "<h1 style='color: white; font-family: Arial;'>Event Management System</h1>" +
                "</body></html>";
        text.setText(htmlText);
        text.setFont(new Font("Arial", Font.BOLD, 24));
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(JLabel.CENTER);
        image.add(text, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setOpaque(false);

        JButton userButton = new JButton("User");
        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login1(); // Open Login1 frame
                dispose(); // Close the Splash frame
            }
        });
        buttonPanel.add(userButton);

        JButton adminButton = new JButton("Admin");
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login(); // Open Login frame
                dispose(); // Close the Splash frame
            }
        });
        buttonPanel.add(adminButton);

        image.add(buttonPanel, BorderLayout.SOUTH);

        t = new Thread(this);
        t.start();

        setVisible(true);

        int x = 1;
        for (int i = 2; i <= 600; i+=4, x+=1) {
            setLocation(600 - ((i + x)/2), 350 - (i/2));
            setSize(i + 3*x, i + x/2);

            try {
                Thread.sleep(10);
            } catch (Exception e) {}
        }        
    }
    
    public void run() {
        // Do not sleep the frame
    }
    
    public static void main(String[] args){
        Splash s = new Splash();
    }
}