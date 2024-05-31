package event.management.sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {

    Project() {
        setSize(1540, 850);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eventm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        JMenuBar mb = new JMenuBar();

        // New Information
        JMenu newInformation = new JMenu("New Information");
        newInformation.setForeground(Color.BLUE);
        mb.add(newInformation);

        JMenuItem eventInfo = new JMenuItem("New Event Information");
        eventInfo.setBackground(Color.WHITE);
        eventInfo.addActionListener(this);
        newInformation.add(eventInfo);

        JMenuItem userInfo = new JMenuItem("New User Information");
        userInfo.setBackground(Color.WHITE);
        userInfo.addActionListener(this);
        newInformation.add(userInfo);

        // Details
        JMenu details = new JMenu("View Details");
        details.setForeground(Color.RED);
        mb.add(details);

        JMenuItem eventdetails = new JMenuItem("View Event Details");
        eventdetails.setBackground(Color.WHITE);
        eventdetails.addActionListener(this);
        details.add(eventdetails);

        JMenuItem userdetails = new JMenuItem("View User Details");
        userdetails.setBackground(Color.WHITE);
        userdetails.addActionListener(this);
        details.add(userdetails);

        // UpdateInfo
        JMenu updateInfo = new JMenu("Update Details");
        updateInfo.setForeground(Color.BLUE);
        mb.add(updateInfo);

        JMenuItem updateeventinfo = new JMenuItem("Update Event Details");
        updateeventinfo.setBackground(Color.WHITE);
        updateeventinfo.addActionListener(this);
        updateInfo.add(updateeventinfo);

        JMenuItem updateuserinfo = new JMenuItem("Update User Details");
        updateuserinfo.setBackground(Color.WHITE);
        updateuserinfo.addActionListener(this);
        updateInfo.add(updateuserinfo);

        // fee
        JMenu fee = new JMenu("Payment");
        fee.setForeground(Color.RED);
        mb.add(fee);

        JMenuItem payment = new JMenuItem("Payment details");
        payment.setBackground(Color.WHITE);
        payment.addActionListener(this);
        fee.add(payment);

        JMenuItem payform = new JMenuItem("User payment Form");
        payform.setBackground(Color.WHITE);
        payform.addActionListener(this);
        fee.add(payform);

        // Utility
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLUE);
        mb.add(utility);

        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setBackground(Color.WHITE);
        notepad.addActionListener(this);
        utility.add(notepad);

        JMenuItem calc = new JMenuItem("Calculator");
        calc.setBackground(Color.WHITE);
        calc.addActionListener(this);
        utility.add(calc);

        // about
        JMenu about = new JMenu("About");
        about.setForeground(Color.RED);
        mb.add(about);

        JMenuItem ab = new JMenuItem("About");
        ab.setBackground(Color.WHITE);
        ab.addActionListener(this);
        about.add(ab);

        // exit
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.BLUE);
        mb.add(exit);

        JMenuItem ex = new JMenuItem("Exit");
        ex.setBackground(Color.WHITE);
        ex.addActionListener(this);
        exit.add(ex);

        setJMenuBar(mb);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();

        if (msg.equals("Exit")) {
            setVisible(false);
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {

            }
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {

            }
        } else if (msg.equals("New Event Information")) {
            setVisible(true);
            new Addevent();
        } else if (msg.equals("New User Information")) {
            setVisible(true);
            new Adduser();
        } else if (msg.equals("View Event Details")) {
            setVisible(true);
            new Eventdetails();
        } else if (msg.equals("View User Details")) {
            setVisible(true);
            new Userdetails();
        } else if (msg.equals("Update Event Details")) {
            setVisible(true);
            new UpdateEvent();
        } else if (msg.equals("Update User Details")) {
            setVisible(true);
            new UpdateUserDetails();
        } else if (msg.equals("User payment Form")) {
            setVisible(true);
            new Userpayment();
        } else if (msg.equals("Payment details")) {
            setVisible(true);
            new Payment1();
        } else if (msg.equals("About")) {
            setVisible(true);
            new About();
        }
    }

    public static void main(String[] args) {
        new Project();
    }
}
