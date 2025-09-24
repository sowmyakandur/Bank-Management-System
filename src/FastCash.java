import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class FastCash extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;

    FastCash(String pin) {
        this.pin = pin;

        JLabel l1 = new JLabel("SELECT FAST CASH AMOUNT");
        l1.setBounds(200, 50, 300, 30);
        add(l1);

        b1 = new JButton("500");
        b2 = new JButton("1000");
        b3 = new JButton("2000");
        b4 = new JButton("5000");
        b5 = new JButton("10000");
        b6 = new JButton("20000");
        b7 = new JButton("BACK");

        b1.setBounds(100, 120, 100, 30);
        b2.setBounds(250, 120, 100, 30);
        b3.setBounds(400, 120, 100, 30);
        b4.setBounds(100, 170, 100, 30);
        b5.setBounds(250, 170, 100, 30);
        b6.setBounds(400, 170, 100, 30);
        b7.setBounds(250, 220, 100, 30);

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        setLayout(null);
        setSize(600, 350);
        setLocation(550, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = "";
            if (ae.getSource() == b1) amount = "500";
            else if (ae.getSource() == b2) amount = "1000";
            else if (ae.getSource() == b3) amount = "2000";
            else if (ae.getSource() == b4) amount = "5000";
            else if (ae.getSource() == b5) amount = "10000";
            else if (ae.getSource() == b6) amount = "20000";
            else if (ae.getSource() == b7) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
                return;
            }

            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin='" + pin + "'");
            double balance = 0;
            while (rs.next()) {
                if (rs.getString("mode").equals("Deposit"))
                    balance += Double.parseDouble(rs.getString("amount"));
                else
                    balance -= Double.parseDouble(rs.getString("amount"));
            }

            if (balance < Double.parseDouble(amount)) {
                JOptionPane.showMessageDialog(null, "Insufficient balance");
                return;
            }

            c.s.executeUpdate("INSERT INTO bank VALUES('" + pin + "', NOW(), 'Withdrawl', '" + amount + "')");
            JOptionPane.showMessageDialog(null, "Rs. " + amount + " withdrawn successfully");
            setVisible(false);
            new Transactions(pin).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
