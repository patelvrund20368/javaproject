package event.management.sys;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class Eventdetails extends JFrame implements ActionListener {

    Choice Eid, doe;
    JTable table;
    JButton search, print, update, add, cancel;
    JLabel doeLabel;
    JTextField doeField;
    
    Eventdetails() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Search by Event ID");
        heading.setBounds(20, 20, 150, 20);
        add(heading);
        
        Eid = new Choice();
        Eid.setBounds(180, 20, 150, 20);
        add(Eid);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from event");
            while(rs.next()) {
                Eid.add(rs.getString("event_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        doeLabel = new JLabel("Search by Date of Event (yyyy-mm-dd)");
        doeLabel.setBounds(20, 50, 200, 20);
        add(doeLabel);
        
        doeField = new JTextField();
        doeField.setBounds(220, 50, 150, 20);
        add(doeField);
        
        table = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from event");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            if (!doeField.getText().isEmpty()) {
                String query = "select * from event where doe = '"+doeField.getText()+"'";
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String query = "select * from event where event_id = '"+Eid.getSelectedItem()+"'";
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == add) {
            setVisible(false);
            new Addevent();
        } else if (ae.getSource() == update) {
            String selectedEventId = Eid.getSelectedItem();
            setVisible(false);
            new UpdateEvent();
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Eventdetails();
    }
}