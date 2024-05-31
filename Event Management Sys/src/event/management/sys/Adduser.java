package event.management.sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Adduser extends JFrame implements ActionListener{

    JButton register, cancel;
    JTextField tfusername, tfname, tfemail, tfaddress, tfaadhar, tfphone;
    JPasswordField tfpassword, tfconfirmpassword;

    Adduser () {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 20);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 20);
        add(tfusername);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(40, 60, 100, 20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(150, 60, 150, 20);
        add(tfname);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(40, 100, 100, 20);
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(150, 100, 150, 20);
        add(tfemail);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(40, 140, 100, 20);
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(150, 140, 150, 20);
        add(tfaddress);

        JLabel lblaadhar = new JLabel("Aadhar");
        lblaadhar.setBounds(40, 180, 100, 20);
        add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(150, 180, 150, 20);
        add(tfaadhar);

        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(40, 220, 100, 20);
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(150, 220, 150, 20);
        add(tfphone);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 260, 100, 20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 260, 150, 20);
        add(tfpassword);

        JLabel lblconfirmpassword = new JLabel("Confirm Password");
        lblconfirmpassword.setBounds(40, 300, 120, 20);
        add(lblconfirmpassword);

        tfconfirmpassword = new JPasswordField();
        tfconfirmpassword.setBounds(150, 300, 150, 20);
        add(tfconfirmpassword);

        register = new JButton("Register");
        register.setBounds(40, 340, 120, 30);
        register.setBackground(Color.BLACK);
        register.setForeground(Color.WHITE);
        register.addActionListener(this);
        register.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(register);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 340, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);

        setSize(600, 450);
        setLocation(500, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == register) {
            String username = tfusername.getText();
            String name = tfname.getText();
            String email = tfemail.getText();
            String address = tfaddress.getText();
            String aadhar = tfaadhar.getText();
            String phone = tfphone.getText();
            String password = new String(tfpassword.getPassword());
            String confirmpassword = new String(tfconfirmpassword.getPassword());

            if (password.equals(confirmpassword)) {
                String query = "insert into user values('"+username+"','"+name+"','"+email+"','"+address+"','"+aadhar+"','"+phone+"','"+password+"')";

                try {
                    Conn c = new Conn();
                    c.s.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "User Details Added Successfully\nUsername: "+username+"\nName: "+name+"\nEmail: "+email+"\nAddress: "+address+"\nAadhar: "+aadhar+"\nPhone Number: "+phone);
                    setVisible(false);
                    new Login1();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Password and Confirm Password do not match");
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Adduser();
    }
}