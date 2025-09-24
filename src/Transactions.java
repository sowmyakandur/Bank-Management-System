import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Transactions extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;

    Transactions(String pin) {
        this.pin = pin;

        JLabel l1 = new JLabel("SELECT TRANSACTION");
        l1.setFont(new Font("Arial", Font.BOLD, 24));
        l1.setBounds(250, 50, 300, 30);
        add(l1);

        b1 = new JButton("DEPOSIT");
        b2 = new JButton("CASH WITHDRAWL");
        b3 = new JButton("FAST CASH");
        b4 = new JButton("MINI STATEMENT");
        b5 = new JButton("PIN CHANGE");
        b6 = new JButton("BALANCE ENQUIRY");
        b7 = new JButton("EXIT");

        b1.setBounds(100, 120, 250, 30);
        b2.setBounds(400, 120, 250, 30);
        b3.setBounds(100, 170, 250, 30);
        b4.setBounds(400, 170, 250, 30);
        b5.setBounds(100, 220, 250, 30);
        b6.setBounds(400, 220, 250, 30);
        b7.setBounds(250, 270, 250, 30);

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
        setSize(800, 480);
        setLocation(550, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            new Deposit(pin).setVisible(true);
        } else if (ae.getSource() == b2) {
            new Withdrawl(pin).setVisible(true);
        } else if (ae.getSource() == b3) {
            new FastCash(pin).setVisible(true);
        } else if (ae.getSource() == b4) {
            new MiniStatement(pin).setVisible(true);
        } else if (ae.getSource() == b5) {
            new Pin(pin).setVisible(true);
        } else if (ae.getSource() == b6) {
            new BalanceEnquiry(pin).setVisible(true);
        } else if (ae.getSource() == b7) {
            System.exit(0);
        }
        setVisible(false);
    }

    public static void main(String[] args) {
        new Transactions("");
    }
}
