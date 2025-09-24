import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1, b2, b3;

    Login() {
        setTitle("ATM");

        l1 = new JLabel("WELCOME TO ATM");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setBounds(200, 40, 450, 40);
        add(l1);

        l2 = new JLabel("Card No:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(125, 150, 375, 30);
        add(l2);

        tf1 = new JTextField();
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        tf1.setBounds(300, 150, 230, 30);
        add(tf1);

        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(125, 220, 375, 30);
        add(l3);

        pf2 = new JPasswordField();
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(300, 220, 230, 30);
        add(pf2);

        b1 = new JButton("SIGN IN");
        b2 = new JButton("CLEAR");
        b3 = new JButton("SIGN UP");

        b1.setBounds(300, 300, 100, 30);
        b2.setBounds(430, 300, 100, 30);
        b3.setBounds(300, 350, 230, 30);

        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setFont(new Font("Arial", Font.BOLD, 14));

        add(b1);
        add(b2);
        add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(800, 480);
        setLocation(550, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                Conn c1 = new Conn();
                String cardno = tf1.getText();
                String pin = new String(pf2.getPassword());
                String q = "SELECT * FROM login WHERE card_no = '" + cardno + "' AND pin = '" + pin + "'";
                ResultSet rs = c1.s.executeQuery(q);

                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } else if (ae.getSource() == b2) {
                tf1.setText("");
                pf2.setText("");
            } else if (ae.getSource() == b3) {
                setVisible(false);
                new Signup().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
