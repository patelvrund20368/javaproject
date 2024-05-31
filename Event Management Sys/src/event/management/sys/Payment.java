package event.management.sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment extends JFrame implements ActionListener {

    JButton upiButton, cardButton;

    public Payment() {
        // Set up the frame
        setTitle("Payment Options");
        setSize(400, 200);
        setLocation(500, 300);
        setLayout(new GridLayout(2, 1));

        // Create and add UPI payment button
        upiButton = new JButton("Pay with UPI");
        upiButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        upiButton.addActionListener(this);
        add(upiButton);

        // Create and add Card payment button
        cardButton = new JButton("Pay with Card");
        cardButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cardButton.addActionListener(this);
        add(cardButton);

        // Set default close operation and make the frame visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == upiButton) {
            JOptionPane.showMessageDialog(this, "UPI Payment option selected");
            // Add logic for UPI payment here
        } else if (e.getSource() == cardButton) {
            JOptionPane.showMessageDialog(this, "Card Payment option selected");
            // Add logic for Card payment here
        }
    }

    public static void main(String[] args) {
        new Payment();
    }
}
