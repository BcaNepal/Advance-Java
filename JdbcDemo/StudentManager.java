import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class StudentManager {
    // Database connection details
    final String DB_URL = "jdbc:mariadb://localhost/College";
    final String USER = "root";
    final String PASS = "root";
    Connection conn;

    // Construction to initializr the GUI and databse connection
    public StudentManager() {
        // Establish database connection
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Creating and setting up the main frame
        JFrame frame = new JFrame("StudentManager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);
        // Roll No label and text field
        JLabel labelRollNo = new JLabel("RollNo: ");
        labelRollNo.setBounds(50, 50, 100, 30);
        frame.add(labelRollNo);

        JTextField txtRollNo = new JTextField();
        txtRollNo.setBounds(150, 50, 200, 30);
        frame.add(txtRollNo);

        // Name label and text field
        JLabel labelName = new JLabel("Name: ");
        labelName.setBounds(50, 100, 100, 30);
        frame.add(labelName);
        JTextField txtName = new JTextField();
        txtName.setBounds(150, 100, 200, 30);
        frame.add(txtName);

        // Age label and text field
        JLabel labelAge = new JLabel("Age: ");
        labelAge.setBounds(50, 150, 100, 30);
        frame.add(labelAge);
        JTextField txtAge = new JTextField();
        txtAge.setBounds(150, 150, 200, 30);
        frame.add(txtAge);

        // Clear button
        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(50, 200, 100, 30);
        frame.add(btnClear);
        // Save button

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(160, 200, 100, 30);
        frame.add(btnSave);

        // Edit button
        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(270, 200, 100, 30);
        frame.add(btnEdit);

        // Delete button
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(50, 250, 100, 30);
        frame.add(btnDelete);

        // Find button
        JButton btnFind = new JButton("Find");
        btnFind.setBounds(160, 250, 100, 30);
        frame.add(btnFind);

        // Make the frame visible
        frame.setVisible(true);

        // Action Listerner for Clear button
        btnClear.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        // Clear text field
                        txtRollNo.setText("");
                        txtName.setText("");
                        txtAge.setText("");
                    }
                });

        // Action Listerner for Save button
        btnSave.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            // Insert new student into datbase
                            String sql = "INSERT INTO Students (rollno, name, age) VALUES (?, ?, ?)";
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.setInt(1, Integer.parseInt(txtRollNo.getText()));
                            ps.setString(2, txtName.getText());
                            ps.setInt(3, Integer.parseInt(txtAge.getText()));
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Student saved sucessfully");
                            // Clear the text field
                            txtRollNo.setText("");
                            txtName.setText("");
                            txtAge.setText("");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });

        // Action Listerner for Edit button
        btnEdit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            // Update students details in the datbase
                            String sql = "UPDATE Students SET name = ?, age = ? WHERE rollno = ?";
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.setString(1, txtName.getText());
                            ps.setInt(2, Integer.parseInt(txtAge.getText()));
                            ps.setInt(3, Integer.parseInt(txtRollNo.getText()));
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Student updated successfully!");
                            // Clear the text field
                            txtRollNo.setText("");
                            txtName.setText("");
                            txtAge.setText("");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });

        // Action Listerner for Delete button
        btnDelete.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            // Delete student from the database
                            String sql = "DELETE FROM Students WHERE rollno = ?";
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.setInt(1, Integer.parseInt(txtRollNo.getText()));
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Student deleted sucessfully");
                            // Clear the text field
                            txtRollNo.setText("");
                            txtName.setText("");
                            txtAge.setText("");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });

        // Action Listerner for Find button
        btnFind.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            // Find the student in databse
                            String sql = "SELECT * FROM Students WHERE rollno = ?";
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.setInt(1, Integer.parseInt(txtRollNo.getText()));
                            ResultSet rs = ps.executeQuery();
                            if (rs.next()) {
                                // Show text field with student details
                                txtName.setText(rs.getString("name"));
                                txtAge.setText(rs.getString("age"));
                            } else {
                                // Message if no student found
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Student not found");
                                // Clear the text fields
                                txtRollNo.setText("");
                                txtName.setText("");
                                txtAge.setText("");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    // Main method
    public static void main(String[] args) {
        new StudentManager();
    }
}
