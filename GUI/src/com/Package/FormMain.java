package com.Package;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FormMain {
    private TableGrid tableGrid;
    private JTable jTable;
    private DefaultTableModel tableModel;
    private JPanel panel;
    private JTextField txtName;
    private JComboBox<Integer> cmbID;
    private JTextField txtDepart;
    private JButton btnSave;
    private static final String url="jdbc:postgresql://localhost:5432/DataBase";
    private static final String user="postgres";
    private static final String password = "8520";
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField txtPhone;
    private JTextField txtAge;
    private JRadioButton rbtnmale;
    private JRadioButton rbtnfemale;
    private JButton btnShowAll;
    private JTextField txtSalary;
    private PreparedStatement insertStatement, updateStatement, showStatement , deleteStatement , selectIDsStatement;
    private final List<Integer> IDs;
    private final List<Employee> employees;


    public FormMain() {
        Connection connection;
        employees=new ArrayList<>();
        tableModel=new DefaultTableModel();
        jTable=new JTable(tableModel);
        tableGrid = new TableGrid(jTable,employees);
        try {
            connection= DriverManager.getConnection(url,user,password);
            insertStatement = connection.prepareStatement("insert into employees (\"id\",\"Age\",department,gender,\"name\",phone,salary)"
                    +" values (?,?,?,?,?,?,?)");
            showStatement = connection.prepareStatement("SELECT * FROM employees");
            updateStatement = connection.prepareStatement("UPDATE employees " +
                    "SET \"id\"=?,\"Age\"=?,department=?,gender=?,\"name\"=?,phone=?,salary=? WHERE id=?");
            selectIDsStatement = connection.prepareStatement("select id from Employees");
            deleteStatement = connection.prepareStatement("DELETE FROM employees WHERE id = ? ;");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        IDs = new ArrayList<>();
        try {
            ResultSet IDsResult = null;
            if (selectIDsStatement != null) {
                IDsResult = selectIDsStatement.executeQuery();
            }
            if (IDsResult != null) {
                while (IDsResult.next()) {
                    int id = IDsResult.getInt("id");
                    cmbID.addItem(id);
                    IDs.add(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cmbID.setSelectedItem(null);
        btnSave.addActionListener(e -> saveEmployee());
        btnShowAll.addActionListener(e -> showEmployees());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
    }

    private void showEmployees() {
        try {
            ResultSet resultSet= showStatement.executeQuery();
            tableModel.setRowCount(0);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String department = resultSet.getString("department");
                String gender = resultSet.getString("gender");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                double salary = resultSet.getDouble("salary");
                int age = resultSet.getInt("age");

                Employee employee=new Employee(id,phone,age,name,gender,department,salary);
                employees.add(employee);
                tableModel.addRow(new Object[]{employee});

            }
            jTable=new JTable(tableModel);
            jTable.setVisible(true);

            tableGrid=new TableGrid(jTable,employees);

            resultSet.close();
            showStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private void deleteEmployee() {
        int ID=Integer.parseInt(Objects.requireNonNull(cmbID.getSelectedItem()).toString());
        if (Objects.requireNonNull(cmbID.getSelectedItem()).toString() != null && IDs.contains(ID) ) {
            try {
                deleteStatement.setInt(1,ID);
                deleteStatement.executeUpdate();
                cmbID.removeItem(cmbID.getSelectedItem().toString());
                clearFields();
                JOptionPane.showMessageDialog(null, "Successful to delete employee data.", "Delete Employee", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else
            JOptionPane.showMessageDialog(null,"Please Select Correct ID.");
    }

    private String Gender(){
        if (rbtnmale.isSelected()) {
            return rbtnmale.getText();
        }else
            return rbtnfemale.getText();
    }
    private void saveEmployee() {
        try {
            String id = Objects.requireNonNull(cmbID.getSelectedItem()).toString();
            String department = txtDepart.getText();
            String gender = Gender();
            String name = txtName.getText();
            String phone = txtPhone.getText();
            String salary = txtSalary.getText();
            String Age = txtAge.getText();

            // Set the parameter values for the prepared statement
            insertStatement.setInt(1, Integer.parseInt(id));
            insertStatement.setInt(2, Integer.parseInt(Age));
            insertStatement.setString(3, department);
            insertStatement.setString(4, gender);
            insertStatement.setString(5, name);
            insertStatement.setString(6, phone);
            insertStatement.setInt(7, Integer.parseInt(salary));
            Employee employee=new Employee(Integer.parseInt(id),phone,Integer.parseInt(Age),name,gender,department,(int)Double.parseDouble(salary));

            employees.add(employee);
            // Execute the insert statement
            insertStatement.executeUpdate();
            clearFields();
            JOptionPane.showMessageDialog(null, "Successful to save employee data.", "Save Employee Data",
                    JOptionPane.INFORMATION_MESSAGE);


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to save employee data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployee() {
        try {
            if (IDs.contains(Integer.parseInt(Objects.requireNonNull(cmbID.getSelectedItem()).toString())) ) {
                String department = txtDepart.getText();
                String gender = Gender();
                String name = txtName.getText();
                String phone = txtPhone.getText();
                String salary = txtSalary.getText();
                String id = cmbID.toString();
                String Age = txtAge.getText();

                // Set the parameter values for the prepared statement
                updateStatement.setInt(1, Integer.parseInt(id));
                updateStatement.setInt(2, Integer.parseInt(Age));
                updateStatement.setString(3, department);
                updateStatement.setString(4, gender);
                updateStatement.setString(5, name);
                updateStatement.setString(6, phone);
                updateStatement.setInt(7, Integer.parseInt(salary));
                updateStatement.setInt(8, Integer.parseInt(id));


                Employee employee=new Employee(Integer.parseInt(id),phone,Integer.parseInt(Age),name,gender,department,(int)Double.parseDouble(salary));

                employees.add(employee);
                // Execute the update statement
                updateStatement.executeUpdate();
                System.out.println("Updated");
                JOptionPane.showMessageDialog(null, "Successful to update employee data.", "Update Employee Data",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear the input fields
                clearFields();
            }
            else
                JOptionPane.showMessageDialog(null, "ID Doesn't Exist.", "Update Employee Data",
                        JOptionPane.INFORMATION_MESSAGE);


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update employee data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearFields() {
        cmbID.setSelectedIndex(-1);
        txtDepart.setText("");
        rbtnmale.setSelected(false);
        rbtnfemale.setSelected(false);
        txtName.setText("");
        txtPhone.setText("");
        txtSalary.setText("");
        txtAge.setText("");
    }
    public static void main(String[] args) {
        JFrame frame=new JFrame("GUI");
        frame.setContentPane(new FormMain().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
