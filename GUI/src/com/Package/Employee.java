package com.Package;

public class Employee extends Person{
    private String department;
    private double salary;

    public Employee(int ID, String phone, int age, String name, String gender,  String department,  double salary) {
        super(ID, phone, age, name, gender);
        this.salary = salary;
        this.department=department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }



    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    // Getters and setters

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + super.getID() +
                ", department='" + department + '\'' +
                ", gender='" + super.getGender() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", phone='" + super.getPhone() + '\'' +
                ", salary=" + salary +
                ", age=" + super.getAge() +
                '}';
    }
}
