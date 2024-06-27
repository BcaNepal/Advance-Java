import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class SwingDemo implements ActionListener {
  JButton btnSubmit, btnReset;
  JLabel lblName, lblAddress, lblEmail;
  JTextField txtName, txtAddress, txtEmail;
  JFrame f;
  JPanel p;

  public SwingDemo() {
    // btn imstance
    btnSubmit = new JButton("Button");
    btnReset = new JButton("Reset");
    // listener for
    btnSubmit.addActionListener(this);
    btnReset.addActionListener(this);

    // JLabel
    lblName = new JLabel("Name");
    lblName.setFont(new Font("Serif", Font.BOLD, 20));
    lblAddress = new JLabel("Address");
    lblAddress.setFont(new Font("Serif", Font.BOLD, 20));
    lblEmail = new JLabel("Email");
    lblEmail.setFont(new Font("Serif", Font.BOLD, 20));
    // TextField
    txtName = new JTextField("Name");
    txtName.setColumns(20);
    txtAddress = new JTextField("Address");
    txtAddress.setColumns(20);
    txtEmail = new JTextField("Email");
    txtEmail.setColumns(20);
    f = new JFrame();
    p = new JPanel();

    f.setVisible(true);
    f.setSize(600, 500);

    f.add(p);

    p.add(btnReset);
    p.add(btnSubmit);
    p.add(lblName);
    p.add(lblAddress);
    p.add(lblEmail);
    p.add(txtName);
    p.add(txtAddress);
    p.add(txtEmail);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String name = txtName.getText();
    System.out.println(name);
  }

  public static void main(String[] args) {
    new SwingDemo();
  }
}
