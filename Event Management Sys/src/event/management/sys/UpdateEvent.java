package event.management.sys;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


public class UpdateEvent extends JFrame implements ActionListener {
    
    JTextField tename, tedescription, labelnop, teventid;
    JDateChooser dcdoe;
    JComboBox<String> cbev, cbet;
    JButton update, cancel;
    
    UpdateEvent() {
        
        setSize(900, 700);
        setLocation(350, 50);
        setLayout(null);
        
        JLabel heading = new JLabel("Update Event Details");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);
        
        JLabel lbleventid = new JLabel("Enter Event ID");
        lbleventid.setBounds(50, 100, 200, 30);
        lbleventid.setFont(new Font("serif", Font.BOLD, 20));
        add(lbleventid);
        
        teventid = new JTextField();
        teventid.setBounds(250, 100, 150, 30);
        add(teventid);
        
        update = new JButton("Update");
        update.setBounds(250, 150, 120, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        update.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(update);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(400, 150, 140, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            String eventId = teventid.getText();
            fetchEventDetails(eventId);
        } else {
            setVisible(false);
        }
    }
    
    public void fetchEventDetails(String eid) {
        try {
            Conn con = new Conn();
            String query = "select * from event where event_id = '"+eid+"'";
            ResultSet rs = con.s.executeQuery(query);
            if (rs.next()) {
                // If event ID exists, open the Update Event Form
                openUpdateEventForm(eid);
            } else {
                // If event ID doesn't exist, show error message
                JOptionPane.showMessageDialog(null, "Invalid Event ID. Please enter a valid Event ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void openUpdateEventForm(String eid) {
    JFrame updateFrame = new JFrame();
    updateFrame.setSize(900, 700);
    updateFrame.setLocation(350, 50);
    updateFrame.setLayout(null);
    
    JLabel heading = new JLabel("Update Event Details");
    heading.setBounds(310, 30, 500, 50);
    heading.setFont(new Font("serif", Font.BOLD, 30));
    updateFrame.add(heading);
    
    JLabel lbleventid = new JLabel("Event ID");
    lbleventid.setBounds(50, 100, 200, 30);
    lbleventid.setFont(new Font("serif", Font.BOLD, 20));
    updateFrame.add(lbleventid);
    
    teventid = new JTextField(eid);
    teventid.setBounds(200, 100, 150, 30);
    teventid.setEditable(false);
    updateFrame.add(teventid);
    
    JLabel lblname = new JLabel("Event Name");
    lblname.setBounds(50, 150, 200, 30);
    lblname.setFont(new Font("serif", Font.BOLD, 20));
    updateFrame.add(lblname);
    
    tename = new JTextField();
    tename.setBounds(200, 150, 150, 30);
    updateFrame.add(tename);
    
    JLabel lbldescription = new JLabel("Description");
    lbldescription.setBounds(400, 150, 200, 30);
    lbldescription.setFont(new Font("serif", Font.BOLD, 20));
    updateFrame.add(lbldescription);
    
    tedescription = new JTextField();
    tedescription.setBounds(600, 150, 150, 30);
    updateFrame.add(tedescription);
    
    JLabel lblnop = new JLabel("No. of People");
    lblnop.setBounds(50, 200, 200, 30);
    lblnop.setFont(new Font("serif", Font.BOLD, 20));
    updateFrame.add(lblnop);
    
    labelnop = new JTextField();
    labelnop.setBounds(200, 200, 150, 30);
    updateFrame.add(labelnop);
    
    JLabel lbldoe = new JLabel("Date of Event");
    lbldoe.setBounds(400, 200, 200, 30);
    lbldoe.setFont(new Font("serif", Font.BOLD, 20));
    updateFrame.add(lbldoe);
    
    dcdoe = new JDateChooser();
    dcdoe.setBounds(600, 200, 150, 30);
    updateFrame.add(dcdoe);
    
    JLabel lblev = new JLabel("Select Venue");
    lblev.setBounds(50, 250, 200, 30);
    lblev.setFont(new Font("serif", Font.BOLD, 20));
    updateFrame.add(lblev);
    
    String venues[] = {"Goa", "Kutch", "Dwarka", "Bhavnagar", "Jamnagar", "Div", "Vadodara", "Ahmedabad"};
    cbev = new JComboBox<>(venues);
    cbev.setBounds(200, 250, 150, 30);
    cbev.setBackground(Color.WHITE);
    updateFrame.add(cbev);
    
    JLabel lblet = new JLabel("No. of Hours");
    lblet.setBounds(400, 250, 200, 30);
    lblet.setFont(new Font("serif", Font.BOLD, 20));
    updateFrame.add(lblet);
    
    String hours[] = {"6", "8", "12", "16", "24", "48", "60"};
    cbet = new JComboBox<>(hours);
    cbet.setBounds(600, 250, 150, 30);
    cbet.setBackground(Color.WHITE);
    updateFrame.add(cbet);
    
    JButton updateBtn = new JButton("Update");
    updateBtn.setBounds(175, 450, 120, 30);
    updateBtn.setBackground(Color.BLACK);
    updateBtn.setForeground(Color.WHITE);
    updateBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            String eventId = teventid.getText();
            String name = tename.getText();
            String description = tedescription.getText();
            String nop = labelnop.getText();
            String doe = ((JTextField) dcdoe.getDateEditor().getUiComponent()).getText();
            String venue = (String) cbev.getSelectedItem();
            String hours = (String) cbet.getSelectedItem();

            // Convert date to correct format
            try {
                SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MMM-yyyy");
                SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = originalFormat.parse(doe);
                String formattedDate = targetFormat.format(date);

                String query = "update event set name='" + name + "', description='" + description + "', nop='" + nop + "', doe='" + formattedDate + "', venue='" + venue + "', hours='" + hours + "' where event_id='" + eventId + "'";

                Conn con = new Conn();
                con.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Event Details Updated Successfully");
                updateFrame.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    });
    updateFrame.add(updateBtn);
    
    JButton deleteBtn = new JButton("Delete");
    deleteBtn.setBounds(325, 450, 120, 30);
    deleteBtn.setBackground(Color.RED);
    deleteBtn.setForeground(Color.WHITE);
    deleteBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            String eventId = teventid.getText();
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this event?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    String query = "delete from event where event_id='" + eventId + "'";
                    Conn con = new Conn();
                    con.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Event Deleted Successfully");
                    updateFrame.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        }
    });
    updateFrame.add(deleteBtn);
    
    JButton cancelBtn = new JButton("Cancel");
    cancelBtn.setBounds(475, 450, 140, 30);
    cancelBtn.setBackground(Color.BLACK);
    cancelBtn.setForeground(Color.WHITE);
    cancelBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            updateFrame.setVisible(false);
        }
    });
    updateFrame.add(cancelBtn);
    
    updateFrame.setVisible(true);
}

    
    public static void main(String[] args) {
        new UpdateEvent();
    }
}
