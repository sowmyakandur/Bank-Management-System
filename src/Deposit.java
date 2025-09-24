import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    JTextField t1;
    JButton b1, b2;
    String pin;

    Deposit(String pin) {
        this.pin = pin;

        JLabel l1 = new JLabel("ENTER AMOUNT TO DEPOSIT");
        l1.setBounds(200, 80, 300, 30);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(200, 130, 200, 30);
        add(t1);

        b1 = new JButton("DEPOSIT");
        b2 = new JButton("BACK");
        b1.setBounds(200, 180, 100, 30);
        b2.setBounds(320, 180, 100, 30);
        add(b1); add(b2);

        b1.addActionListener(this); b2.addActionListener(this);

        setLayout(null);
        setSize(600, 350);
        setLocation(550, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                String amount = t1.getText();
                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter amount");
                    return;
                }
                Conn c = new Conn();
                c.s.executeUpdate("INSERT INTO bank VALUES('" + pin + "', NOW(), 'Deposit', '" + amount + "')");
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " deposited successfully");
                setVisible(false);
                new Transactions(pin).setVisible(true);
            } else if (ae.getSource() == b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
