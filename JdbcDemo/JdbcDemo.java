import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class JdbcDemo {

  private static void placeComponents(JPanel panel) {
    // define variable
    String load = "org.mariadb.jdbc.Driver";
    String url = "jdbc:mariadb://localhost/Bca6";
    String username = "root";
    String password = "password";
    // End variable
    panel.setLayout(null);
    // Name label
    JLabel nameLabel = new JLabel("Name:");
    nameLabel.setBounds(10, 20, 80, 25);
    panel.add(nameLabel);
    // name text
    JTextField txtName = new JTextField(20);
    txtName.setBounds(100, 20, 165, 25);
    panel.add(txtName);
    // End of Name
    //
    // Age LABEL
    JLabel ageLabel = new JLabel("Age:");
    ageLabel.setBounds(10, 50, 80, 25);
    panel.add(ageLabel);
    // Age TEXT
    JTextField txtAge = new JTextField(20);
    txtAge.setBounds(100, 50, 165, 25);
    panel.add(txtAge);
    // END of AGE
    //
    // Roll number
    JLabel rollNoLabel = new JLabel("Roll No");
    rollNoLabel.setBounds(10, 80, 90, 25);
    panel.add(rollNoLabel);
    // Roll number Text
    JTextField txtRollNo = new JTextField(20);
    txtRollNo.setBounds(100, 80, 165, 25);
    panel.add(txtRollNo);
    // END of .... label and text Field
    //
    // Start of Button
    // BUTTON OF CLEAR
    JButton btnClear = new JButton("Clear");
    btnClear.setBounds(10, 150, 120, 12);
    panel.add(btnClear);

    // BUTTON OF SAVE
    JButton btnSave = new JButton("Save");
    btnSave.setBounds(10, 150, 120, 12);
    panel.add(btnSave);

    // BUTTON OF EDIT
    JButton btnEdit = new JButton("Edit");
    btnEdit.setBounds(10, 150, 120, 12);
    panel.add(btnEdit);

    // BUTTON OF DELETE
    JButton btnDelete = new JButton("Delete");
    btnDelete.setBounds(20, 42, 24, 24);
    panel.add(btnDelete);

    // BUTTON OF FIND
    JButton btnFind = new JButton("Find");
    btnFind.setBounds(30, 32, 120, 12);
    panel.add(btnFind);

    // BUTTON OF UPDATE
    JButton btnUpdate = new JButton("Update");
    btnUpdate.setBounds(10, 110, 150, 25);
    panel.add(btnUpdate);
    // BUTTON OF STATUS
    JLabel statusLabel = new JLabel("");
    statusLabel.setBounds(10, 140, 250, 25);
    panel.add(statusLabel);
    // END OF BUTTONS
    //
    // ActionListener Interface
    // for Find
    btnFind.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Class.forName(load);// load the drive
          Connection conn = DriverManager.getConnection(url, username, password); // Get
          PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE rollno=?"); // Query form
          ps.setInt(1, Integer.parseInt(txtRollNo.getText()));
          ResultSet rs = ps.executeQuery();
          if (rs.next()) {
            txtName.setText(rs.getString(2));
            txtAge.setText(String.valueOf(rs.getInt(3)));
          }
        } catch (SQLException ex) {
          System.err.println("SQL error:" + ex);
        } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
        }
      }
    });
    // End of ActionListener for btnFind
    //
    // Start of ActionListener btnUpdate
    btnUpdate.addActionListener(new ActionListener() {
      String name;
      int age, rollno;

      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Class.forName(load);
          Connection conn = DriverManager.getConnection(url, username, password);
          PreparedStatement ps = conn.prepareStatement("UPDATE Student SET name=?, age=? WHERE rollno=?");
          ps.setString(1, name);
          ps.setInt(2, age);
          ps.setInt(2, rollno);
          ps.executeQuery();
        } catch (SQLException er) {
          System.err.println("SQL erro:" + er);
        } catch (ClassNotFoundException er) {
          System.err.println("Class not found" + er);
        }
      }
    });
    // TODO: add ActionListener for clear, save, edit, Delete
    // End of ActionListener for btnUpdate
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Jdbc Demo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 300);
    JPanel panel = new JPanel();
    frame.add(panel);
    frame.setVisible(true);
    // new placeComponents();
  }
}
