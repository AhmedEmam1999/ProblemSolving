package com.Package;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.List;

public class TableGrid {
    private final List<Employee> employees;
    private JTable tableData;
    private PreparedStatement showStatement;
    private DefaultTableModel tableModel;
    private JPanel panel1;
    private static final String url="jdbc:postgresql://localhost:5432/DataBase";
    private static final String user="postgres";
    private static final String password = "8520";

    public TableGrid(JTable tGridData, List<Employee> employees) {
        this.tableData = tGridData;
        this.employees = employees;
    }

    public TableGrid() {
        employees = null;
        showEmployees();
    }
    private void showEmployees() {
        Connection connection ;
        try {
            connection= DriverManager.getConnection(url,user,password);
            showStatement = connection.prepareStatement("SELECT * FROM employees");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            tableData=new JTable(tableModel);
            tableData.setVisible(true);

            resultSet.close();
            showStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("TableGrid");
        frame.setContentPane(new TableGrid().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
