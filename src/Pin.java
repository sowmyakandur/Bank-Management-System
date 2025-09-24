import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Pin extends JFrame implements ActionListener {
    JPasswordField pf1, pf2;
    JButton b1, b2;
    String pin;

    Pin(String pin) {
        this.pin = pin;

        JLabel l1 = new JLabel("Enter New PIN");
        l1.setBounds(150, 50, 200, 30);
        add(l1);

        pf1 = new JPasswordField();
        pf1.setBounds(300, 50, 200, 30);
        add(pf1);

        JLabel l2 = new JLabel("Re-Enter New PIN");
        l2.setBounds(150, 100, 200, 30);
        add(l2);

        pf2 = new JPasswordField();
        pf2.setBounds(300, 100, 200, 30);
        add(pf2);

        b1 = new JButton("CHANGE");
        b2 = new JButton("BACK");
        b1.setBounds(200, 150, 100, 30);
        b2.setBounds(320, 150, 100, 30);
        add(b1); add(b2);

        b1.addActionListener(this); b2.addActionListener(this);

        setLayout(null);
        setSize(600, 250);
        setLocation(550, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                String newPin = pf1.getText();
                String rePin = pf2.getText();
                if (!newPin.equals(rePin)) {
                    JOptionPane.showMessageDialog(null, "PINs do not match");
                    return;
                }
                Conn c = new Conn();
                c.s.executeUpdate("UPDATE login SET pin='" + newPin + "' WHERE pin='" + pin + "'");
                c.s.executeUpdate("UPDATE bank SET pin='" + newPin + "' WHERE pin='" + pin + "'");
                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(newPin).setVisible(true);
            } else if (ae.getSource() == b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
