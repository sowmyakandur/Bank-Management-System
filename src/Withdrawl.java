import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener {
    JTextField t1;
    JButton b1, b2;
    String pin;

    Withdrawl(String pin) {
        this.pin = pin;

        JLabel l1 = new JLabel("ENTER AMOUNT TO WITHDRAW");
        l1.setBounds(200, 80, 300, 30);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(200, 130, 200, 30);
        add(t1);

        b1 = new JButton("WITHDRAW");
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
                String amountStr = t1.getText();
                if (amountStr.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter amount");
                    return;
                }
                double amount = Double.parseDouble(amountStr);

                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin='" + pin + "'");
                double balance = 0;
                while (rs.next()) {
                    if (rs.getString("mode").equals("Deposit"))
                        balance += Double.parseDouble(rs.getString("amount"));
                    else
                        balance -= Double.parseDouble(rs.getString("amount"));
                }

                if (balance < amount) {
                    JOptionPane.showMessageDialog(null, "Insufficient balance");
                    return;
                }

                c.s.executeUpdate("INSERT INTO bank VALUES('" + pin + "', NOW(), 'Withdrawl', '" + amount + "')");
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " withdrawn successfully");
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