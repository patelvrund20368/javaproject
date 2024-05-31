package event.management.sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateUserDetails extends JFrame implements ActionListener {

    JTextField tfname, tfcurrentEmail, tfnewEmail, tfphone;
    JPasswordField tfpassword, tfconfirmpassword;
    JButton update, cancel;
    Conn conn;

    UpdateUserDetails() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(40, 20, 100, 20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(150, 20, 150, 20);
        add(tfname);

        JLabel lblcurrentEmail = new JLabel("Current Email");
        lblcurrentEmail.setBounds(40, 70, 100, 20);
        add(lblcurrentEmail);

        tfcurrentEmail = new JTextField();
        tfcurrentEmail.setBounds(150, 70, 150, 20);
        add(tfcurrentEmail);

        JLabel lblnewEmail = new JLabel("New Email");
        lblnewEmail.setBounds(40, 120, 100, 20);
        add(lblnewEmail);

        tfnewEmail = new JTextField();
        tfnewEmail.setBounds(150, 120, 150, 20);
        add(tfnewEmail);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(40, 170, 100, 20);
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(150, 170, 150, 20);
        add(tfphone);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 220, 100, 20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 220, 150, 20);
        add(tfpassword);

        JLabel lblconfirmpassword = new JLabel("Confirm Password");
        lblconfirmpassword.setBounds(40, 270, 150, 20);
        add(lblconfirmpassword);

        tfconfirmpassword = new JPasswordField();
        tfconfirmpassword.setBounds(150, 270, 150, 20);
        add(tfconfirmpassword);

        update = new JButton("Update");
        update.setBounds(40, 320, 120, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        update.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 320, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        conn = new Conn();

        setSize(400, 400);
        setLocation(500, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            String newEmail = tfnewEmail.getText();

            try {
                Connection con = conn.getConnection();
                PreparedStatement pst = con.prepareStatement("UPDATE user SET email=?, name=?, phone=?, password=? WHERE email=?");
                pst.setString(1, newEmail);
                pst.setString(2, tfname.getText());
                pst.setString(3, tfphone.getText());
                pst.setString(4, String.valueOf(tfpassword.getPassword()));
                pst.setString(5, tfcurrentEmail.getText());
                int i = pst.executeUpdate();

                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "User details Updated Successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Update");
                }
                con.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateUserDetails();
    }
}
