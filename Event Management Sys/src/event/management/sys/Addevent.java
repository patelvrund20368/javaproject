package event.management.sys;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class Addevent extends JFrame implements ActionListener {
    
    JTextField tename, tedescription, labelnop, teventid;
    JDateChooser dcdoe;
    JComboBox<String> cbev, cbet;
    JButton submit, cancel;
    
    Random ran = new Random();
    long eventId = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
    Addevent() {
        
        setSize(900, 700);
        setLocation(350, 50);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Event Details");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);
        
        JLabel lbleventid = new JLabel("Event ID");
        lbleventid.setBounds(50, 100, 200, 30);
        lbleventid.setFont(new Font("serif", Font.BOLD, 20));
        add(lbleventid);
        
        teventid = new JTextField(String.valueOf(eventId));
        teventid.setBounds(200, 100, 150, 30);
        teventid.setEditable(false);
        add(teventid);
        
        JLabel lblname = new JLabel("Event Name");
        lblname.setBounds(50, 150, 200, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);
        
        tename = new JTextField();
        tename.setBounds(200, 150, 150, 30);
        add(tename);
        
        JLabel lbldescription = new JLabel("Description");
        lbldescription.setBounds(400, 150, 200, 30);
        lbldescription.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldescription);
        
        tedescription = new JTextField();
        tedescription.setBounds(600, 150, 150, 30);
        add(tedescription);
        
        JLabel lblnop = new JLabel("No. of People");
        lblnop.setBounds(50, 200, 200, 30);
        lblnop.setFont(new Font("serif", Font.BOLD, 20));
        add(lblnop);
        
        labelnop = new JTextField();
        labelnop.setBounds(200, 200, 150, 30);
        add(labelnop);
        
        JLabel lbldoe = new JLabel("Date of Event");
        lbldoe.setBounds(400, 200, 200, 30);
        lbldoe.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldoe);
        
        dcdoe = new JDateChooser();
        dcdoe.setBounds(600, 200, 150, 30);
        add(dcdoe);
        
        JLabel lblev = new JLabel("Select Venue");
        lblev.setBounds(50, 250, 200, 30);
        lblev.setFont(new Font("serif", Font.BOLD, 20));
        add(lblev);
        
        String venues[] = {"Goa", "Kutch", "Dwarka", "Bhavnagar", "Jamnagar", "Div", "Vadodara", "Ahmedabad"};
        cbev = new JComboBox<>(venues);
        cbev.setBounds(200, 250, 150, 30);
        cbev.setBackground(Color.WHITE);
        add(cbev);
        
        JLabel lblet = new JLabel("No. of Hours");
        lblet.setBounds(400, 250, 200, 30);
        lblet.setFont(new Font("serif", Font.BOLD, 20));
        add(lblet);
        
        String hours[] = {"6", "8", "12", "16", "24", "48", "60"};
        cbet = new JComboBox<>(hours);
        cbet.setBounds(600, 250, 150, 30);
        cbet.setBackground(Color.WHITE);
        add(cbet);
        
        submit = new JButton("Submit");
        submit.setBounds(275, 450, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(475, 450, 140, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String eventId = teventid.getText();
            String name = tename.getText();
            String description = tedescription.getText();
            String nop = labelnop.getText();
            String doe = ((JTextField) dcdoe.getDateEditor().getUiComponent()).getText();
            String venue = (String) cbev.getSelectedItem();
            String hours = (String) cbet.getSelectedItem();
            
            try {
                String query = "insert into event (event_id, name, description, nop, doe, venue, hours) values('"+eventId+"', '"+name+"', '"+description+"', '"+nop+"', '"+doe+"', '"+venue+"', '"+hours+"')";

                Conn con = new Conn();
                con.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Event Details Inserted Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new Addevent();
    }
}
