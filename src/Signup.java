import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class Signup extends JFrame implements ActionListener {

    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JRadioButton r1,r2,r3,r4,r5;
    JButton b;
    JDateChooser dateChooser;

    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L) + 1000L;
    String first = "" + Math.abs(first4);

    Signup() {
        setTitle("NEW ACCOUNT APPLICATION FORM");
        setLayout(null);

        l1 = new JLabel("APPLICATION FORM NO. " + first);
        l1.setFont(new Font("Raleway", Font.BOLD, 32));
        l1.setBounds(140, 20, 600, 40); add(l1);

        l2 = new JLabel("Page 1: Personal Details");
        l2.setFont(new Font("Raleway", Font.BOLD, 22));
        l2.setBounds(290, 70, 600, 30); add(l2);

        l3 = new JLabel("Name:"); l3.setBounds(100, 120, 200, 30); add(l3);
        t1 = new JTextField(); t1.setBounds(300, 120, 400, 30); add(t1);

        l4 = new JLabel("Father's Name:"); l4.setBounds(100, 170, 200, 30); add(l4);
        t2 = new JTextField(); t2.setBounds(300, 170, 400, 30); add(t2);

        l5 = new JLabel("Date of Birth:"); l5.setBounds(100, 220, 200, 30); add(l5);
        dateChooser = new JDateChooser(); dateChooser.setBounds(300, 220, 400, 30); add(dateChooser);

        l6 = new JLabel("Gender:"); l6.setBounds(100, 270, 200, 30); add(l6);
        r1 = new JRadioButton("Male"); r1.setBackground(Color.WHITE); r1.setBounds(300,270,100,30); add(r1);
        r2 = new JRadioButton("Female"); r2.setBackground(Color.WHITE); r2.setBounds(450,270,100,30); add(r2);
        ButtonGroup groupgender = new ButtonGroup(); groupgender.add(r1); groupgender.add(r2);

        l7 = new JLabel("Email Address:"); l7.setBounds(100, 320, 200, 30); add(l7);
        t3 = new JTextField(); t3.setBounds(300,320,400,30); add(t3);

        l8 = new JLabel("Marital Status:"); l8.setBounds(100, 370, 200, 30); add(l8);
        r3 = new JRadioButton("Married"); r3.setBackground(Color.WHITE); r3.setBounds(300,370,100,30); add(r3);
        r4 = new JRadioButton("Unmarried"); r4.setBackground(Color.WHITE); r4.setBounds(450,370,100,30); add(r4);
        r5 = new JRadioButton("Other"); r5.setBackground(Color.WHITE); r5.setBounds(600,370,100,30); add(r5);
        ButtonGroup groupstatus = new ButtonGroup(); groupstatus.add(r3); groupstatus.add(r4); groupstatus.add(r5);

        l9 = new JLabel("Address:"); l9.setBounds(100, 420, 200, 30); add(l9);
        t4 = new JTextField(); t4.setBounds(300,420,400,30); add(t4);

        l10 = new JLabel("City:"); l10.setBounds(100, 470, 200, 30); add(l10);
        t5 = new JTextField(); t5.setBounds(300,470,400,30); add(t5);

        l11 = new JLabel("Pin Code:"); l11.setBounds(100, 520, 200, 30); add(l11);
        t6 = new JTextField(); t6.setBounds(300,520,400,30); add(t6);

        l12 = new JLabel("State:"); l12.setBounds(100, 570, 200, 30); add(l12);
        t7 = new JTextField(); t7.setBounds(300,570,400,30); add(t7);

        b = new JButton("Next"); b.setBounds(600, 620, 120, 35); b.setBackground(Color.BLACK); b.setForeground(Color.WHITE); add(b);
        b.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(900, 720);
        setLocation(400, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String formno = first;
        String name = t1.getText();
        String fname = t2.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : null;
        String email = t3.getText();
        String marital = r3.isSelected() ? "Married" : r4.isSelected() ? "Unmarried" : r5.isSelected() ? "Other" : null;
        String address = t4.getText();
        String city = t5.getText();
        String pincode = t6.getText();
        String state = t7.getText();

        try {
            if (pincode.equals("")) JOptionPane.showMessageDialog(null, "Fill all the required fields");
            else {
                Conn c1 = new Conn();
                String q1 = "insert into signup values('" + formno + "','" + name + "','" + fname + "','" + dob + "','" + gender + "','" + email + "','" + marital + "','" + address + "','" + city + "','" + pincode + "','" + state + "')";
                c1.s.executeUpdate(q1);
                new Signup2(first).setVisible(true);
                setVisible(false);
            }
        } catch(Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        new Signup().setVisible(true);
    }
}
