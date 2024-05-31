package event.management.sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Userpayment extends JFrame implements ActionListener {
    JTextField tfName, tfPhone, tfPaymentStatus, tfPaymentAmount;
    JButton submit, cancel;
    Choice cPaymentId;
    JLabel lblName, lblPhone, lblPaymentAmount, lblPaymentStatus;
    
    Userpayment() {
        setSize(500, 500);
        setLocation(550, 150);
        setLayout(null);
        
        JLabel heading = new JLabel("Payment Status");
        heading.setBounds(50, 10, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(heading);
        
        JLabel lblPaymentId = new JLabel("Select Payment ID");
        lblPaymentId.setBounds(50, 60, 200, 20);
        lblPaymentId.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblPaymentId);
        
        cPaymentId = new Choice();
        cPaymentId.setBounds(250, 60, 200, 20);
        add(cPaymentId);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select payment_id from userpayment");
            while (rs.next()) {
                cPaymentId.add(rs.getString("payment_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        lblName = new JLabel("Name");
        lblName.setBounds(50, 100, 200, 30);
        lblName.setFont(new Font("serif", Font.BOLD, 20));
        add(lblName);
        
        tfName = new JTextField();
        tfName.setBounds(250, 100, 200, 30);
        tfName.setEditable(false);
        add(tfName);
        
        lblPhone = new JLabel("Phone");
        lblPhone.setBounds(50, 150, 200, 30);
        lblPhone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblPhone);
        
        tfPhone = new JTextField();
        tfPhone.setBounds(250, 150, 200, 30);
        tfPhone.setEditable(false);
        add(tfPhone);
        
        lblPaymentAmount = new JLabel("Payment Amount");
        lblPaymentAmount.setBounds(50, 200, 200, 30);
        lblPaymentAmount.setFont(new Font("serif", Font.BOLD, 20));
        add(lblPaymentAmount);
        
        tfPaymentAmount = new JTextField();
        tfPaymentAmount.setBounds(250, 200, 200, 30);
        tfPaymentAmount.setEditable(false);
        add(tfPaymentAmount);
        
        lblPaymentStatus = new JLabel("Payment Status");
        lblPaymentStatus.setBounds(50, 250, 200, 30);
        lblPaymentStatus.setFont(new Font("serif", Font.BOLD, 20));
        add(lblPaymentStatus);
        
        tfPaymentStatus = new JTextField();
        tfPaymentStatus.setBounds(250, 250, 200, 30);
        add(tfPaymentStatus);
        
        submit = new JButton("Update");
        submit.setBounds(150, 350, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(300, 350, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        cPaymentId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                fetchPaymentDetails(cPaymentId.getSelectedItem());
            }
        });
        
        setVisible(true);
    }
    
    public void fetchPaymentDetails(String paymentId) {
        try {
            Conn c = new Conn();
            String query = "select * from userpayment where payment_id = '" + paymentId + "'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                tfName.setText(rs.getString("name"));
                tfPhone.setText(rs.getString("phone"));
                tfPaymentAmount.setText(rs.getString("payment_amount"));
                tfPaymentStatus.setText(rs.getString("payment_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String paymentId = cPaymentId.getSelectedItem();
            String paymentStatus = tfPaymentStatus.getText();
            
            try {
                String query = "update userpayment set payment_status='" + paymentStatus + "' where payment_id='" + paymentId + "'";
                Conn con = new Conn();
                con.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Payment Status Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new Userpayment();
    }
}
