package event.management.sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Userpage extends JFrame implements ActionListener {

    Userpage() {
        setSize(1540, 850);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/userbg.png"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        JMenuBar mb = new JMenuBar();

        // Book Event
        JMenu bookEvent = new JMenu("Book Event");
        bookEvent.setForeground(Color.BLUE);
        mb.add(bookEvent);

        JMenuItem bookEventItem = new JMenuItem("Book a New Event");
        bookEventItem.setBackground(Color.WHITE);
        bookEventItem.addActionListener(this);
        bookEvent.add(bookEventItem);

        // Update Event
        JMenu updateEvent = new JMenu("Update Event");
        updateEvent.setForeground(Color.RED);
        mb.add(updateEvent);

        JMenuItem updateEventItem = new JMenuItem("Update Your Event");
        updateEventItem.setBackground(Color.WHITE);
        updateEventItem.addActionListener(this);
        updateEvent.add(updateEventItem);

        // Update Details
        JMenu updateDetails = new JMenu("Update Details");
        updateDetails.setForeground(Color.BLUE);
        mb.add(updateDetails);

        JMenuItem updateDetailsItem = new JMenuItem("Update Your Details");
        updateDetailsItem.setBackground(Color.WHITE);
        updateDetailsItem.addActionListener(this);
        updateDetails.add(updateDetailsItem);

        // View Previous Bookings
        JMenu viewBookings = new JMenu("Previous Bookings");
        viewBookings.setForeground(Color.RED);
        mb.add(viewBookings);

        JMenuItem viewBookingsItem = new JMenuItem("View Your Previous Bookings");
        viewBookingsItem.setBackground(Color.WHITE);
        viewBookingsItem.addActionListener(this);
        viewBookings.add(viewBookingsItem);

        //payment 
        JMenu fee = new JMenu("Payment");
        fee.setForeground(Color.BLUE);
        mb.add(fee);

        JMenuItem payment = new JMenuItem("Payment details");
        payment.setBackground(Color.WHITE);
        payment.addActionListener(this);
        fee.add(payment);

        //About
        JMenu about = new JMenu("About");
        about.setForeground(Color.RED);
        mb.add(about);

        JMenuItem AboutItem = new JMenuItem("About");
        AboutItem.setBackground(Color.WHITE);
        AboutItem.addActionListener(this);
        about.add(AboutItem);

        // Logout
        JMenu logout = new JMenu("Logout");
        logout.setForeground(Color.BLUE);
        mb.add(logout);

        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.setBackground(Color.WHITE);
        logoutItem.addActionListener(this);
        logout.add(logoutItem);

        setJMenuBar(mb);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();

        if (msg.equals("Logout")) {
            setVisible(false);
        } else {
            if (msg.equals("Book a New Event")) {
                new Addevent().setVisible(true);
            } else if (msg.equals("Update Your Event")) {
                new UpdateEvent().setVisible(true);
            } else if (msg.equals("Update Your Details")) {
                new UpdateUserDetails().setVisible(true);
            } else if (msg.equals("View Your Previous Bookings")) {
                new Eventdetails().setVisible(true);
            } else if (msg.equals("Payment details")) {
                setVisible(true);
                new Payment();
            } else if (msg.equals("About")) {
                setVisible(true);
                new About().setVisible(true);
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        new Userpage();
    }
}
