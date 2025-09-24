import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame {
    BalanceEnquiry(String pin) {
        setTitle("Balance Enquiry");

        JLabel l1 = new JLabel();
        l1.setBounds(50, 50, 400, 30);
        add(l1);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin='" + pin + "'");
            double balance = 0;
            while (rs.next()) {
                String type = rs.getString("mode");
                double amount = Double.parseDouble(rs.getString("amount"));
                if (type.equals("Deposit")) balance += amount;
                else balance -= amount;
            }
            l1.setText("Your current balance is Rs. " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(null);
        setSize(500, 200);
        setLocation(550, 200);
        setVisible(true);
    }
}
