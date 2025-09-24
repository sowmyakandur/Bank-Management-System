import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame {
    MiniStatement(String pin) {
        setTitle("Mini Statement");

        JTextArea area = new JTextArea();
        area.setBounds(20, 20, 500, 400);
        add(area);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin='" + pin + "'");
            double balance = 0;
            area.append("Date\t\tType\t\tAmount\n\n");
            while (rs.next()) {
                String type = rs.getString("mode");
                String amount = rs.getString("amount");
                String date = rs.getString("date");
                area.append(date + "\t" + type + "\t" + amount + "\n");
                if (type.equals("Deposit")) balance += Double.parseDouble(amount);
                else balance -= Double.parseDouble(amount);
            }
            area.append("\nCurrent Balance: " + balance);
        } catch (Exception e) { e.printStackTrace(); }

        setLayout(null);
        setSize(550, 500);
        setLocation(550, 200);
        setVisible(true);
    }
}
