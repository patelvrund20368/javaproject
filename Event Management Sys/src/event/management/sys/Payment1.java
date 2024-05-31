package event.management.sys;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class Payment1 extends JFrame implements ActionListener {

    Choice paymentIdChoice;
    JTable table;
    JButton search, print, update, add, cancel;

    public Payment1() {
        // Frame setup
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by Payment ID");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        paymentIdChoice = new Choice();
        paymentIdChoice.setBounds(180, 20, 150, 20);
        add(paymentIdChoice);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM userpayment");
            while (rs.next()) {
                paymentIdChoice.add(rs.getString("payment_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        updateTable();

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);

        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        add = new JButton("Add");
        add.setBounds(220, 70, 80, 20);
        add.addActionListener(this);
        add(add);

        update = new JButton("Update");
        update.setBounds(320, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(420, 70, 80, 20);
        cancel.addActionListener(this);
        add(cancel);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    private void updateTable() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM userpayment");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "SELECT * FROM userpayment WHERE payment_id = '" + paymentIdChoice.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == add) {
            // Implement add functionality here
        } else if (ae.getSource() == update) {
            // Implement update functionality here
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Payment1();
    }
}
