package event.management.sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login1 extends JFrame implements ActionListener{

    JButton login, cancel, register;
    JTextField tfemail;
    JPasswordField tfpassword;

    Login1 () {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Email");
        lblusername.setBounds(40, 20, 100, 20);
        add(lblusername);

        tfemail = new JTextField();
        tfemail.setBounds(150, 20, 150, 20);
        add(tfemail);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 20);
        add(tfpassword);

        login = new JButton("Login");
        login.setBounds(40, 140, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        login.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 140, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        register = new JButton("Register");
        register.setBounds(40, 180, 120, 30);
        register.setBackground(Color.BLACK);
        register.setForeground(Color.WHITE);
        register.addActionListener(this);
        register.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(register);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);

        setSize(600, 350);
        setLocation(500, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String email = tfemail.getText();
            String password = String.valueOf(tfpassword.getPassword());

            String query = "select * from user where email='"+email+"' and password='"+password+"'";

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    new Userpage();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        } else if (ae.getSource() == register) {
            setVisible(false);
            new Adduser();
        }
   }

    public static void main(String[]args) {
        new Login1();
    }
}